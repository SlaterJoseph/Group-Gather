package com.groupgather.controllers;

import com.groupgather.services.ActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
