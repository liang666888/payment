/**
 * Copyright © 2017-2019 WL.All Rights Reserved.
 */
package pers.wl.payment.core.api.client.config;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * 描述说明 支付配置
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2019年9月6日 下午3:08:46
 * @since JDK 1.8
 */
@FeignClient(value = "pay-core", path = "/payconfig")
public interface PayConfigClient {

}
