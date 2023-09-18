package com.groupgather.dao;

import com.groupgather.mappers.ActivityRowMapper;
import com.groupgather.models.Activity;
import com.groupgather.utils.SqlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ActivityDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class);
    private final JdbcTemplate jdbcTemplate;
    private final SqlUtils sqlUtils;
    private final Map<String, String> sqlMap;
    private final ActivityRowMapper activityRowMapper = new ActivityRowMapper();

    @Autowired
    public ActivityDao(JdbcTemplate jdbcTemplate, SqlUtils sqlUtils){
        this.jdbcTemplate = jdbcTemplate;
        this.sqlUtils = sqlUtils;
        this.sqlMap = sqlUtils.getActivitySqlMap();
    }


    // Create a new activity
    public void createActivity(int userId, List<String> columns, List<String> values){
        LOGGER.debug("Creating activity for user {}", userId);
        jdbcTemplate.update(sqlUtils.createSQLActivity(userId, columns, values));
    }

    public List<Activity> getActivitiesByUser(int id) {
        LOGGER.debug("Pulling activity from user {}", id);
        return jdbcTemplate.query(sqlMap.get("GET_ACTIVITIES_BY_USER"), activityRowMapper);
    }
}
