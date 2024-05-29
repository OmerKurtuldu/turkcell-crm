package com.turkcell.basketService;

import com.turkcell.commonpackage.utils.constants.Paths;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication(scanBasePackages = {Paths.ConfigurationBasePackage,Paths.Basket.ServiceBasePackage})
@EnableFeignClients
public class BasketServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasketServiceApplication.class, args);
	}

}
