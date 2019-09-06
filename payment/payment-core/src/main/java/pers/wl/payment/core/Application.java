package pers.wl.payment.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.cloud.comp.AutoconfigPackage;
import com.spring4all.swagger.EnableSwagger2Doc;

/**
 * 
 * 描述说明 项目启动类
 * 
 * @version V1.0
 * @author wuliang@https://github.com/liang666888
 * @Date 2019年4月10日 下午9:37:52
 * @since JDK 1.8
 */
@EnableSwagger2Doc
@EntityScan("pers.wl.payment.core.entity")
@SpringBootApplication(scanBasePackageClasses = { Application.class, AutoconfigPackage.class })
@EnableTransactionManagement // 开启注解事务管理
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}