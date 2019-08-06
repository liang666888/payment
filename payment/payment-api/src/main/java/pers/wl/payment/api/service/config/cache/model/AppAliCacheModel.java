/**
 * Copyright © 2017-2019 WL.All Rights Reserved.
 */
package pers.wl.payment.api.service.config.cache.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 描述说明
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2019年8月6日 上午10:33:45
 * @since JDK 1.8
 */
@Data
@AllArgsConstructor
public class AppAliCacheModel implements Serializable {

	/** */
	private static final long serialVersionUID = -6611224595578092443L;

	private String serialId;

	private String appId;

	private String configAliId;

	private String notifyUrl;

	private String returnUrl;

	private int weight;

	private String aliAppId;

	private String partner;

	private String privateKey;

	private String sellerId;

	private String signType;

	private String stat;

}
