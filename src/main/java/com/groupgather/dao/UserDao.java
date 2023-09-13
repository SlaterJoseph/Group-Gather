package com.groupgather.dao;

import com.groupgather.mappers.UserRowMapper;
import com.groupgather.models.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Types;

@Repository
public class UserDao {

    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper = new UserRowMapper();

    @Autowired
    public UserDao(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
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
        jdbcTemplate.update(NEW_USER,
                            new Object[]{email, password},
                            new int[]{Types.VARCHAR, Types.VARCHAR});
    }

    public User getUser(String email){
        return jdbcTemplate.queryForObject(GET_USER, userRowMapper, email);
    }
}
