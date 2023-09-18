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
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setNickName(rs.getString("nick_name"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setDescription(rs.getString("description"));
        user.setDrinks(rs.getBoolean("does_drink"));
        user.setSmokes(rs.getBoolean("does_smoke"));
        user.setAge(rs.getInt("age"));
        user.setParticipantRating(rs.getLong("participant_rating"));
        user.setPlannerRating(rs.getLong("planner_rating"));
        user.setActivitiesParticipated(rs.getInt("activities_participated_in"));
        user.setActivitiesHosted(rs.getInt("activities_hosted"));

        return user;
    }
}
