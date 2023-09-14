package com.groupgather.configurations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan
public class SpringJdbcConfig {
    /**
     *
     * JDBC FORMAT
     * jdbcTemplate.query(GET_USER, mapper, parameters, ... , end of parameters); (LIST)
     *
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringJdbcConfig.class);
    private final PropertyConfig propertyConfig;

    @Autowired
    public SpringJdbcConfig(PropertyConfig propertyConfig){
    this.propertyConfig = propertyConfig;
    }

    @Bean
    public DataSource postgresqlDataSource(){
        LOGGER.info("Connecting to Database");

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(propertyConfig.getFileData("db_url"));
        dataSource.setUsername(propertyConfig.getFileData("db_username"));
        dataSource.setPassword(propertyConfig.getFileData("db_password"));


        LOGGER.info("Database connection successful");
        return dataSource;
    }
}