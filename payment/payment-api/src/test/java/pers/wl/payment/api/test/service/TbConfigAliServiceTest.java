/**
 * Copyright © 2017-2019 WL.All Rights Reserved.
 */
package pers.wl.payment.api.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

import pers.wl.payment.api.common.enums.AliSignTypeEnum;
import pers.wl.payment.api.common.enums.StatEnum;
import pers.wl.payment.api.entity.TbConfigAli;
import pers.wl.payment.api.service.config.TbConfigAliService;
import pers.wl.payment.api.service.config.dto.AddConfigAliDto;
import pers.wl.payment.api.service.config.dto.UpdateConfigAliDto;

/**
 * 描述说明
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2019年8月5日 下午1:58:19
 * @since JDK 1.8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TbConfigAliServiceTest {

	@Autowired
	private TbConfigAliService tbConfigAliService;

	/**
	 * 新增支付宝配置
	 * 
	 * @param dto
	 * @return
	 */
	@Test
	public void add() {
		// 私钥
		String privateKey = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCye9LawaZey5psCySPjl7apMRE2LfIGpfXHiL3MUEcUYH6sZK9YOxjUSjkDMF7KXkOyBLhoqD/Y3ANd/XayvojXK5EZDa3N9n2+wYNbsliIAWZfPRisk0+EZO9b126aCKtbT590jpHMXBHBluP9TdbeSlkHrZuV7khIHrIWONq3uV7D5rYR8E/OJj2u3lx4P2OfYz4tupU0jkX9yiPKnf7VpI4vwAd30nI5VPy5qwDuoMYXNfZHpEfxbnUKfMpQTipEYXTCsY9n7l1CR38d2vIZP59mX9ziARAzBKhw/3utqUz9h37JNOH6nTa0Kv14LadYVkf7PqZBNAUfztQXBGnAgMBAAECggEBAIs75zg5+wRWRqfkZWgLw1lDUcz3Vx8mB6+L1fEKHmXt+7zKi9Nqad3scT696K2go8xxLbGRQZEZTE9ldohw8YNmiJfXqYtyaXmvR4BxHW6L1nJvNOJNf85cvej4mKCTHgI2h5p1xnrFSXZOslzyCXY1ZaGyB9S6YDREdyQVm4tA9zeCBk21SecWhboX7nvJFPtKyeMipcZX39vT/iHZz3c1RWiZlzJ6VmDJWzjQIp49pIdDsxd8vIIsooTLndFiaRlTy8ivPyxFgh1W6+q7GbQOeQLYnEKaJxIdVwI1HmoC11T7DgPG1tjJFpT5+N61tpw0y5zyLH/IRtkx+l82Y0ECgYEA+YvpMvJBhutu6wmYgnULPkkQjkUCzDDt/oo5YBuyBFcO0P0ctft4PhhcPW/EA3GDuYGMIh9XTTs+nLXeiRLiOIfzM8aSIUOrw/0vcrxHBN8LI8YA8Bih8bG6O8lCwMrCmAy9YKLYquBljTyAwgXoIUgC+puUM1+LtXhHO2+sCQcCgYEAtxlzZkriVQtnl3XX8m/4hO3J3XVqJ6OgGRDsm1c8BEDi0aHy5g/85VkJ5YZO6UEHXoCyXRcFVMXaJZvOye2CldCgRTKOkThH5l8vF8Gkw+l3HKRjwxmNCejsm+06xHpg7yUoHDwYz3sKEWd/xQo4uH7RO74JkKE3VZ6LA2biqmECgYBrSz6/Cu4lpoCQShXqsYxeAWg+42kqhjct5Bx7OC04NH5IWSvJCxBOEodGWxG6MUpjrlGkMjmFGV34XzIJWbcuezWUI2KdLs1hfTCzckRO0d+mR/ILSWwH0VSzc/thjUxRt9iM/jeOcOD48vShd4yxEpdFTTNRS1l0cvydj4w2bQKBgQCBn2bu9/ybMGO5+hdS7qoFkUHjogfw4AmrNSwMHQnECRuKRRhsfs0OoKY884RR5VOON5YOCQohJaB2F4OhuJ9F6FhOJKiBblKYaLIyI0i92T4V7Nsjd6c9aMR47s0341tUcf2fX3xBBl3uTFNL7xyQoFcH8FVdKoS9/Gt1kRTOgQKBgQDN4z5DPJ5UpfeGI4gbmTErgJdUhSoIeICqfs2xH2YXJaSOh40bkL8Zg7KFQBVp3HwPAWrNXN2KJZ4cfDo2Vu29vFTCLq155IpjR+OwHRvE2yFrjJFiGKAR0LTONhE8jU9azq2tAu+u80Q2PUbCDAWzQT+i72sL6z/Dq72ufGzgqQ==";
		AddConfigAliDto dto = new AddConfigAliDto();
		dto.setAliAppId("2016081500253153");
		dto.setPartner(null);
		dto.setPrivateKey(privateKey);
		dto.setSellerId("吴亮沙箱账号(wkjpnx1880@sandbox.com)");
		dto.setSignType(AliSignTypeEnum.RSA2.name());
		dto.setStat(StatEnum.ENABLE.name());
		TbConfigAli entity = tbConfigAliService.add(dto);
		System.out.println(JSON.toJSONString(entity));
	}

	/**
	 * 修改支付宝配置
	 * 
	 * @param dto
	 * @return
	 */
	@Test
	public void update() {
		// 私钥
		String privateKey = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCye9LawaZey5psCySPjl7apMRE2LfIGpfXHiL3MUEcUYH6sZK9YOxjUSjkDMF7KXkOyBLhoqD/Y3ANd/XayvojXK5EZDa3N9n2+wYNbsliIAWZfPRisk0+EZO9b126aCKtbT590jpHMXBHBluP9TdbeSlkHrZuV7khIHrIWONq3uV7D5rYR8E/OJj2u3lx4P2OfYz4tupU0jkX9yiPKnf7VpI4vwAd30nI5VPy5qwDuoMYXNfZHpEfxbnUKfMpQTipEYXTCsY9n7l1CR38d2vIZP59mX9ziARAzBKhw/3utqUz9h37JNOH6nTa0Kv14LadYVkf7PqZBNAUfztQXBGnAgMBAAECggEBAIs75zg5+wRWRqfkZWgLw1lDUcz3Vx8mB6+L1fEKHmXt+7zKi9Nqad3scT696K2go8xxLbGRQZEZTE9ldohw8YNmiJfXqYtyaXmvR4BxHW6L1nJvNOJNf85cvej4mKCTHgI2h5p1xnrFSXZOslzyCXY1ZaGyB9S6YDREdyQVm4tA9zeCBk21SecWhboX7nvJFPtKyeMipcZX39vT/iHZz3c1RWiZlzJ6VmDJWzjQIp49pIdDsxd8vIIsooTLndFiaRlTy8ivPyxFgh1W6+q7GbQOeQLYnEKaJxIdVwI1HmoC11T7DgPG1tjJFpT5+N61tpw0y5zyLH/IRtkx+l82Y0ECgYEA+YvpMvJBhutu6wmYgnULPkkQjkUCzDDt/oo5YBuyBFcO0P0ctft4PhhcPW/EA3GDuYGMIh9XTTs+nLXeiRLiOIfzM8aSIUOrw/0vcrxHBN8LI8YA8Bih8bG6O8lCwMrCmAy9YKLYquBljTyAwgXoIUgC+puUM1+LtXhHO2+sCQcCgYEAtxlzZkriVQtnl3XX8m/4hO3J3XVqJ6OgGRDsm1c8BEDi0aHy5g/85VkJ5YZO6UEHXoCyXRcFVMXaJZvOye2CldCgRTKOkThH5l8vF8Gkw+l3HKRjwxmNCejsm+06xHpg7yUoHDwYz3sKEWd/xQo4uH7RO74JkKE3VZ6LA2biqmECgYBrSz6/Cu4lpoCQShXqsYxeAWg+42kqhjct5Bx7OC04NH5IWSvJCxBOEodGWxG6MUpjrlGkMjmFGV34XzIJWbcuezWUI2KdLs1hfTCzckRO0d+mR/ILSWwH0VSzc/thjUxRt9iM/jeOcOD48vShd4yxEpdFTTNRS1l0cvydj4w2bQKBgQCBn2bu9/ybMGO5+hdS7qoFkUHjogfw4AmrNSwMHQnECRuKRRhsfs0OoKY884RR5VOON5YOCQohJaB2F4OhuJ9F6FhOJKiBblKYaLIyI0i92T4V7Nsjd6c9aMR47s0341tUcf2fX3xBBl3uTFNL7xyQoFcH8FVdKoS9/Gt1kRTOgQKBgQDN4z5DPJ5UpfeGI4gbmTErgJdUhSoIeICqfs2xH2YXJaSOh40bkL8Zg7KFQBVp3HwPAWrNXN2KJZ4cfDo2Vu29vFTCLq155IpjR+OwHRvE2yFrjJFiGKAR0LTONhE8jU9azq2tAu+u80Q2PUbCDAWzQT+i72sL6z/Dq72ufGzgqQ==";
		UpdateConfigAliDto dto = new UpdateConfigAliDto();
		dto.setConfigAliId("");
		dto.setAliAppId("2016081500253153");
		dto.setPartner(null);
		dto.setPrivateKey(privateKey);
		dto.setSellerId("吴亮沙箱账号222(wkjpnx1880@sandbox.com)");
		dto.setSignType(AliSignTypeEnum.RSA2.name());
		dto.setStat(StatEnum.ENABLE.name());
		TbConfigAli entity = tbConfigAliService.update(dto);
		System.out.println(JSON.toJSONString(entity));
	}

	/**
	 * 删除支付宝配置
	 * 
	 * @param id
	 */
	@Test
	public void delete() {
		String configAliId = "";
		tbConfigAliService.delete(configAliId);
	}

	/**
	 * 根据主键查询支付宝配置
	 * 
	 * @param configAliId
	 * @return
	 */
	@Test
	public void queryByConfigAliId() {
		String configAliId = "";
		TbConfigAli entity = tbConfigAliService.queryByConfigAliId(configAliId);
		System.out.println(JSON.toJSONString(entity));
	}
}
