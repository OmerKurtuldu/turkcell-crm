package com.turkcell.basketService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.nio.file.Paths;

@SpringBootApplication(scanBasePackages = {Paths.ConfigurationBasePackage,Paths.Basket.ServiceBasePackage})
@EnableDiscoveryClient
public class BasketServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasketServiceApplication.class, args);
	}

}
