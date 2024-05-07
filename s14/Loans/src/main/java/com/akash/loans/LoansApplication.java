package com.akash.loans;

import com.akash.loans.dto.LoansContactsInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties({LoansContactsInfoDto.class})
@OpenAPIDefinition(
		info = @Info(
				title = "Loan Microservice REST API Documentation",
				description = "EzBank Loan Microservice REST API Documentation",
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
				description = "EZBank Loan Microservice REST API Documentation",
				url = "www.neutrinotechlabs.com"
		)
)
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

}
