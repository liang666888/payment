package pers.wl.payment.api.config.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.cloud.comp.common.dto.BaseRestDto;

import pers.wl.payment.api.utils.LogUtil;
import pers.wl.payment.api.utils.WebUtil;

/**
 * web请求AOP日志
 * 
 * @author wuliang
 * @version $Id: WebLogAspect.java, v 0.1 2018年11月4日 下午5:47:28 wuliang Exp $
 */
@Aspect
@Component
public class WebLogAspect {

	private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

	// spring的默认bean的scope是singleton,每次请求使用的aspect都是同一个实例
	private ThreadLocal<Long> startTime = new ThreadLocal<Long>();

	@Pointcut("execution(public * com.qqgy.core.controller..*Controller.*(..))")
	private void weblog() {
	}

	@Before("weblog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		// 记录请求开始时间
		startTime.set(System.currentTimeMillis());
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		LogUtil.info(logger, "处理web请求", "URL:" + request.getRequestURL().toString(),
				"HTTP_METHOD:" + request.getMethod(), "IP:" + WebUtil.getIpAddress(request));
		String queryString = request.getQueryString();
		// 经过验证，这里@RequestBody注解的切面会先生效，获取到的args为已经做过json转化的请求参数对象
		Object[] args = joinPoint.getArgs();
		String params = "";
		// 获取请求参数集合并进行遍历拼接
		if (args.length > 0) {
			if ("POST".equalsIgnoreCase(request.getMethod())) {
				try {
					params = JSON.toJSONString(args[0]);
				} catch (Exception e) {
					LogUtil.info(logger, "请求参数JSON解析异常");
				}
			} else if ("GET".equalsIgnoreCase(request.getMethod())) {
				params = queryString;
			}
		}
		LogUtil.info(logger, "请求报文:" + params);
		if (args.length > 0 && "POST".equalsIgnoreCase(request.getMethod())) {
			if (args[0] instanceof BaseRestDto) { // 请求参数逻辑校验
				BaseRestDto r = (BaseRestDto) args[0];
				r.validateLogic();
			}
		}
	}

	@AfterReturning(returning = "ret", pointcut = "weblog()")
	public void doAfterReturning(Object ret) throws Throwable {
		// 接口响应日志
		LogUtil.info(logger, "请求耗时:" + (System.currentTimeMillis() - startTime.get()) + "ms",
				"请求响应报文：" + JSON.toJSONString(ret));
	}

}
