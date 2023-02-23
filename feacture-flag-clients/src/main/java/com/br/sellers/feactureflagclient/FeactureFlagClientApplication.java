package com.br.sellers.feactureflagclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FeactureFlagClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeactureFlagClientApplication.class, args);
	}

}
