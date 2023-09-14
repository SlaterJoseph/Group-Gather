package com.groupgather.controllers;

import com.groupgather.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("activity")
public class ActivityController {

    ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService){
        this.activityService = activityService;
    }

}
