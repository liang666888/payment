/**
 * Copyright © 2017-2018 WL.All Rights Reserved.
 */
package pers.wl.payment.api.common.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 描述说明 业务操作枚举
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2018年12月14日 上午10:36:16
 * @since JDK 1.8
 */
public enum ServiceOperEnum {

	/** 未知操作 */
	UNKNOW("UNKNOW", "未知操作");

	public String code;

	public String desc;

	private ServiceOperEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static ServiceOperEnum getByCode(String code) {
		for (ServiceOperEnum operEnum : values()) {
			if (StringUtils.equals(code, operEnum.code)) {
				return operEnum;
			}
		}
		return null;
	}

}
