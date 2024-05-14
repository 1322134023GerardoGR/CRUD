package com.crud1.CRUD.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;


@Configuration
public class DatabaseConfig {

    @Bean
    @ConfigurationProperties("datasource.crud")
    public DataSource crudDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public JdbcTemplate crudjdbcTemplate(DataSource crudDataSource) {
        return new JdbcTemplate(crudDataSource);

    }

    @Bean
    public NamedParameterJdbcTemplate crudNamedParameterJdbcTemplate(DataSource crudDataSource) {
        return new NamedParameterJdbcTemplate(crudDataSource);
    }

}
