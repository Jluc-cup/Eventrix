package com.eventrix.base.swagger;

import com.eventrix.base.swagger.annotation.SwaggerDoc;
import com.eventrix.base.swagger.annotation.TaskTopicSwaggerDoc;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;

@Configuration
public class OpenApiCustomizerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Eventrix API V1")
                        .version("1.0.0")
                        .description("API for managing Eventrix api"))
                .addServersItem(new Server().url("http://localhost:8080")
                        .description("Local Development Server"));
    }

    @Bean
    public OperationCustomizer customizeOperation() {
        return (operation, handlerMethod) -> {
            final Method method = handlerMethod.getMethod();
            Arrays.stream(method.getAnnotations())
                    .filter(annotation -> Arrays.asList(annotation.annotationType()
                            .getInterfaces()).contains(SwaggerDoc.class))
                    .forEach(annotation -> configureOperation(operation, (SwaggerDoc) annotation));
            return operation;
        };
    }

    private void configureOperation(Operation operation, SwaggerDoc swaggerDoc) {
        if (swaggerDoc == null) return;
        operation.setSummary(swaggerDoc.summary());
        updateOperationDetails(operation, swaggerDoc);
    }

    private void updateOperationDetails(Operation operation, SwaggerDoc swaggerDoc) {

        final ApiResponses apiResponses = operation.getResponses();
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
        final String existingDescription = operation.getDescription() != null ? operation.getDescription() : "";
        operation.setDescription(existingDescription + (existingDescription.isEmpty() ? "" : " | ") +
                swaggerDoc.description() + " (Documented on " + LocalDateTime.now() + ")");
    }
}