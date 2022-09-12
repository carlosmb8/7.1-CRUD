package com.example.ej2crudjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class Ej2CrudJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ej2CrudJpaApplication.class, args);
	}


}
