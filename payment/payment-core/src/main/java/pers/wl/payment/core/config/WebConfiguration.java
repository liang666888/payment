package pers.wl.payment.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 
 * 描述说明
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2019年4月20日 下午1:32:24
 * @since JDK 1.8
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		// setUseSuffixPatternMatch设置为false，强校验访问后缀
		// 之前若不设置setUseSuffixPatternMatch，访问 /login ，
		// 但是通过 /login.do /login.action /login.json 都能访问
		configurer.setUseSuffixPatternMatch(false);
		configurer.setUseTrailingSlashMatch(true);
	}

}
