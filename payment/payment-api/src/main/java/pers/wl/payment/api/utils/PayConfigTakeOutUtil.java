package pers.wl.payment.api.utils;

import java.util.List;

import org.springframework.util.CollectionUtils;

import pers.wl.payment.api.service.config.cache.model.AppAliCacheModel;

/**
 * <p>
 * File：PayConfigTakeOutUtil.java
 * </p>
 * <p>
 * Title: 按概率抽取支付配置项工具类
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2018 上午10:04:09
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author 吴亮
 * @version 1.0
 */
public class PayConfigTakeOutUtil {
	/**
	 * 按权重配置比抽出支付宝收款账号
	 * 
	 * @author 吴亮
	 * @param configList
	 * @return
	 */
	public static AppAliCacheModel takeOutAliConfig(List<AppAliCacheModel> configList) {
		AppAliCacheModel config = null;
		if (!CollectionUtils.isEmpty(configList)) {
			if (configList.size() > 1) {
				// 计算总权重
				double sumWeight = 0;
				for (AppAliCacheModel c : configList) {
					sumWeight += c.getWeight();
				}
				// 产生随机数
				double randomNumber;
				randomNumber = Math.random();
				// 根据随机数在所有奖品分布的区域并确定所抽奖品
				double d1 = 0;
				double d2 = 0;
				for (int i = 0; i < configList.size(); i++) {
					d2 += configList.get(i).getWeight() / sumWeight;
					if (i == 0) {
						d1 = 0;
					} else {
						d1 += configList.get(i - 1).getWeight() / sumWeight;
					}
					if (randomNumber >= d1 && randomNumber <= d2) {
						config = configList.get(i);
						break;
					}
				}
			} else {
				config = configList.get(0);
			}
		}
		return config;
	}

}
