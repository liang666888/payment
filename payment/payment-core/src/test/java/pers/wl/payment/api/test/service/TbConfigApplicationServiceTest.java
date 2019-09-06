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

import pers.wl.payment.core.api.client.config.dto.AddConfigAppDto;
import pers.wl.payment.core.api.client.config.dto.UpdateConfigAppDto;
import pers.wl.payment.core.api.enums.StatEnum;
import pers.wl.payment.core.entity.TbConfigApplication;
import pers.wl.payment.core.service.config.TbConfigApplicationService;

/**
 * 描述说明
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2019年8月5日 下午12:36:15
 * @since JDK 1.8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TbConfigApplicationServiceTest {

	@Autowired
	private TbConfigApplicationService tbConfigApplicationService;

	/**
	 * 新增应用
	 * 
	 * @param dto
	 * @return
	 */
	@Test
	public void add() {
		AddConfigAppDto dto = new AddConfigAppDto();
		dto.setAppName("测试应用");
		dto.setDomain("http://127.0.0.1:8080/");
		dto.setMemo("测试测试");
		dto.setStat(StatEnum.ENABLE.name());
		TbConfigApplication entity = tbConfigApplicationService.add(dto);
		System.out.println(JSON.toJSONString(entity));
	}

	/**
	 * 修改应用信息
	 * 
	 * @param dto
	 * @return
	 */
	@Test
	public void update() {
		UpdateConfigAppDto dto = new UpdateConfigAppDto();
		dto.setAppId("5d47b6dea93a0f262ffec9cf");
		dto.setAppName("测试应用");
		dto.setDomain("http://127.0.0.1:8080/");
		dto.setMemo("测试");
		dto.setStat(StatEnum.ENABLE.name());
		TbConfigApplication entity = tbConfigApplicationService.update(dto);
		System.out.println(JSON.toJSONString(entity));
	}

	/**
	 * 删除应用
	 * 
	 * @param id
	 */
	@Test
	public void delete() {
		String appId = "5d47b6dea93a0f262ffec9cf";
		tbConfigApplicationService.delete(appId);
	}
	
	@Test
	public void queryByAppId() {
		String appId = "5d47b6dea93a0f262ffec9cf";
		TbConfigApplication entity = tbConfigApplicationService.queryByAppId(appId);
		System.out.println(JSON.toJSONString(entity));
	}
}
