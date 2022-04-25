package com.letscode.contamicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ContamicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContamicroserviceApplication.class, args);
	}

}
