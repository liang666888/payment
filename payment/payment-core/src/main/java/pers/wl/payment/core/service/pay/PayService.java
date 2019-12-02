/**
 * Copyright © 2017-2019 WL.All Rights Reserved.
 */
package pers.wl.payment.core.service.pay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.comp.common.model.ResultModel;

import cn.hutool.core.util.StrUtil;
import pers.wl.payment.core.api.client.pay.dto.PayOrderDto;
import pers.wl.payment.core.api.enums.PayApiRetCodeEnum;
import pers.wl.payment.core.api.enums.StatEnum;
import pers.wl.payment.core.common.annotations.ServiceOper;
import pers.wl.payment.core.entity.TbConfigApplication;
import pers.wl.payment.core.service.config.TbConfigApplicationService;
import pers.wl.payment.core.service.pay.handler.AppPayHandler;
import pers.wl.payment.core.service.pay.handler.PayHandlerFactory;
import pers.wl.payment.core.service.pay.handler.WapPayHandler;
import pers.wl.payment.core.utils.AssertResultUtil;

/**
 * 描述说明
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2019年8月5日 下午3:38:42
 * @since JDK 1.8
 */
@Service
public class PayService {

	@Autowired
	private TbConfigApplicationService tbConfigApplicationService;

	/**
	 * pc/h5网页支付
	 * 
	 * @param payOrderDto
	 */
	@ServiceOper(desc = "wap支付")
	public void wapPay(PayOrderDto payOrderDto) {
		// 订单参数校验
		paramsValidate(payOrderDto);
		WapPayHandler wapPayHandler = PayHandlerFactory.getWapPayHandler(payOrderDto.getPayType());
		wapPayHandler.wapPay(payOrderDto);
	}

	/**
	 * app支付
	 * 
	 * @param payOrderDto
	 * @return
	 */
	@ServiceOper(desc = "app支付")
	public ResultModel<Object> appPay(PayOrderDto payOrderDto) {
		// 订单参数校验
		paramsValidate(payOrderDto);
		AppPayHandler appPayHandler = PayHandlerFactory.getAppPayHandler(payOrderDto.getPayType());
		return appPayHandler.appPay(payOrderDto);
	}

	/**
	 * 订单参数校验
	 * 
	 * @param dto
	 */
	private void paramsValidate(PayOrderDto payOrderDto) {
		TbConfigApplication configApplication = tbConfigApplicationService.queryByAppId(payOrderDto.getAppId());
		AssertResultUtil.isTrue(
				configApplication != null && StrUtil.equals(StatEnum.ENABLE.name(), configApplication.getStat()),
				PayApiRetCodeEnum.APP_NOT_EXIST);
	}
}
