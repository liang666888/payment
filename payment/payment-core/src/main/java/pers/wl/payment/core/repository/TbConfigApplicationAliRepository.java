/**
 * Copyright © 2017-2019 WL.All Rights Reserved.
 */
package pers.wl.payment.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pers.wl.payment.core.entity.TbConfigApplicationAli;
import pers.wl.payment.core.service.config.cache.model.AppAliCacheModel;

/**
 * 描述说明
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2019年4月21日 下午6:48:24
 * @since JDK 1.8
 */
public interface TbConfigApplicationAliRepository
		extends JpaRepository<TbConfigApplicationAli, String>, JpaSpecificationExecutor<TbConfigApplicationAli> {

	/**
	 * 查询应用可用的支付宝配置
	 * 
	 * @param appId
	 *            应用ID
	 * @return
	 */
	@Query(value = "select new pers.wl.payment.core.service.config.cache.model.AppAliCacheModel(t1.serialId,t1.appId,"
			+ "t1.configAliId,t1.notifyUrl,t1.returnUrl,t1.weight,t2.aliAppId,t2.partner,t2.privateKey,t2.sellerId,"
			+ "t2.signType,t2.stat) from TbConfigApplicationAli t1,TbConfigAli t2 "
			+ "where t1.appId = :appId and t1.configAliId = t2.configAliId and t2.stat = 'ENABLE'")
	public List<AppAliCacheModel> findAppAliAvailableConfig(@Param("appId") String appId);

	/**
	 * 根据支付宝配置ID，查询出所有使用改配置的应用
	 * 
	 * @param configAliId
	 * @return
	 */
	public List<TbConfigApplicationAli> findByConfigAliId(String configAliId);
}
