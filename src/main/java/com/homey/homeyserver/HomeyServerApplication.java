package com.homey.homeyserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HomeyServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeyServerApplication.class, args);
	}

}
