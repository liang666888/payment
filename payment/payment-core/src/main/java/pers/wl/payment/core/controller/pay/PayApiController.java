/**
 * Copyright © 2017-2019 WL.All Rights Reserved.
 */
package pers.wl.payment.core.controller.pay;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import pers.wl.payment.core.api.client.pay.PayApiClient;
import pers.wl.payment.core.api.client.pay.dto.PayOrderDto;
import pers.wl.payment.core.service.pay.PayService;

/**
 * 描述说明
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2019年5月20日 下午5:19:08
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/payapi")
public class PayApiController implements PayApiClient {

	@Autowired
	private PayService payService;

	/**
	 * @see pers.wl.payment.core.api.client.pay.PayApiClient#wappay(pers.wl.payment.core.api.client.pay.dto.PayOrderDto)
	 */
	@Override
	public void wappay(@Valid PayOrderDto payOrderDto) {
		payService.wapPay(payOrderDto);
	}

	/** 
	 * @see pers.wl.payment.core.api.client.pay.PayApiClient#apppay(pers.wl.payment.core.api.client.pay.dto.PayOrderDto)
	 */
	@Override
	public void apppay(@Valid PayOrderDto payOrderDto) {
		payService.appPay(payOrderDto);
	}

}
