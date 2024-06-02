package com.turkcell.accountService;
import com.turkcell.commonpackage.utils.constants.Paths;
import com.turkcell.corepackage.annotations.EnableSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication(scanBasePackages = {Paths.ConfigurationBasePackage, Paths.Account.ServiceBasePackage})
@EnableFeignClients
@EnableJpaAuditing
@EnableSecurity
public class AccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}

}
