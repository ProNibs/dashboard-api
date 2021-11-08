package com.flatironschool.dashboard.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MCMSDataSourceConfig {

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://mcmsdb:5432/mcmsdb");
        dataSourceBuilder.username("mcmsuser");
        dataSourceBuilder.password("mcmsuser123!");
        return dataSourceBuilder.build();
    }
}
