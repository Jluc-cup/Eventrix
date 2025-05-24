package com.eventrix.base.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfig {

//    @Bean(initMethod = "migrate")
//    public Flyway flyway(DataSource dataSource) {
//        return Flyway.configure()
//                .dataSource(dataSource)
//                .locations("classpath:db/migration")
//                .schemas("public")
//                .baselineOnMigrate(true)
//                .baselineVersion("1.0")
//                .validateOnMigrate(true)
//                .load();
//    }
}