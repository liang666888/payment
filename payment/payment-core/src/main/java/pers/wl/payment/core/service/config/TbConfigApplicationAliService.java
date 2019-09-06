/**
 * Copyright © 2017-2019 WL.All Rights Reserved.
 */
package pers.wl.payment.core.service.config;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cloud.comp.common.exceptions.BizException;

import cn.hutool.core.util.IdUtil;
import pers.wl.payment.core.api.client.config.dto.AddAppAliDto;
import pers.wl.payment.core.api.client.config.dto.UpdateAppAliDto;
import pers.wl.payment.core.api.enums.PayApiRetCodeEnum;
import pers.wl.payment.core.common.annotations.ServiceOper;
import pers.wl.payment.core.entity.TbConfigApplicationAli;
import pers.wl.payment.core.repository.TbConfigApplicationAliRepository;
import pers.wl.payment.core.service.config.cache.AppAliCacheService;

/**
 * 描述说明
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2019年8月5日 上午11:34:43
 * @since JDK 1.8
 */
@Cacheable(cacheNames = "TbConfigApplicationAliService")
@Service
public class TbConfigApplicationAliService {

	@Autowired
	private TbConfigApplicationAliRepository tbConfigApplicationAliRepository;

	@Autowired
	private AppAliCacheService appAliCacheService;

	/**
	 * 新增应用支付宝配置
	 * 
	 * @param dto
	 * @return
	 */
	@ServiceOper(desc = "新增应用支付宝配置")
	public TbConfigApplicationAli add(AddAppAliDto dto) {
		TbConfigApplicationAli entity = new TbConfigApplicationAli();
		BeanUtils.copyProperties(dto, entity);
		entity.setSerialId(IdUtil.objectId());
		entity.setCreateTime(new Date());
		entity = tbConfigApplicationAliRepository.saveAndFlush(entity);
		// 重置应用可用的支付宝配置
		appAliCacheService.resetAppAvailableAliConfig(dto.getAppId());
		return entity;
	}

	/**
	 * 修改应用支付宝配置
	 * 
	 * @param dto
	 * @return
	 */
	@ServiceOper(desc = "修改应用支付宝配置")
	public TbConfigApplicationAli update(UpdateAppAliDto dto) {
		Optional<TbConfigApplicationAli> optional = tbConfigApplicationAliRepository.findById(dto.getSerialId());
		TbConfigApplicationAli entity = optional
				.orElseThrow(() -> new BizException(PayApiRetCodeEnum.RECORD_NOT_EXIST.code,
						PayApiRetCodeEnum.RECORD_NOT_EXIST.msg));
		BeanUtils.copyProperties(dto, entity);
		entity.setUpdateTime(new Date());
		entity = tbConfigApplicationAliRepository.saveAndFlush(entity);
		// 重置应用可用的支付宝配置
		appAliCacheService.resetAppAvailableAliConfig(dto.getAppId());
		return entity;
	}

	/**
	 * 删除应用支付宝配置
	 * 
	 * @param serialId
	 */
	@ServiceOper(desc = "删除应用支付宝配置")
	public void delete(String serialId) {
		Optional<TbConfigApplicationAli> optional = tbConfigApplicationAliRepository.findById(serialId);
		optional.ifPresent(t -> {
			tbConfigApplicationAliRepository.deleteById(serialId);
			// 重置应用可用的支付宝配置
			appAliCacheService.resetAppAvailableAliConfig(t.getAppId());
		});
	}

}
