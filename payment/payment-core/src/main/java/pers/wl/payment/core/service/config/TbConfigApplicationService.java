/**
 * Copyright © 2017-2019 WL.All Rights Reserved.
 */
package pers.wl.payment.core.service.config;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.cloud.comp.common.exceptions.BizException;

import cn.hutool.core.util.IdUtil;
import pers.wl.payment.core.api.client.config.dto.AddConfigAppDto;
import pers.wl.payment.core.api.client.config.dto.UpdateConfigAppDto;
import pers.wl.payment.core.api.enums.PayApiRetCodeEnum;
import pers.wl.payment.core.common.annotations.ServiceOper;
import pers.wl.payment.core.entity.TbConfigApplication;
import pers.wl.payment.core.repository.TbConfigApplicationRepository;

/**
 * 描述说明
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2019年8月5日 上午11:33:56
 * @since JDK 1.8
 */
@CacheConfig(cacheNames = "TbConfigApplicationService")
@Service
public class TbConfigApplicationService {

	@Autowired
	private TbConfigApplicationRepository tbConfigApplicationRepository;

	/**
	 * 新增应用
	 * 
	 * @param dto
	 * @return
	 */
	@ServiceOper(desc = "新增应用")
	public TbConfigApplication add(AddConfigAppDto dto) {
		TbConfigApplication entity = new TbConfigApplication();
		BeanUtils.copyProperties(dto, entity);
		entity.setAppId(IdUtil.objectId());
		entity.setCreateTime(new Date());
		return tbConfigApplicationRepository.saveAndFlush(entity);
	}

	/**
	 * 修改应用信息
	 * 
	 * @param dto
	 * @return
	 */
	@CachePut(key = "#p0.appId")
	@Modifying
	@ServiceOper(desc = "修改应用")
	public TbConfigApplication update(UpdateConfigAppDto dto) {
		Optional<TbConfigApplication> optional = tbConfigApplicationRepository.findById(dto.getAppId());
		TbConfigApplication entity = optional
				.orElseThrow(() -> new BizException(PayApiRetCodeEnum.RECORD_NOT_EXIST.code,
						PayApiRetCodeEnum.RECORD_NOT_EXIST.msg));
		BeanUtils.copyProperties(dto, entity);
		entity.setUpdateTime(new Date());
		return tbConfigApplicationRepository.saveAndFlush(entity);
	}

	/**
	 * 删除应用
	 * 
	 * @param appId
	 */
	@CacheEvict(key = "#p0")
	@Modifying
	@ServiceOper(desc = "删除应用")
	public void delete(String appId) {
		tbConfigApplicationRepository.deleteById(appId);
	}

	/**
	 * 根据appId查询应用
	 * 
	 * @param appId
	 * @return
	 */
	@Cacheable(key = "#p0")
	@ServiceOper(desc = "根据appId查询应用")
	public TbConfigApplication queryByAppId(String appId) {
		Optional<TbConfigApplication> optional = tbConfigApplicationRepository.findById(appId);
		TbConfigApplication entity = optional.orElse(null);
		return entity;
	}
}
