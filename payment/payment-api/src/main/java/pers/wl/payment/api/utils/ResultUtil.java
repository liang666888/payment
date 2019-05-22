package pers.wl.payment.api.utils;

import com.cloud.comp.common.model.ResultModel;

import pers.wl.payment.api.common.enums.PayApiRetCodeEnum;

/**
 * rest接口统一返回工具类
 * 
 * @author wuliang
 * @version $Id: ResultUtil.java, v 0.1 2018年11月4日 下午4:16:16 wuliang Exp $
 */
public class ResultUtil {

	/**
	 * 成功返回
	 * 
	 * @return
	 */
	public static <T> ResultModel<T> success() {
		return new ResultModel<T>(PayApiRetCodeEnum.SUCCESS.code, PayApiRetCodeEnum.SUCCESS.msg);
	}

	/**
	 * 成功返回
	 * 
	 * @param data
	 *            返回数据
	 * @return
	 */
	public static <T> ResultModel<T> success(T data) {
		return new ResultModel<T>(PayApiRetCodeEnum.SUCCESS.code, PayApiRetCodeEnum.SUCCESS.msg, data);
	}

	/**
	 * 成功返回(分页)
	 * 
	 * @param data
	 *            返回数据
	 * @param count
	 *            总条数
	 * @return
	 */
	public static <T> ResultModel<T> success(T data, Long count) {
		ResultModel<T> ResultModel = new ResultModel<T>(PayApiRetCodeEnum.SUCCESS.code, PayApiRetCodeEnum.SUCCESS.msg,
				data);
		ResultModel.setCount(count);
		return ResultModel;
	}

	/**
	 * 错误返回
	 * 
	 * @param errorCodeEnum
	 *            错误枚举
	 * @return
	 */
	public static <T> ResultModel<T> error(PayApiRetCodeEnum errorCodeEnum) {
		return new ResultModel<T>(errorCodeEnum.code, errorCodeEnum.msg);
	}

	/**
	 * 错误返回
	 * 
	 * @param code
	 *            错误码
	 * @param msg
	 *            错误信息
	 * @return
	 */
	public static <T> ResultModel<T> error(String code, String msg) {
		return new ResultModel<T>(code, msg);
	}
}
