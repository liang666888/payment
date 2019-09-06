/**
 * Copyright © 2017-2019 WL.All Rights Reserved.
 */
package pers.wl.payment.api.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import pers.wl.payment.api.test.base.TestSupport;
import pers.wl.payment.core.config.payconfig.AlipayConfig;

/**
 * 描述说明
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2019年5月20日 下午5:43:05
 * @since JDK 1.8
 */
public class AlipayConfigTest extends TestSupport {
	
	@Autowired
	private AlipayConfig alipayConfig;
	
	@Test
	public void test() {
		System.out.println(alipayConfig.getGatewayUrl());
	}
}
