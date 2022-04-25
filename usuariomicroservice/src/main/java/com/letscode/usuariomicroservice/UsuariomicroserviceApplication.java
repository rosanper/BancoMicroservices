package com.letscode.usuariomicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UsuariomicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsuariomicroserviceApplication.class, args);
	}

}
