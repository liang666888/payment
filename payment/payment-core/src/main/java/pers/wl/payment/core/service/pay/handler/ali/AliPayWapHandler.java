/**
 * Copyright © 2017-2019 WL.All Rights Reserved.
 */
package pers.wl.payment.core.service.pay.handler.ali;

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
import com.alipay.api.request.AlipayTradeWapPayRequest;

import pers.wl.payment.core.api.client.pay.dto.PayOrderDto;
import pers.wl.payment.core.api.enums.PayApiRetCodeEnum;
import pers.wl.payment.core.config.payconfig.AlipayConfig;
import pers.wl.payment.core.service.config.cache.AppAliCacheService;
import pers.wl.payment.core.service.config.cache.model.AppAliCacheModel;
import pers.wl.payment.core.service.pay.dto.AliPayContent;
import pers.wl.payment.core.service.pay.handler.WapPayHandler;
import pers.wl.payment.core.utils.AssertResultUtil;
import pers.wl.payment.core.utils.PayConfigTakeOutUtil;
import pers.wl.payment.core.utils.WebUtil;

/**
 * O 描述说明
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2019年8月5日 下午4:45:59
 * @since JDK 1.8
 */
@Service
public class AliPayWapHandler implements WapPayHandler {

	@Autowired
	private AlipayConfig alipayConfig;

	@Autowired
	private AppAliCacheService appAliCacheService;

	/**
	 * @see pers.wl.payment.core.service.pay.WapPayHandler#wapPay(pers.wl.payment.core.service.pay.dto.PayOrderDto)
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
		payContent.setProduct_code(AliProductCode.getAliProductCode(payOrderDto.getPayType()));
		// 设置支付宝回传参数
		// payContent.setPassback_params("{\"applicationId\":" +
		// payOrderDto.getApplicationId() + "}");
		// 创建API对应的request(这里支付宝即时到账支付/网站PC支付request)
		AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
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

}
