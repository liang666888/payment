/**
 * Copyright © 2017-2019 WL.All Rights Reserved.
 */
package pers.wl.payment.core.service.pay.handler;

import pers.wl.payment.core.api.client.pay.dto.PayOrderDto;

/**
 * 描述说明 h5/网页支付处理类
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2019年8月5日 下午4:41:02
 * @since JDK 1.8
 */
public interface WapPayHandler {

	public void wapPay(PayOrderDto payOrderDto);

}
