package com.groupgather.controllers;

import com.groupgather.models.Activity;
import com.groupgather.services.ActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("activity")
public class ActivityController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityController.class);
    private final ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService){
        this.activityService = activityService;
    }

    @PostMapping("create-activity")
    public ResponseEntity<String> createActivity(@RequestBody Map<String, String> activityPayload){
        LOGGER.debug("Creating activity for user {} named {}", activityPayload.get("userEmail"), activityPayload.get("name"));
        return activityService.createActivity(activityPayload);
    }

    @GetMapping("location") // Get activities within some distance
    public ResponseEntity<List<Activity>> getActivitiesLocation(@RequestBody Map<String, String> locationPayload){
        LOGGER.debug("Getting activities based on location");
        return activityService.getActivitiesFromLocation(locationPayload);
    }

//    // Gets activities of a certain type
//    @GetMapping("type")
//    public ResponseEntity<List<Activity>> getActivityType(@RequestBody Map<String, String> activityTypePayload){
//        LOGGER.debug("Getting activities based on type");
//
//    }

    // Gets activities from a specific user
    @GetMapping("user")
    public ResponseEntity<List<Activity>> getActivityUser(@RequestBody Map<String, String> activityUserPayload){
        LOGGER.debug("Getting activities from user {}", activityUserPayload.get("email"));
        return activityService.getActivitiesFromUser(activityUserPayload);
    }

    @PostMapping("join-event")
    public ResponseEntity<String> joinEvent(@RequestBody Map<String, String> joinPayload){
        LOGGER.debug("User {} is attempting to join event {}", joinPayload.get("user_id"), joinPayload.get("activity_id"));
        return activityService.addUser(joinPayload);
    }
}
