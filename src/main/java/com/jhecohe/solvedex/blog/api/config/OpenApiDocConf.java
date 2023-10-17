package com.jhecohe.solvedex.blog.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiDocConf {

	@Bean
	public OpenAPI customOpenApi() {
		return new OpenAPI().info(new Info().title("Post API").version("1.0")
				.description("Api to handle posts with roles").termsOfService("http://termsOfService.com")
				.license(new License().name("Apache 2.0").url("http://rights.com")));
	}
}
