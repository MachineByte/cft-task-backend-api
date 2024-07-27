package ru.cft.task_backend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Test CFT task",
                version = "1.0.0",
                contact = @Contact(
                        name = "Mihail Belyanov",
                        email = "mihail.belyanov@gmail.com"
                )
        )
)
public class OpenApiConfig {

}