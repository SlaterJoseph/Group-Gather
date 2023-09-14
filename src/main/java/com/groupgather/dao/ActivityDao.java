package com.groupgather.dao;

import com.groupgather.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ActivityDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class);
    private final JdbcTemplate jdbcTemplate;
    private final JsonUtils jsonUtils;

    @Autowired
    public ActivityDao(JdbcTemplate jdbcTemplate, JsonUtils jsonUtils){
        this.jdbcTemplate = jdbcTemplate;
        this.jsonUtils = jsonUtils;
    }

    // Create a new activity
    public void createActivity(int userId, List<String> columns, List<String> values){
        LOGGER.debug("Creating activity for user {}", userId);
        StringBuilder sb = new StringBuilder("INSERT INTO activities");

        sb.append(jsonUtils.dynamicSQL(columns, values));
        sb.append()
    }

}
