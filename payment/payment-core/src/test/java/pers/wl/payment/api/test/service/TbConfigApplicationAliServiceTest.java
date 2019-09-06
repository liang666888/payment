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

import pers.wl.payment.core.api.client.config.dto.AddAppAliDto;
import pers.wl.payment.core.api.client.config.dto.UpdateAppAliDto;
import pers.wl.payment.core.api.enums.StatEnum;
import pers.wl.payment.core.entity.TbConfigApplicationAli;
import pers.wl.payment.core.service.config.TbConfigApplicationAliService;

/**
 * 描述说明
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2019年8月5日 下午2:47:17
 * @since JDK 1.8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TbConfigApplicationAliServiceTest {

	@Autowired
	private TbConfigApplicationAliService tbConfigApplicationAliService;

	@Test
	public void add() {
		AddAppAliDto dto = new AddAppAliDto();
		dto.setAppId("5d47b6dea93a0f262ffec9cf");
		dto.setConfigAliId("5d47d023a93a19b5ae4feae6");
		dto.setNotifyUrl(null);
		dto.setReturnUrl("http://127.0.0.1:8080/");
		dto.setStat(StatEnum.ENABLE.name());
		dto.setWeight(1);
		TbConfigApplicationAli entity = tbConfigApplicationAliService.add(dto);
		System.out.println(JSON.toJSONString(entity));
	}

	@Test
	public void update() {
		UpdateAppAliDto dto = new UpdateAppAliDto();
		dto.setSerialId("");
		dto.setAppId("5d47b6dea93a0f262ffec9cf");
		dto.setConfigAliId("5d47d023a93a19b5ae4feae6");
		dto.setNotifyUrl(null);
		dto.setReturnUrl("http://127.0.0.1:8080/");
		dto.setStat(StatEnum.ENABLE.name());
		dto.setWeight(2);
		TbConfigApplicationAli entity = tbConfigApplicationAliService.update(dto);
		System.out.println(JSON.toJSONString(entity));
	}

	@Test
	public void delete() {
		String serialId = "";
		tbConfigApplicationAliService.delete(serialId);
	}
}
