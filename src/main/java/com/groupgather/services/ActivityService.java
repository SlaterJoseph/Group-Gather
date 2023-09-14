package com.groupgather.services;

import com.groupgather.dao.ActivityDao;
import com.groupgather.models.User;
import com.groupgather.utils.JsonUtils;
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
        return null;
    }
}
