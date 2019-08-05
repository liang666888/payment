/**
 * Copyright © 2017-2019 WL.All Rights Reserved.
 */
package pers.wl.payment.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import pers.wl.payment.api.entity.TbConfigApplication;

/**
 * 描述说明
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2019年4月21日 下午6:48:24
 * @since JDK 1.8
 */
public interface TbConfigApplicationRepository
		extends JpaRepository<TbConfigApplication, String>, JpaSpecificationExecutor<TbConfigApplication> {

}
