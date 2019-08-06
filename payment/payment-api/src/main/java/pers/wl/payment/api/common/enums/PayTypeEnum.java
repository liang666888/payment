/**
 * Copyright © 2017-2019 WL.All Rights Reserved.
 */
package pers.wl.payment.api.common.enums;

/**
 * 描述说明
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2019年5月20日 下午5:10:37
 * @since JDK 1.8
 */
public enum PayTypeEnum {

	ALIPAY_APP("支付宝APP"), 
	ALIPAY_WAP("支付宝手机网页"), 
	ALIPAY_PC_DIRECT("支付宝即时到账"), 
	ALIPAY_QR("支付宝当面付");

	/**
	 * 描述
	 */
	public String desc;

	private PayTypeEnum(String desc) {
		this.desc = desc;
	}

}
