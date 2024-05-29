package com.turkcell.orderService;

import com.turkcell.commonpackage.utils.constants.Paths;
import com.turkcell.corepackage.annotations.EnableSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages = {Paths.ConfigurationBasePackage, Paths.Order.ServiceBasePackage})
@EnableJpaAuditing
@EnableSecurity()
@EnableFeignClients
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

}
