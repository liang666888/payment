/**
 * Copyright © 2017-2019 WL.All Rights Reserved.
 */
package pers.wl.payment.core.service.pay.handler;

import pers.wl.payment.core.api.enums.PayTypeEnum;
import pers.wl.payment.core.service.pay.handler.ali.AliPayAppHandler;
import pers.wl.payment.core.service.pay.handler.ali.AliPayPcDirectHandler;
import pers.wl.payment.core.service.pay.handler.ali.AliPayQrHandler;
import pers.wl.payment.core.service.pay.handler.ali.AliPayWapHandler;
import pers.wl.payment.core.utils.SpringBeanUtil;

/**
 * 描述说明 支付处理工厂类
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2019年8月5日 下午4:49:05
 * @since JDK 1.8
 */
public class PayHandlerFactory {

	/**
	 * 获取wap支付方式处理类
	 * 
	 * @param payTypeEnum
	 * @return
	 */
	public static WapPayHandler getWapPayHandler(PayTypeEnum payTypeEnum) {
		WapPayHandler wapPayHandler = null;
		switch (payTypeEnum) {
		case ALIPAY_PC_DIRECT:
			wapPayHandler = SpringBeanUtil.getBean(AliPayPcDirectHandler.class);
			break;
		case ALIPAY_WAP:
			wapPayHandler = SpringBeanUtil.getBean(AliPayWapHandler.class);
			break;
		case ALIPAY_QR:
			wapPayHandler = SpringBeanUtil.getBean(AliPayQrHandler.class);
			break;
		default:
			break;
		}
		return wapPayHandler;
	}

	/**
	 * 获取app方式处理类
	 * 
	 * @param payTypeEnum
	 * @return
	 */
	public static AppPayHandler getAppPayHandler(PayTypeEnum payTypeEnum) {
		AppPayHandler appPayHandler = null;
		switch (payTypeEnum) {
		case ALIPAY_APP:
			appPayHandler = SpringBeanUtil.getBean(AliPayAppHandler.class);
			break;
		default:
			break;
		}
		return appPayHandler;
	}
}
