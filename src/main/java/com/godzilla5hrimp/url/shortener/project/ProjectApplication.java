package com.godzilla5hrimp.url.shortener.project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class ProjectApplication {

	private static Logger logger = LoggerFactory.getLogger(ProjectApplication.class);

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		SpringApplication.run(ProjectApplication.class, args);
	}

}
