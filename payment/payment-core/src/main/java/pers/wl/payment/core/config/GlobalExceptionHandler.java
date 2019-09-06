package pers.wl.payment.core.config;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.comp.common.exceptions.BizException;
import com.cloud.comp.common.model.ResultModel;

import pers.wl.payment.core.api.enums.PayApiRetCodeEnum;
import pers.wl.payment.core.utils.LogUtil;

/**
 * web统一异常捕捉 使用@ControllerAdvice注解实现全局异常捕获 basePackages可添加多个{"A","B"}
 * 
 * @author wuliang
 * @version $Id: GlobalExceptionHandler.java, v 0.1 2018年11月2日 上午9:49:57 wuliang
 *          Exp $
 */
@ControllerAdvice(basePackages = { "pers.wl.payment.core.controller" })
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * 系统异常捕获
	 * 
	 * @param request
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResultModel<Object> exceptionHandler(HttpServletRequest request, Exception exception) {
		LogUtil.error(logger, exception.getMessage(), exception); // 系统错误日志，打印级别error
		return handleErrorInfo(request, PayApiRetCodeEnum.SYSTEM_ERROR.code, PayApiRetCodeEnum.SYSTEM_ERROR.msg, exception);
	}

	/**
	 * 业务异常捕获
	 * 
	 * @param request
	 * @return
	 */
	@ExceptionHandler(BizException.class)
	@ResponseBody
	public ResultModel<Object> bizExceptionHandler(HttpServletRequest request, BizException bizException) {
		return handleErrorInfo(request, bizException.getCode(), bizException.getMessage(), bizException);
	}

	/**
	 * 请求参数校验异常
	 * 
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ResultModel<Object> methodArgumentNotValidExceptionHandler(HttpServletRequest request,
			MethodArgumentNotValidException validException) {
		BindingResult bindingResult = validException.getBindingResult();
		String errorMesssage = "";
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			errorMesssage += fieldError.getDefaultMessage() + ",";
		}
		errorMesssage = errorMesssage.substring(0, errorMesssage.length() - 1);
		return handleErrorInfo(request, PayApiRetCodeEnum.PARAM_ILLEGAL.code, errorMesssage, validException);
	}

	/**
	 * 错误信息处理
	 * 
	 * @param request
	 * @param code
	 * @param message
	 * @param exception
	 * @return
	 */
	private ResultModel<Object> handleErrorInfo(HttpServletRequest request, String code, String message,
			Exception exception) {
		return new ResultModel<Object>(code, message);
	}

}
