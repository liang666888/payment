/**
 * Copyright © 2017-2019 WL.All Rights Reserved.
 */
package pers.wl.payment.core.controller.config;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pers.wl.payment.core.api.client.config.PayConfigClient;

/**
 * 描述说明 支付配置
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2019年9月6日 下午3:09:43
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/payconfig")
public class PayConfigController implements PayConfigClient {

}
