package com.groupgather.dao;

import com.groupgather.mappers.UserRowMapper;
import com.groupgather.models.User;
import com.groupgather.utils.JsonUtils;
import com.groupgather.utils.SqlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;

@Repository
public class UserDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class);
    private final JdbcTemplate jdbcTemplate;
    private final JsonUtils jsonUtils;
    private final SqlUtils sqlUtils;
    private final HashMap<String, String> sqlMap;
    private final UserRowMapper userRowMapper = new UserRowMapper();

    @Autowired
    public UserDao(DataSource dataSource, JsonUtils jsonUtils, SqlUtils sqlUtils){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.jsonUtils = jsonUtils;
        this.sqlUtils = sqlUtils;
        this.sqlMap = sqlUtils.getUserSqlMap();
    }

    // Creates a new user
    public void createUser(String email, String password){
        LOGGER.debug("Adding user {} to db", email);
        jdbcTemplate.update(sqlMap.get("NEW_USER"),
                            new Object[]{email, password},
                            new int[]{Types.VARCHAR, Types.VARCHAR});
    }

    // Get a user using email
    public User getUser(String email){
        LOGGER.debug("Getting user {} from db", email);
        return jdbcTemplate.queryForObject(sqlMap.get("GET_USER"),
                userRowMapper,
                email);
    }

    // Change a users options/settings
    public void updateUser(String email, List<String> columns, List<String> values){
        LOGGER.debug("Updating settings for user {}", email);
        StringBuilder sb = new StringBuilder("UPDATE users SET ");

        sb.append(jsonUtils.dynamicSQL(columns, values));
        sb.append(" WHERE email = ?");

        jdbcTemplate.update(sb.toString(),
                            new Object[]{email},
                            new int[]{Types.VARCHAR});
    }

    // Deletes a user from the db
    public void deleteUser(String userEmail) {
        LOGGER.debug("Deleting user {} from the server", userEmail);
        jdbcTemplate.update(sqlMap.get("DELETE_USER"),
                            new Object[]{userEmail},
                            new int[]{Types.VARCHAR});
    }
}
