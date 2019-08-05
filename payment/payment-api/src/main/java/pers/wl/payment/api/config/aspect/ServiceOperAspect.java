/**
 * Copyright © 2017-2018 WL.All Rights Reserved.
 */
package pers.wl.payment.api.config.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.cloud.comp.common.exceptions.BizException;

import pers.wl.payment.api.common.annotations.ServiceOper;
import pers.wl.payment.api.common.enums.PayApiRetCodeEnum;
import pers.wl.payment.api.utils.LogUtil;

/**
 * 描述说明 业务操作日志记录切面
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2018年12月14日 上午10:41:45
 * @since JDK 1.8
 */
@Aspect
@Component
public class ServiceOperAspect {

	private static final Logger logger = LoggerFactory.getLogger(ServiceOperAspect.class);

	@Pointcut("@annotation(serviceOper)")
	private void pointCutMethod(ServiceOper serviceOper) {
	}

	/**
	 * 业务操作
	 * 
	 * @param pjp
	 * @return
	 */
	@Around("pointCutMethod(serviceOper)")
	public Object doAround(ProceedingJoinPoint pjp, ServiceOper serviceOper) {
		Object object = null;
		long startTime = System.currentTimeMillis();
		boolean excuteSuccess = false;
		try {
			// 执行方法
			object = pjp.proceed();
			excuteSuccess = true;
		} catch (ConstraintViolationException exception) {
			LogUtil.error(logger, serviceOper.desc() + "数据库操作异常", exception);
			throw new BizException(PayApiRetCodeEnum.SYSTEM_ERROR.code, PayApiRetCodeEnum.SYSTEM_ERROR.msg);
		} catch (DataIntegrityViolationException exception) {
			LogUtil.warn(logger, serviceOper.desc() + "唯一性约束冲突", exception.getMessage());
			throw new BizException(PayApiRetCodeEnum.SYSTEM_ERROR.code, PayApiRetCodeEnum.SYSTEM_ERROR.msg);
		} catch (BizException exception) {
			LogUtil.info(logger, serviceOper.desc() + "业务异常：", exception.getCode(), exception.getMessage());
			throw exception;
		} catch (Exception exception) {
			LogUtil.error(logger, serviceOper.desc() + "执行异常：", exception);
			throw new BizException(PayApiRetCodeEnum.SYSTEM_ERROR.code, PayApiRetCodeEnum.SYSTEM_ERROR.msg);
		} catch (Throwable exception) {
			LogUtil.error(serviceOper.desc() + "执行异常：", exception);
			throw new BizException(PayApiRetCodeEnum.SYSTEM_ERROR.code, PayApiRetCodeEnum.SYSTEM_ERROR.msg);
		} finally {
			// 记录操作时间
			long endTime = System.currentTimeMillis();
			LogUtil.info(logger, serviceOper.desc(), "执行时间：", String.valueOf(endTime - startTime) + "ms",
					String.valueOf(excuteSuccess));
		}
		return object;
	}
}
