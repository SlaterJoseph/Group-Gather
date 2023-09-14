package com.groupgather.controllers;

import com.groupgather.services.UserService;
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
@RequestMapping("user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    // Method to log in a user
    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> loginPayload){
        LOGGER.debug("Logging in user: {}", loginPayload.get("email"));
        return userService.loginAccount(loginPayload);
    }

    // Method to create an account
    @PostMapping("create-account")
    public ResponseEntity<String> createAccount(@RequestBody Map<String, String> createPayload){
        LOGGER.debug("Create account for user: {}", createPayload.get("email"));
        return userService.createAccount(createPayload);
    }

    // Method to change user options
    @PostMapping("update-account")
    public ResponseEntity<String> changeSettings(@RequestBody Map<String, String> updatePayload){
        LOGGER.debug("Updating settings for user: {}", updatePayload.get("email"));
        return userService.updateAccount(updatePayload);
    }
}
