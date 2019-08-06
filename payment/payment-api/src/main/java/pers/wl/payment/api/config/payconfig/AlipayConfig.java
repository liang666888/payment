/**
 * Copyright © 2017-2019 WL.All Rights Reserved.
 */
package pers.wl.payment.api.config.payconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;

/**
 * 描述说明 支付宝支付配置
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2019年5月20日 下午5:01:37
 * @since JDK 1.8
 */
@Data
@Configuration
@PropertySource(value="classpath:config/alipay.properties")
@ConfigurationProperties(prefix = "alipay")
public class AlipayConfig {
	
	public static final String APP_PRODUCT_CODE    = "QUICK_MSECURITY_PAY";

    public static final String DIRECT_PRODUCT_CODE = "FAST_INSTANT_TRADE_PAY";

    public static final String WAP_PRODUCT_CODE    = "QUICK_WAP_PAY";

	private String charset;

	private String format;

	/**
	 * 支付网关地址
	 */
	private String gatewayUrl;

	/**
	 * 支付宝公钥
	 */
	private String alipayPulicKey;
	
	/**
	 * 支付宝异步通知地址
	 */
	private String notifyUrl;

}
