/**
 * Copyright © 2017-2019 WL.All Rights Reserved.
 */
package pers.wl.payment.api.service.pay.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.cloud.comp.common.dto.BaseRestDto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pers.wl.payment.api.common.enums.PayTypeEnum;

/**
 * 描述说明 支付申请请求参数
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2019年8月5日 下午3:39:33
 * @since JDK 1.8
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PayOrderDto extends BaseRestDto {

	/** */
	private static final long serialVersionUID = 948296421530423064L;

	/**
	 * 应用唯一标识（支付中心配置）
	 */
	@NotBlank(message = "appId can not be null")
	private String appId;

	/**
	 * 支付方式
	 */
	@NotNull(message = "payType can not be null")
	private PayTypeEnum payType;

	/**
	 * 对一笔交易的具体描述信息
	 */
	private String memo;

	/**
	 * 商品的标题
	 */
	private String subject;

	/**
	 * 外部订单号(商户网站唯一订单号)
	 */
	@NotBlank(message = "orderno can not be null")
	private String orderno;

	/**
	 * 订单总金额，单位为元，精确到小数点后两位
	 */
	@NotBlank(message = "amount can not be null")
	@Pattern(regexp = "^[0-9]{0,}[.]{0,1}[0-9]{0,2}$", message = "订单金额格式不正确")
    @Length(max = 14, message = "金额超过限制")
	private String amount;

	/**
	 * @see com.cloud.comp.common.dto.BaseRestDto#validateLogic()
	 */
	@Override
	public void validateLogic() {
	}

}
