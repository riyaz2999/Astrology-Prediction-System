package com.kelf.Rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReCaptchaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReCaptchaApplication.class, args);
		System.out.println("App Started");
	}

}
