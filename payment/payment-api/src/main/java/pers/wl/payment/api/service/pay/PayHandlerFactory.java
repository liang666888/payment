/**
 * Copyright © 2017-2019 WL.All Rights Reserved.
 */
package pers.wl.payment.api.service.pay;

import pers.wl.payment.api.common.enums.PayTypeEnum;
import pers.wl.payment.api.service.pay.handler.AliPayHandler;
import pers.wl.payment.api.service.pay.handler.WxPayHandler;
import pers.wl.payment.api.utils.SpringBeanUtil;

/**
 * 描述说明
 * 支付处理工厂类
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2019年8月5日 下午4:49:05
 * @since JDK 1.8
 */
public class PayHandlerFactory {

	public static PayHandler getPayHandler(PayTypeEnum payTypeEnum) {
		PayHandler payHandler = null;
		switch (payTypeEnum.handler) {
		case "ali":
			payHandler = SpringBeanUtil.getBean(AliPayHandler.class);
			break;
		case "wx":
			payHandler = SpringBeanUtil.getBean(WxPayHandler.class);
			break;
		default:
			break;
		}
		return payHandler;
	}
}
