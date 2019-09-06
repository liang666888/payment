/**
 * Copyright © 2017-2018 WL.All Rights Reserved.
 */
package pers.wl.payment.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述说明 日志工具类
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2018年11月24日 下午2:26:41
 * @since JDK 1.8
 */
public class LogUtil {
	
	private static Logger logger = LoggerFactory.getLogger(LogUtil.class);
	
	public static void debug(String message) {
        if (logger.isDebugEnabled()) {
            logger.debug("{},{}", ContextHolder.getCorrelationID(), message);
        }
    }

    public static void debug(String message, String... strings) {
        if (logger.isDebugEnabled()) {
            logger.debug("{},{}", ContextHolder.getCorrelationID(), message, strings);
        }
    }

    public static void debug(Logger logger, String message) {
        if (logger.isDebugEnabled()) {
            logger.debug("{},{}", ContextHolder.getCorrelationID(), message);
        }
    }

    public static void debug(Logger logger, String message, String... strings) {
        if (logger.isDebugEnabled()) {
            logger.debug("{},{},{}", ContextHolder.getCorrelationID(), message, strings);
        }
    }

    public static void info(String message) {
        logger.info("{},{}", ContextHolder.getCorrelationID(), message);
    }

    public static void info(String message, String... strings) {
        logger.info("{},{},{}", ContextHolder.getCorrelationID(), message, strings);
    }

    public static void info(Logger logger, String message) {
        logger.info("{},{}", ContextHolder.getCorrelationID(), message);
    }

    public static void info(Logger logger, String message, String... strings) {
        logger.info("{},{},{}", ContextHolder.getCorrelationID(), message, strings);
    }

    public static void warn(String message) {
        logger.warn("{},{}", ContextHolder.getCorrelationID(), message);
    }

    public static void warn(String message, String... strings) {
        logger.warn("{},{},{}", ContextHolder.getCorrelationID(), message, strings);
    }

    public static void warn(Logger logger, String message) {
        logger.warn("{},{}", ContextHolder.getCorrelationID(), message);
    }

    public static void warn(Logger logger, String message, String... strings) {
        logger.warn("{},{},{}", ContextHolder.getCorrelationID(), message, strings);
    }

    public static void error(String message) {
        logger.error("{},{}", ContextHolder.getCorrelationID(), message);
    }

    public static void error(String message, Exception exception) {
        logger.error("{},{}", ContextHolder.getCorrelationID(), message, exception);
    }

    public static void error(String message, Throwable exception) {
        logger.error("{},{}", ContextHolder.getCorrelationID(), message, exception);
    }

    public static void error(String message, String... strings) {
        logger.error("{},{},{}", ContextHolder.getCorrelationID(), message, strings);
    }

    public static void error(Logger logger, String message) {
        logger.error("{},{}", ContextHolder.getCorrelationID(), message);
    }

    public static void error(Logger logger, String message, Exception exception) {
        logger.error("{},{}", ContextHolder.getCorrelationID(), message, exception);
    }

    public static void error(Logger logger, String message, String... strings) {
        logger.error("{},{},{}", ContextHolder.getCorrelationID(), message, strings);
    }
}
