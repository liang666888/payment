/**
 * Copyright © 2017-2019 WL.All Rights Reserved.
 */
package pers.wl.payment.core.service.pay.handler.ali;

import java.text.MessageFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.cloud.comp.common.exceptions.BizException;
import com.cloud.comp.common.model.ResultModel;

import cn.hutool.core.util.StrUtil;
import pers.wl.payment.core.api.client.pay.dto.PayOrderDto;
import pers.wl.payment.core.api.enums.PayApiRetCodeEnum;
import pers.wl.payment.core.config.payconfig.AlipayConfig;
import pers.wl.payment.core.service.config.cache.AppAliCacheService;
import pers.wl.payment.core.service.config.cache.model.AppAliCacheModel;
import pers.wl.payment.core.service.pay.handler.AppPayHandler;
import pers.wl.payment.core.utils.AssertResultUtil;
import pers.wl.payment.core.utils.LogUtil;
import pers.wl.payment.core.utils.PayConfigTakeOutUtil;
import pers.wl.payment.core.utils.ResultUtil;

/**
 * O 描述说明
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2019年8月5日 下午4:45:59
 * @since JDK 1.8
 */
@Service
public class AliPayAppHandler implements AppPayHandler {

	private static final Logger logger = LoggerFactory.getLogger(AliPayAppHandler.class);

	@Autowired
	private AlipayConfig alipayConfig;

	@Autowired
	private AppAliCacheService appAliCacheService;

	/**
	 * 
	 * @see pers.wl.payment.core.service.pay.handler.AppPayHandler#appPay(pers.wl.payment.core.api.client.pay.dto.PayOrderDto)
	 */
	@Override
	public ResultModel<Object> appPay(PayOrderDto payOrderDto) {
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
		// 构建支付宝app支付参数
		AlipayTradeAppPayModel alipayTradeAppPayModel = new AlipayTradeAppPayModel();
		alipayTradeAppPayModel.setSubject(payOrderDto.getSubject());
		alipayTradeAppPayModel.setBody(payOrderDto.getMemo());
		alipayTradeAppPayModel.setOutTradeNo(payOrderDto.getOrderno());
		alipayTradeAppPayModel.setTotalAmount(payOrderDto.getAmount());
		alipayTradeAppPayModel.setProductCode(AliProductCode.getAliProductCode(payOrderDto.getPayType()));
		AlipayTradeAppPayRequest alipayRequest = new AlipayTradeAppPayRequest();
		// 回调通知路径上加上appId，用于区分应用
		String notifyUrl = MessageFormat.format(alipayConfig.getNotifyUrl(), payOrderDto.getAppId(),
				config.getConfigAliId());
		alipayRequest.setNotifyUrl(notifyUrl);
		alipayRequest.setBizModel(alipayTradeAppPayModel);
		AlipayTradeAppPayResponse response;
		try {
			LogUtil.info(logger, "支付宝app支付请求报文：", JSON.toJSONString(alipayRequest));
			// 这里和普通的接口调用不同，使用的是sdkExecute
			response = alipayClient.sdkExecute(alipayRequest);
			// 就是orderString 可以直接给客户端请求，无需再做处理。
			LogUtil.info(logger, "支付宝app支付返回报文：", JSON.toJSONString(response));
			if (StrUtil.equalsAny(response.getCode(), "10000", "40004")) {
				// 10000成功，40004业务处理失败
				AssertResultUtil.equals(response.getCode(), "10000",
						new BizException(response.getSubCode(), response.getSubMsg()));
			} else {
				throw new BizException(response.getCode(), response.getMsg());
			}
		} catch (AlipayApiException e) {
			LogUtil.error(logger, "支付宝app支付异常", e);
			throw new BizException(PayApiRetCodeEnum.SYSTEM_ERROR.code, PayApiRetCodeEnum.SYSTEM_ERROR.msg);
		}

		return ResultUtil.success(response.getBody());
	}

}
