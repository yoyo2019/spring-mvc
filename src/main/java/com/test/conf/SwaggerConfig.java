/*
 * Created by zhangzxiang91@gmail.com on 2019/04/28.
 */
package com.test.conf;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2配置类
 * 通过@Configuration注解，让Spring来加载该类配置。
 * 再通过@EnableSwagger2注解来启用Swagger2。
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private static final String API_INFO_TITLE   = "API接口文档";
	private static final String API_BASE_PACKAGE = "com.test.controller";

	@Bean
	public Docket createRestApi() {
		ApiInfo apiInfo = new ApiInfoBuilder().title(API_INFO_TITLE).version("1.0").build();

		return new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(false)//
						  .apiInfo(apiInfo).select()//
						  .apis(RequestHandlerSelectors.basePackage(API_BASE_PACKAGE))//
						  .paths(PathSelectors.any())// .paths(Predicates.or(Predicates.containsPattern("/*")))
						  .paths(Predicates.not(PathSelectors.regex("/web_error")))//
						  .build();
	}
}
