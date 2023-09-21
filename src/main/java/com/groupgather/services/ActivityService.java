package com.groupgather.services;

import com.groupgather.dao.ActivityDao;
import com.groupgather.models.Activity;
import com.groupgather.models.User;
import com.groupgather.utils.JsonUtils;
import net.postgis.jdbc.PGgeometry;
import net.postgis.jdbc.geometry.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ActivityService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityService.class);
    private final JsonUtils jsonUtils;
    private final UserService userService;
    private final ActivityDao activityDao;

    @Autowired
    public ActivityService(JsonUtils jsonUtils, UserService userService, ActivityDao activityDao){
        this.jsonUtils = jsonUtils;
        this.userService = userService;
        this.activityDao = activityDao;
    }

    // Creates activities
    public ResponseEntity<String> createActivity(Map<String, String> createPayload){
        LOGGER.debug("Creating an activity");
        User user = userService.getUser(createPayload.get("email"));
        createPayload.remove("email");
        ResponseEntity<String> response;

        if(user == null){
            LOGGER.debug("User was null");
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Account not found");
        } else {
            LOGGER.debug("User is found: {}", user.getEmail());

            List<List<String>> settings = jsonUtils.mapToLists(createPayload);
            activityDao.createActivity(user.getId(),
                                       settings.get(0),
                                       settings.get(1));

            response = ResponseEntity.ok().body("Activity successfully created");
        }
        return response;
    }

    public ResponseEntity<String> updateActivity(Map<String, String> updatePayload){
        return null;
    }

    public ResponseEntity<String> addUser(Map<String, String> addUserPayload){
        int activityId = Integer.parseInt(addUserPayload.get("activity_id"));
        int userId = Integer.parseInt(addUserPayload.get("user_id"));

        LOGGER.debug("Adding user {} to activity {}", addUserPayload.get("activity_id"), addUserPayload.get("user_id"));
        ResponseEntity<String> response;

        if(activityDao.checkActivityFull(activityId)){
            response = ResponseEntity.badRequest().body("Activity already full");
        } else {
            activityDao.addActivityParticipantEntry(activityId, userId);
            activityDao.incrementParticipants(activityId);
            response = ResponseEntity.ok("Added to activity");
        }

        return response;
    }

    // Gets activities based on the user/host
    public ResponseEntity<List<Activity>> getActivitiesFromUser(Map<String, String> activityUserPayload) {
        LOGGER.debug("Getting activities from {}", activityUserPayload.get("email"));
        User user = userService.getUser(activityUserPayload.get("email"));
        List<Activity> activityList = activityDao.getActivitiesByUser(user.getId());
        return ResponseEntity.ok(activityList);
    }

    // Gets activities based on the location
    public ResponseEntity<List<Activity>> getActivitiesFromLocation(Map<String, String> locationPayload) {
        double longitude = Double.parseDouble(locationPayload.get("longitude"));
        double latitude = Double.parseDouble(locationPayload.get("latitude"));
        LOGGER.debug("Getting activities close to location long: {}, lat: {}", longitude, latitude);

        PGgeometry location = new PGgeometry(new Point(longitude, latitude));
        List<Activity> activities = activityDao.getActivitiesByLocation(location, Integer.parseInt(locationPayload.get("amount")));
        return ResponseEntity.ok(activities);
    }
}
