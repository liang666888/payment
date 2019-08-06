/**
 * Copyright © 2017-2019 WL.All Rights Reserved.
 */
package pers.wl.payment.api.service.config.cache;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.comp.cache.redis.RedisUtil;

import pers.wl.payment.api.repository.TbConfigApplicationAliRepository;
import pers.wl.payment.api.service.config.cache.model.AppAliCacheModel;
import pers.wl.payment.api.utils.LogUtil;

/**
 * 描述说明
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2019年8月6日 上午10:31:49
 * @since JDK 1.8
 */
@Service
public class AppAliCacheService {

	private static final String APP_ALI_CONFIG_CACHE = "APP_ALI_CONFIG_CACHE_";

	private static final Logger logger = LoggerFactory.getLogger(AppAliCacheService.class);

	@Autowired
	private RedisUtil redisUtil;

	@Autowired
	private TbConfigApplicationAliRepository tbConfigApplicationAliRepository;

	/**
	 * 获取应用可用的支付宝配置
	 * 
	 * @param appId
	 * @return
	 */
	public List<AppAliCacheModel> getAppAvailableAliConfig(String appId) {
		String key = APP_ALI_CONFIG_CACHE + appId;
		List<AppAliCacheModel> cachelist = null;
		if (redisUtil.existsKey(key)) {
			cachelist = redisUtil.getObjectCacheList(key, AppAliCacheModel.class);
		} else {
			// 缓存不存在从数据库查询
			cachelist = tbConfigApplicationAliRepository.findAppAliAvailableConfig(appId);
			try {
				redisUtil.putObjectCache(key, cachelist);
			} catch (Exception e) {
				LogUtil.warn(logger, "应用可用支付宝配置放入redis缓存失败", "appId:", appId, e.getMessage());
			}

		}
		return cachelist;
	}

	/**
	 * 重置应用可用的支付宝配置
	 * 
	 * @param appId
	 */
	public void resetAppAvailableAliConfig(String... appIds) {
		List<String> keys = new ArrayList<String>();
		for (String appId : appIds) {
			keys.add(APP_ALI_CONFIG_CACHE + appId);
		}
		redisUtil.deleteKeys(keys);
	}
}
