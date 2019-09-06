/**
 * Copyright © 2017-2019 WL.All Rights Reserved.
 */
package pers.wl.payment.core.api.client.config.dto;

import com.cloud.comp.common.dto.BaseRestDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 描述说明
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2019年8月5日 上午11:38:03
 * @since JDK 1.8
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AddAppAliDto extends BaseRestDto {

	/** */
	private static final long serialVersionUID = 9081334620594105600L;

	private String appId;

	private String configAliId;

	private String notifyUrl;

	private String returnUrl;

	private String stat;

	private int weight;

	/**
	 * @see com.cloud.comp.common.dto.BaseRestDto#validateLogic()
	 */
	@Override
	public void validateLogic() {
	}

}
