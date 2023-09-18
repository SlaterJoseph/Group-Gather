package com.groupgather.dao;

import com.groupgather.utils.SqlUtils;
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
    private final SqlUtils sqlUtils;

    @Autowired
    public ActivityDao(JdbcTemplate jdbcTemplate, SqlUtils sqlUtils){
        this.jdbcTemplate = jdbcTemplate;
        this.sqlUtils = sqlUtils;

    }

    // Create a new activity
    public void createActivity(int userId, List<String> columns, List<String> values){
        LOGGER.debug("Creating activity for user {}", userId);
        jdbcTemplate.update(sqlUtils.createSQLActivity(userId, columns, values));
    }
}
