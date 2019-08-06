/**
 * Copyright © 2017-2019 WL.All Rights Reserved.
 */
package pers.wl.payment.api.service.pay.handler;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.cloud.comp.common.model.ResultModel;

import pers.wl.payment.api.common.enums.PayApiRetCodeEnum;
import pers.wl.payment.api.common.enums.PayTypeEnum;
import pers.wl.payment.api.config.payconfig.AlipayConfig;
import pers.wl.payment.api.service.config.cache.AppAliCacheService;
import pers.wl.payment.api.service.config.cache.model.AppAliCacheModel;
import pers.wl.payment.api.service.pay.PayHandler;
import pers.wl.payment.api.service.pay.dto.AliPayContent;
import pers.wl.payment.api.service.pay.dto.PayOrderDto;
import pers.wl.payment.api.utils.AssertResultUtil;
import pers.wl.payment.api.utils.PayConfigTakeOutUtil;
import pers.wl.payment.api.utils.WebUtil;

/**
 * O 描述说明
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2019年8月5日 下午4:45:59
 * @since JDK 1.8
 */
@Service
public class AliPayAppHandler implements PayHandler {

	@Autowired
	private AlipayConfig alipayConfig;

	@Autowired
	private AppAliCacheService appAliCacheService;

	/**
	 * @see pers.wl.payment.api.service.pay.PayHandler#wapPay(pers.wl.payment.api.service.pay.dto.PayOrderDto)
	 */
	@Override
	public void wapPay(PayOrderDto payOrderDto) {
		// 获取应用可用的支付宝收款账户列表
		List<AppAliCacheModel> availableConfigList = appAliCacheService
				.getAppAvailableAliConfig(payOrderDto.getAppId());
		// 按概率挑出收款账号
		AppAliCacheModel config = PayConfigTakeOutUtil.takeOutAliConfig(availableConfigList);
		AssertResultUtil.notNull(config, PayApiRetCodeEnum.NO_PAY_CONFIG);
		// 初始化支付宝支付sdk客户端工具
		AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.getGatewayUrl(), config.getAliAppId(),
				config.getPrivateKey(), alipayConfig.getFormat(), alipayConfig.getCharset(),
				alipayConfig.getAlipayPulicKey(), config.getSignType());
		// 构建支付宝wap支付参数
		AliPayContent payContent = new AliPayContent();
		payContent.setSubject(payOrderDto.getSubject());
		payContent.setBody(payOrderDto.getMemo());
		payContent.setOut_trade_no(payOrderDto.getOrderno());
		payContent.setTotal_amount(payOrderDto.getAmount());
		payContent.setProduct_code(getAliProductCode(payOrderDto.getPayType()));
		// 设置支付宝回传参数
		// payContent.setPassback_params("{\"applicationId\":" +
		// payOrderDto.getApplicationId() + "}");
		// 创建API对应的request(这里支付宝即时到账支付/网站PC支付request)
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		// 在公共参数中设置同步与和异步通知地址
		alipayRequest.setReturnUrl(config.getReturnUrl());
		// 回调通知路径上加上appId，用于区分应用
		String notifyUrl = MessageFormat.format(alipayConfig.getNotifyUrl(), payOrderDto.getAppId(),
				config.getConfigAliId());
		alipayRequest.setNotifyUrl(notifyUrl);
		// 填充业务参数，参考
		// https://doc.open.alipay.com/doc2/detail.htm?treeId=203&articleId=105463&docType=1#s0
		alipayRequest.setBizContent(JSON.toJSONString(payContent));
		// 调用SDK生成html表单
		String form = "";
		try {
			form = alipayClient.pageExecute(alipayRequest).getBody();
			// 直接将完整的表单html输出到页面
			HttpServletResponse response = WebUtil.getHttpServletResponse();
			response.setContentType("text/html;charset=" + alipayConfig.getCharset());
			response.getWriter().write(form);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see pers.wl.payment.api.service.pay.PayHandler#appPay()
	 */
	@Override
	public ResultModel<Object> appPay(PayOrderDto payOrderDto) {
		return null;
	}

	private String getAliProductCode(PayTypeEnum payType) {
		String productCode = null;
		switch (payType) {
		case ALIPAY_APP:
			productCode = "QUICK_MSECURITY_PAY";
			break;
		case ALIPAY_PC_DIRECT:
			productCode = "FAST_INSTANT_TRADE_PAY";
			break;
		case ALIPAY_QR:
			productCode = "FACE_TO_FACE_PAYMENT";
			break;
		case ALIPAY_WAP:
			productCode = "QUICK_WAP_PAY";
			break;

		default:
			productCode = "FAST_INSTANT_TRADE_PAY";
			break;
		}
		return productCode;
	}

}
