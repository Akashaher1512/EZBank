package com.akash.accounts;

import com.akash.accounts.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/*
@ComponentScans({@ComponentScan(com.akash.controller)})
@EnableJpaRepositories("com.akash.repository")
@EntityScan("com.akash.entity")

 */
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {AccountsContactInfoDto.class})
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts Microservice REST API Documentation",
				description = "EZBank Accounts Microservice REST API Documentation",
				version = "V1",
				contact = @Contact(
						name = "Akash Aher",
						email = "akash.aher@neutrinotechlabs.com",
						url = "www.neutrinotechlabs.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "www.neutrinotechlabs.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "EZBank Accounts Microservice REST API Documentation",
				url = "www.neutrinotechlabs.com"
		)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}


