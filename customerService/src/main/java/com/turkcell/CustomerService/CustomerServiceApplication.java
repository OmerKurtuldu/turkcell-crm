package com.turkcell.CustomerService;

import com.turkcell.commonpackage.utils.constants.Paths;
import com.turkcell.corepackage.annotations.EnableSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages = {Paths.ConfigurationBasePackage,Paths.Customer.ServiceBasePackage})
@EnableJpaAuditing
@EnableSecurity
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

}
