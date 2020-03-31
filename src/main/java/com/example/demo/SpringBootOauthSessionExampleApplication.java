package com.example.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages="com.example.*")
public class SpringBootOauthSessionExampleApplication {

	private static final Log LOGGER = LogFactory.getLog(SpringBootOauthSessionExampleApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootOauthSessionExampleApplication.class, args);
	}

	@Bean
	ApplicationRunner startup() {
		return args -> {
			LOGGER.info("=========== SPRING OAUTH SESSION ==============");
		};
	}
	
}
