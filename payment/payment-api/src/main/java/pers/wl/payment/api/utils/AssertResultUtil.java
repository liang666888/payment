/**
 * Copyright © 2017-2018 WL.All Rights Reserved.
 */
package pers.wl.payment.api.utils;

import java.util.Map;

import org.springframework.util.Assert;

import com.cloud.comp.common.exceptions.BizException;

import pers.wl.payment.api.common.enums.PayApiRetCodeEnum;

/**
 * 描述说明 方法/接口请求结果合法性判断工具类
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2018年11月24日 下午2:11:47
 * @since JDK 1.8
 */
public class AssertResultUtil {

	public static void isNull(Object object, PayApiRetCodeEnum resultCode) {
		try {
			Assert.isNull(object, resultCode.msg);
		} catch (RuntimeException e) {
			throw new BizException(resultCode.code, resultCode.msg);
		}
	}

	public static void isNull(Object object, BizException bizException) {
		try {
			Assert.isNull(object, bizException.getMessage());
		} catch (RuntimeException e) {
			throw bizException;
		}
	}

	public static void notNull(Object object, PayApiRetCodeEnum resultCode) {
		try {
			Assert.notNull(object, resultCode.msg);
		} catch (RuntimeException e) {
			throw new BizException(resultCode.code, resultCode.msg);
		}
	}

	public static void notBlank(String string, PayApiRetCodeEnum resultCode) {
		try {
			Assert.hasText(string, resultCode.msg);
		} catch (RuntimeException e) {
			throw new BizException(resultCode.code, resultCode.msg);
		}
	}

	public static void notEmpty(Map<String, String> map, PayApiRetCodeEnum resultCode) {
		try {
			Assert.notEmpty(map, resultCode.msg);
		} catch (RuntimeException e) {
			throw new BizException(resultCode.code, resultCode.msg);
		}
	}

	public static void isTrue(boolean boolValue, PayApiRetCodeEnum resultCode) {
		try {
			Assert.isTrue(boolValue, resultCode.msg);
		} catch (RuntimeException e) {
			throw new BizException(resultCode.code, resultCode.msg);
		}
	}

	public static void isTrue(boolean boolValue, PayApiRetCodeEnum resultCode, String detail) {
		try {
			Assert.isTrue(boolValue, resultCode.msg);
		} catch (RuntimeException e) {
			throw new BizException(resultCode.code, resultCode.msg, detail);
		}
	}

	public static void isTrue(boolean boolValue, BizException bizException) {
		try {
			Assert.isTrue(boolValue, bizException.getMessage());
		} catch (RuntimeException e) {
			throw bizException;
		}
	}

	public static void equals(String actualResult, String expectResult, PayApiRetCodeEnum resultCode) {
		if (null == actualResult && null != expectResult) {
			throw new BizException(resultCode.code, resultCode.msg);
		}

		try {
			if (null != actualResult) {
				Assert.isTrue(actualResult.equals(expectResult), resultCode.msg);
			}
		} catch (RuntimeException e) {
			throw new BizException(resultCode.code, resultCode.msg);
		}
	}

	public static void equals(String actualResult, String expectResult, BizException bizException) {
		if (null == actualResult && null != expectResult) {
			throw bizException;
		}
		try {
			if (null != actualResult) {
				Assert.isTrue(actualResult.equals(expectResult), bizException.getMessage());
			}
		} catch (RuntimeException e) {
			throw bizException;
		}
	}

	public static void bigger(int actualResult, int expectResult, PayApiRetCodeEnum resultCode) {
		if (actualResult < expectResult) {
			throw new BizException(resultCode.code, resultCode.msg);
		}
	}

	public static void bigger(int actualResult, int expectResult, BizException bizException) {
		if (actualResult < expectResult) {
			throw bizException;
		}
	}

}
