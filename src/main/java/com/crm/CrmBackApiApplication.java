package com.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableWebMvc
public class CrmBackApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrmBackApiApplication.class, args);
	}

}
