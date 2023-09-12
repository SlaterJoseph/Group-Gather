package com.groupgather.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan
public class SpringJdbcConfig {
    @Bean
    public DataSource postgresqlDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.postgresql.jdbc.Driver");
        dataSource.setUrl("group-gather-rds.c3os1uyzhbdd.us-east-1.rds.amazonaws.com");
        dataSource.setUsername("software_dev");
        dataSource.setPassword("Volbeat65");

        return dataSource;
    }
}
