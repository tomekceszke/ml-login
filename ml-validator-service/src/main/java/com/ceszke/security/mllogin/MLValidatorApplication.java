package com.ceszke.security.mllogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.ceszke.security.mllogin.client"})
public class MLValidatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MLValidatorApplication.class, args);
	}
}
