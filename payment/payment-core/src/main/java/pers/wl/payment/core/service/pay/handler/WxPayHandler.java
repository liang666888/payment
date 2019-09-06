/**
 * Copyright © 2017-2019 WL.All Rights Reserved.
 */
package pers.wl.payment.core.service.pay.handler;

import com.cloud.comp.common.model.ResultModel;

import pers.wl.payment.core.api.client.pay.dto.PayOrderDto;
import pers.wl.payment.core.service.pay.PayHandler;

/** 
 * 描述说明
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2019年8月5日 下午5:08:02
 * @since JDK 1.8
 */
public class WxPayHandler implements PayHandler {

	/** 
	 * @see pers.wl.payment.core.service.pay.PayHandler#wapPay(pers.wl.payment.core.service.pay.dto.PayOrderDto)
	 */
	@Override
	public void wapPay(PayOrderDto payOrderDto) {
	}

	/** 
	 * @see pers.wl.payment.core.service.pay.PayHandler#appPay(pers.wl.payment.core.service.pay.dto.PayOrderDto)
	 */
	@Override
	public ResultModel<Object> appPay(PayOrderDto payOrderDto) {
		return null;
	}

	

}
