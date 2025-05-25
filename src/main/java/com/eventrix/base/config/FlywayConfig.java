package com.eventrix.base.config;

import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class FlywayConfig {

    private final DataSource dataSource;

    private final Environment env;

    @Bean(initMethod = "migrate")
    public Flyway flyway() {
        return Flyway.configure()
                .dataSource(dataSource)
                .locations(ConfigConstants.CLASSPATH_RESOURCE_LOCATIONS) // Только db/migration для Flyway
                .baselineOnMigrate(true)
                .validateOnMigrate(true)
                .schemas(env.getProperty("spring.datasource.username", "eventrix"))
                .load();
    }
}