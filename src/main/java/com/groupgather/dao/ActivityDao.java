package com.groupgather.dao;

import com.groupgather.mappers.ActivityRowMapper;
import com.groupgather.models.Activity;
import com.groupgather.utils.SqlUtils;
import net.postgis.jdbc.PGgeometry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.JDBCType;
import java.sql.Types;
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

    // Gets the activities from the given user
    public List<Activity> getActivitiesByUser(int id) {
        LOGGER.debug("Pulling activity from user {}", id);
        return jdbcTemplate.query(sqlMap.get("GET_ACTIVITIES_BY_USER"), activityRowMapper);
    }

    // Checks if the total participants has been hit
    public boolean checkActivityFull(int activityId) {
    }

    // Increments the current participants
    public void incrementParticipants(Integer activityId) {
        LOGGER.debug("Incrementing activity {} participants by 1", activityId);
        jdbcTemplate.update(sqlMap.get("INCREMENT_PARTICIPANTS"),
                new Object[]{activityId},
                new int[]{Types.INTEGER});
    }

    // Adds an entry to the Activity - User table
    public void addActivityParticipantEntry(Integer activityId, Integer userId) {
        LOGGER.debug("Adding user {} with activity {} to the participant table", userId, activityId);
        jdbcTemplate.update(sqlMap.get("ADD_PARTICIPANT"),
                new Object[]{activityId, userId},
                new int[]{Types.INTEGER, Types.INTEGER});
    }

    // Queries activities from db from location
    public List<Activity> getActivitiesByLocation(PGgeometry location, int amount) {
        LOGGER.debug("Getting {} activities near location {}", amount, location);
        return jdbcTemplate.query(sqlMap.get("GET_ACTIVITIES_BY_LOCATION"),
                ps -> {
                    ps.setObject(1, location);
                    ps.setInt(2, amount);
                }, activityRowMapper);
    }
}
