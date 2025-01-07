package com.evualuation.globallogic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.annotation.Validated;

@SpringBootApplication
@Validated
public class GloballogicApplication {

	public static void main(String[] args) {
		SpringApplication.run(GloballogicApplication.class, args);
	}

}
