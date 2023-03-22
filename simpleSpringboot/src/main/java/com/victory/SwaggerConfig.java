package com.victory;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Bean
	OpenAPI openAPI() {
	   Info info = new Info()
	           .version("v1.0.0")
	           .title("test API")
	           .description("test swagger");

	   return new OpenAPI()
	   		.info(info);
	}

	@Bean
	GroupedOpenApi authApi() {
	 return GroupedOpenApi.builder()
			 .group("Auth API v1.0.0")
			 .packagesToScan("com.victory")
			 .build();
	}
}
