package com.desafio.sbf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SbfApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbfApiApplication.class, args);
	}

}
