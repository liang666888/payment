/**
 * Copyright © 2017-2019 WL.All Rights Reserved.
 */
package pers.wl.payment.core.api.enums;

/**
 * 描述说明
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2019年7月24日 上午10:27:29
 * @since JDK 1.8
 */
public enum StatEnum {

	ENABLE("启用"),

	DISABLE("禁用");

	public String msg;

	private StatEnum(String msg) {
		this.msg = msg;
	}
}
