/**
 * Copyright © 2017-2019 WL.All Rights Reserved.
 */
package pers.wl.payment.api.test.repository;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

import pers.wl.payment.core.entity.TbConfigApplicationAli;
import pers.wl.payment.core.repository.TbConfigApplicationAliRepository;
import pers.wl.payment.core.service.config.cache.model.AppAliCacheModel;

/**
 * 描述说明
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2019年8月6日 下午12:43:59
 * @since JDK 1.8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TbConfigApplicationAliRepositoryTest {
	@Autowired
	private TbConfigApplicationAliRepository tbConfigApplicationAliRepository;

	@Test
	public void findAppAliAvailableConfig() {
		String appId = "5d47b6dea93a0f262ffec9cf";
		List<AppAliCacheModel> list = tbConfigApplicationAliRepository.findAppAliAvailableConfig(appId);
		System.out.println(JSON.toJSONString(list));
	}

	@Test
	public void findByConfigAliId() {
		String configAliId = "5d47d023a93a19b5ae4feae6";
		List<TbConfigApplicationAli> list = tbConfigApplicationAliRepository.findByConfigAliId(configAliId);
		System.out.println(JSON.toJSONString(list));
	}
}
