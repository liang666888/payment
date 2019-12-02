/**
 * Copyright © 2017-2019 WL.All Rights Reserved.
 */
package pers.wl.payment.core.api.client.pay;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import pers.wl.payment.core.api.client.pay.dto.PayOrderDto;

/**
 * 描述说明 支付api
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2019年9月6日 下午3:00:59
 * @since JDK 1.8
 */
@FeignClient(value = "pay-core", path = "/payapi")
public interface PayApiClient {

	/**
	 * pc/h5网页支付
	 */
	@PostMapping("/wappay")
	public void wappay(PayOrderDto payOrderDto);
	
	/**
	 * app支付
	 */
	@PostMapping("/apppay")
	public void apppay(PayOrderDto payOrderDto);
}
