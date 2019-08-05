/**
 * Copyright © 2017-2019 WL.All Rights Reserved.
 */
package pers.wl.payment.api.controller.pay;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 描述说明
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2019年5月20日 下午5:19:08
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/api")
public class PayApiController {

	/**
	 * pc/h5网页支付
	 */
	@PostMapping("/wappay")
	public void wappay() {

	}
}