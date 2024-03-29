/**
 * Copyright © 2017-2018 WL.All Rights Reserved.
 */
package pers.wl.payment.core.common.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * 描述说明
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2018年12月14日 上午10:46:55
 * @since JDK 1.8
 */
@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface ServiceOper {

	/**
	 * 默认
	 * 
	 * @return
	 */
	public String desc() default "";
}
