package com.bridgelabz.fundoonotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import io.swagger.annotations.SwaggerDefinition;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@SpringBootApplication
@EnableSwagger2
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
