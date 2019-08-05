/**
 * Copyright © 2017-2019 WL.All Rights Reserved.
 */
package pers.wl.payment.api.service.config;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.comp.common.exceptions.BizException;

import cn.hutool.core.util.IdUtil;
import pers.wl.payment.api.common.annotations.ServiceOper;
import pers.wl.payment.api.common.enums.PayApiRetCodeEnum;
import pers.wl.payment.api.entity.TbConfigApplicationAli;
import pers.wl.payment.api.repository.TbConfigApplicationAliRepository;
import pers.wl.payment.api.service.config.dto.AddAppAliDto;
import pers.wl.payment.api.service.config.dto.UpdateAppAliDto;

/**
 * 描述说明
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2019年8月5日 上午11:34:43
 * @since JDK 1.8
 */
@Service
public class TbConfigApplicationAliService {

	@Autowired
	private TbConfigApplicationAliRepository tbConfigApplicationAliRepository;

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
		return tbConfigApplicationAliRepository.saveAndFlush(entity);
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
		return tbConfigApplicationAliRepository.saveAndFlush(entity);
	}

	/**
	 * 删除应用支付宝配置
	 * 
	 * @param serialId
	 */
	@ServiceOper(desc = "删除应用支付宝配置")
	public void delete(String serialId) {
		tbConfigApplicationAliRepository.deleteById(serialId);
	}

}
