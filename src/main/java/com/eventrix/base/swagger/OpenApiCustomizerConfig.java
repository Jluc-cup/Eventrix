package com.eventrix.base.swagger;

import com.eventrix.base.swagger.annotation.TaskTopicSwaggerDoc;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiCustomizerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Task Topic API")
                        .version("1.0.0")
                        .description("API for managing task topics in a scheduler application"))
                .addServersItem(new Server().url("http://localhost:8080")
                        .description("Local Development Server"));
    }

    @Bean
    public OperationCustomizer customizeOperation() {
        return (operation, handlerMethod) -> {
            final TaskTopicSwaggerDoc swaggerDoc = handlerMethod.getMethodAnnotation(TaskTopicSwaggerDoc.class);
            if (swaggerDoc != null) {
                ApiResponses apiResponses = operation.getResponses();
                if (apiResponses != null) {
                    ApiResponse successResponse = apiResponses.get(swaggerDoc.responseCode());
                    if (successResponse != null) {
                        successResponse.setDescription("Operation completed with status " + swaggerDoc.responseCode());
                    }

                    for (String errorCode : swaggerDoc.errorCodes()) {
                        if (!apiResponses.containsKey(errorCode)) {
                            apiResponses.addApiResponse(errorCode, new ApiResponse().description("Error: " + errorCode));
                        }
                    }
                }
                operation.setDescription(swaggerDoc.description() + " (Documented on " + java.time.LocalDateTime.now() + ")");
            }
            return operation;
        };
    }
}