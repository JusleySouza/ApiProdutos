package com.ju.springweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableCaching
@OpenAPIDefinition(info = @Info(title = "Product API", version = "1.0", description = "This API is for Products"))
public class ProductrestapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductrestapiApplication.class, args);
	}

}
