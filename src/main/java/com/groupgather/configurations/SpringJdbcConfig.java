package com.groupgather.configurations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan
public class SpringJdbcConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringJdbcConfig.class);

    @Bean
    public DataSource postgresqlDataSource(){
        LOGGER.info("Connecting to Database");

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://group-gather-db.c3os1uyzhbdd.us-east-1.rds.amazonaws.com/group_gather_db");
        dataSource.setUsername("software_dev");
        dataSource.setPassword("Volbeat65");


        LOGGER.info("Database connection successful");
        return dataSource;
    }
}


/**
 *
 * JDBC FORMAT
 *
 * jdbcTemplate.query(GET_USER, mapper, parameters, ... , end of parameters); (LIST)
 *
 */