/**
 * Copyright © 2017-2019 WL.All Rights Reserved.
 */
package pers.wl.payment.api.common.enums;

/** 
 * 描述说明
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2019年4月10日 下午5:44:09
 * @since JDK 1.8
 */
public enum PayApiRetCodeEnum {
	SUCCESS("0000","成功"),
	/**业务异常*/
	REQUEST_ILLEGAL("QC0001", "请求报文非法"),
	PARAM_ILLEGAL("QC0002", "参数校验未通过"),
	
	
	/**系统类错误*/
	SYSTEM_TIMEOUT("QC9001", "服务调用超时"),
	DUPLICATE_KEY_ERROR("QC9002","唯一性约束冲突"),
	SYSTEM_ERROR("QC9999", "系统错误");
	
	
	public final String code;
	
	public final String msg;
	
	private PayApiRetCodeEnum(String code,String msg) {
		this.code = code;
		this.msg = msg;
	}
}
