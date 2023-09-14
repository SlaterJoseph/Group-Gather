package com.groupgather.dao;

import com.groupgather.mappers.UserRowMapper;
import com.groupgather.models.User;
import com.groupgather.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;

@Repository
public class UserDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class);
    private final JdbcTemplate jdbcTemplate;
    private final JsonUtils jsonUtils;
    private final UserRowMapper userRowMapper = new UserRowMapper();

    @Autowired
    public UserDao(DataSource dataSource, JsonUtils jsonUtils){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.jsonUtils = jsonUtils;
    }

    // Start of SQL statements
    private final String NEW_USER =
            "INSERT INTO users " +
            "(email, password) " +
            "VALUES (?, ?)";

    private final String GET_USER =
            "SELECT * " +
            "FROM users " +
            "WHERE email = ?";

    //

    // Creates a new user
    public void createUser(String email, String password){
        LOGGER.debug("Adding user {} to db", email);
        jdbcTemplate.update(NEW_USER,
                            new Object[]{email, password},
                            new int[]{Types.VARCHAR, Types.VARCHAR});
    }

    // Get a user using email
    public User getUser(String email){
        LOGGER.debug("Getting user {} from db", email);
        return jdbcTemplate.queryForObject(GET_USER, userRowMapper, email);
    }

    // Change a users options/settings
    public void updateUser(String email, List<String> columns, List<String> values){
        LOGGER.debug("Updating settings for user {}", email);
        StringBuilder sb = new StringBuilder("UPDATE users SET");

        sb.append(jsonUtils.dynamicSQL(columns, values));
        sb.append("WHERE id = ?");
        jdbcTemplate.update(sb.toString(),
                            new Object[]{email},
                            new int[]{Types.VARCHAR});
    }
}
