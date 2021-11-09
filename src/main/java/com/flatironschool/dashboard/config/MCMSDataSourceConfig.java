package com.flatironschool.dashboard.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

import javax.sql.DataSource;

@Configuration
public class MCMSDataSourceConfig {

    // the part after the colon is defaults
    @Value("${DATABASE_HOSTNAME:localhost}")
    private String database_hostname;
    @Value("${DATABASE_PORT:5432}")
    private String database_port;
    @Value("${DATABASE_NAME:mcmsdb}")
    private String database_name;
    @Value("${DATABASE_USER:mcmsuser}")
    private String database_user;
    @Value("${DATABASE_PASSWORD:mcmsuser123!}")
    private String database_password;
    

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url(
            String.format("jdbc:postgresql://%s:%s/%s",
            database_hostname, database_port, database_name ));
        dataSourceBuilder.username(database_user);
        dataSourceBuilder.password(database_password);
        return dataSourceBuilder.build();
    }
}
