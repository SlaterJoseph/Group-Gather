package com.groupgather.mappers;

import com.groupgather.models.Activity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ActivityRowMapper implements RowMapper<Activity> {
    @Override
    public Activity mapRow(ResultSet rs, int rowNum) throws SQLException {
        Activity activity = new Activity();

        activity.setId(rs.getInt("id"));
        activity.setName(rs.getString("name"));
        activity.setLowestAge(rs.getInt("lowest_age"));
        activity.setHighestAge(rs.getInt("highest_age"));
        activity.setHostId(rs.getInt("host_id"));
        activity.setNumOfPeople(rs.getInt("num_of_people"));
        activity.setListOfPeople(rs.getString("list_of_people"));
        activity.setDrinking(rs.getBoolean("drinking"));
        activity.setSmoking(rs.getBoolean("smoking"));
        activity.setDescription(rs.getString("description"));
        activity.setLocation(rs.getString("location"));
        activity.setDateCreated(rs.getDate("date_created").toLocalDate());
        activity.setDateOfEvent(rs.getDate("date_of_event").toLocalDate());
        return activity;
    }
}
