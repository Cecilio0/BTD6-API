package com.dread9182.BTD6API.config;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
		info = @Info(
				contact = @Contact(
						name = "Dread9182",
						email = "dread9182@gmail.com"
				),
				title = "BTD6 API",
				description = "BTD6 API for general game information",
				version = "0.1",
				license = @License(
						name = "MIT",
						url = "https://github.com/git/git-scm.com/blob/main/MIT-LICENSE.txt"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Documentation on github",
				url = "https://github.com/Dread9182/BTD6-API"
		),
		servers = {
				@Server(
						description = "Local dev ENV",
						url = "http://localhost:8080"
				),
				@Server(
						// todo define deployed server
				)
		}
)
// Defines the security to be used in the documentation, in this case a jwt bearer header
@SecurityScheme(
		name = "bearerAuth",
		description = "JWT auth description",
		scheme = "bearer",
		type = SecuritySchemeType.HTTP,
		bearerFormat = "JWT",
		in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {}