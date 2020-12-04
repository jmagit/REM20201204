package com.example.catalogo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import springfox.documentation.oas.annotations.EnableOpenApi;

//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//@EnableSwagger2

@EnableEurekaClient
@EnableOpenApi
@SpringBootApplication
public class CatalogoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogoApplication.class, args);
	}

}
