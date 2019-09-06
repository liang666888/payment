/**
 * Copyright © 2017-2019 WL.All Rights Reserved.
 */
package pers.wl.payment.core.service.config;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cloud.comp.common.exceptions.BizException;

import cn.hutool.core.util.IdUtil;
import pers.wl.payment.core.api.client.config.dto.AddConfigAliDto;
import pers.wl.payment.core.api.client.config.dto.UpdateConfigAliDto;
import pers.wl.payment.core.api.enums.PayApiRetCodeEnum;
import pers.wl.payment.core.common.annotations.ServiceOper;
import pers.wl.payment.core.entity.TbConfigAli;
import pers.wl.payment.core.entity.TbConfigApplicationAli;
import pers.wl.payment.core.repository.TbConfigAliRepository;
import pers.wl.payment.core.repository.TbConfigApplicationAliRepository;
import pers.wl.payment.core.service.config.cache.AppAliCacheService;
import pers.wl.payment.core.utils.AssertResultUtil;

/**
 * 描述说明
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2019年8月5日 下午1:32:30
 * @since JDK 1.8
 */
@CacheConfig(cacheNames = "TbConfigAliService")
@Service
public class TbConfigAliService {

	@Autowired
	private TbConfigAliRepository tbConfigAliRepository;

	@Autowired
	private TbConfigApplicationAliRepository tbConfigApplicationAliRepository;

	@Autowired
	private AppAliCacheService appAliCacheService;

	/**
	 * 新增支付宝配置
	 * 
	 * @param dto
	 * @return
	 */
	@ServiceOper(desc = "新增支付宝配置")
	public TbConfigAli add(AddConfigAliDto dto) {
		TbConfigAli entity = new TbConfigAli();
		BeanUtils.copyProperties(dto, entity);
		entity.setConfigAliId(IdUtil.objectId());
		entity.setCreateTime(new Date());
		return tbConfigAliRepository.saveAndFlush(entity);
	}

	/**
	 * 修改支付宝配置
	 * 
	 * @param dto
	 * @return
	 */
	@CachePut(key = "#p0.configAliId")
	@Modifying
	@ServiceOper(desc = "修改支付宝配置")
	public TbConfigAli update(UpdateConfigAliDto dto) {
		Optional<TbConfigAli> optional = tbConfigAliRepository.findById(dto.getConfigAliId());
		TbConfigAli entity = optional.orElseThrow(() -> new BizException(PayApiRetCodeEnum.RECORD_NOT_EXIST.code,
				PayApiRetCodeEnum.RECORD_NOT_EXIST.msg));
		BeanUtils.copyProperties(dto, entity);
		entity.setUpdateTime(new Date());
		entity = tbConfigAliRepository.saveAndFlush(entity);
		// 重置关联的应用缓存
		List<TbConfigApplicationAli> reationAppList = tbConfigApplicationAliRepository
				.findByConfigAliId(dto.getConfigAliId());
		for (TbConfigApplicationAli tbConfigApplicationAli : reationAppList) {
			appAliCacheService.resetAppAvailableAliConfig(tbConfigApplicationAli.getAppId());
		}
		return entity;
	}

	/**
	 * 删除支付宝配置
	 * 
	 * @param id
	 */
	@CacheEvict(key = "#p0")
	@Modifying
	@ServiceOper(desc = "删除支付宝配置")
	public void delete(String configAliId) {
		// 查询关联的应用列表
		List<TbConfigApplicationAli> reationAppList = tbConfigApplicationAliRepository.findByConfigAliId(configAliId);
		AssertResultUtil.isTrue(CollectionUtils.isEmpty(reationAppList),PayApiRetCodeEnum.CONFIG_HAS_REATION_APP);
		tbConfigAliRepository.deleteById(configAliId);
	}

	/**
	 * 根据主键查询支付宝配置
	 * 
	 * @param configAliId
	 * @return
	 */
	@Cacheable(key = "#p0")
	@ServiceOper(desc = "根据主键查询支付宝配置")
	public TbConfigAli queryByConfigAliId(String configAliId) {
		Optional<TbConfigAli> optional = tbConfigAliRepository.findById(configAliId);
		TbConfigAli entity = optional.orElseThrow(() -> new BizException(PayApiRetCodeEnum.RECORD_NOT_EXIST.code,
				PayApiRetCodeEnum.RECORD_NOT_EXIST.msg));
		return entity;
	}
}
