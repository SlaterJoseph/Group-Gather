package com.groupgather.mappers;

import com.groupgather.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();

        user.setId(rs.getInt("id"));
        user.setFirstName(rs.getString("firstname"));
        user.setLastName(rs.getString("lastname"));
        user.setNickName(rs.getString("nickname"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setDescription(rs.getString("description"));
        user.setDrinks(rs.getBoolean("drinks"));
        user.setSmokes(rs.getBoolean("smokes"));
        user.setAge(rs.getInt("age"));
        user.setParticipantRating(rs.getLong("participantrating"));
        user.setPlannerRating(rs.getLong("plannerrating"));
        user.setActivitiesParticipated(rs.getInt("activitiesparticipatedin"));
        user.setActivitiesHosted(rs.getInt("activitieshosted"));

        return user;
    }
}
