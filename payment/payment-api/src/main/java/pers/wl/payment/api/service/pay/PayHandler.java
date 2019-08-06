/**
 * Copyright © 2017-2019 WL.All Rights Reserved.
 */
package pers.wl.payment.api.service.pay;

import com.cloud.comp.common.model.ResultModel;

import pers.wl.payment.api.service.pay.dto.PayOrderDto;

/**
 * 描述说明
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2019年8月5日 下午4:41:02
 * @since JDK 1.8
 */
public interface PayHandler {

	public void wapPay(PayOrderDto payOrderDto);

	public ResultModel<Object> appPay(PayOrderDto payOrderDto);
}
