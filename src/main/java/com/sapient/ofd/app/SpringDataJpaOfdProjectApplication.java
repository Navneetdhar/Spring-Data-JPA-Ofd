package com.sapient.ofd.app;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.function.Predicate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.sapient.ofd.aspect.LoggingAspect;

import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@SpringBootApplication(scanBasePackages = "com.sapient")
@EntityScan(basePackages = "com.sapient.ofd.entity")
@EnableJpaRepositories(basePackages = "com.sapient.ofd.dao")
@EnableAspectJAutoProxy
@EnableJpaAuditing
@EnableOpenApi
public class SpringDataJpaOfdProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaOfdProjectApplication.class, args);
	}
	
	
	// not neccessary as @Component is applied before LoggingAspect class
//	@Bean
//	public LoggingAspect loggingAccept() {
//		return new LoggingAspect();
//	}
	
	@Bean
	  public Docket openApiPetStore() {
	    return new Docket(DocumentationType.OAS_30)
	        .groupName("open-api-online-food-delivery")
	        .select()
	        .paths(notePaths())
	        .build();
	  }

	  private Predicate<String> notePaths() {
	    return regex(".*/v1/.*");
	  }
}
