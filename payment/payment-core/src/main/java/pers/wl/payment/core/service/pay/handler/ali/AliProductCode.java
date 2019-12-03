/**
 * Copyright © 2017-2019 WL.All Rights Reserved.
 */
package pers.wl.payment.core.service.pay.handler.ali;

import pers.wl.payment.core.api.enums.PayTypeEnum;
import pers.wl.payment.core.config.payconfig.AlipayConfig;

/**
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2019年12月2日 下午1:37:29
 * @since JDK 1.8
 */
public class AliProductCode {

	/**
	 * 获取支付宝支付对应的产品编码
	 * 
	 * @param payType
	 *            支付方式
	 * @return
	 */
	public static String getAliProductCode(PayTypeEnum payType) {
		String productCode = null;
		switch (payType) {
		case ALIPAY_APP:
			productCode = AlipayConfig.APP_PRODUCT_CODE;
			break;
		case ALIPAY_PC_DIRECT:
			productCode = AlipayConfig.DIRECT_PRODUCT_CODE;
			break;
		case ALIPAY_QR:
			productCode = AlipayConfig.QR_PRODUCT_CODE;
			break;
		case ALIPAY_WAP:
			productCode = AlipayConfig.WAP_PRODUCT_CODE;
			break;
		default:
			productCode = AlipayConfig.DIRECT_PRODUCT_CODE;
			break;
		}
		return productCode;
	}
}
