package com.groupgather.controllers;

import com.groupgather.models.User;
import com.groupgather.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Method to log in a user
    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> loginPayload) {
        LOGGER.debug("Logging in user: {}", loginPayload.get("email"));
        return userService.loginAccount(loginPayload);
    }

    // Method to create an account
    @PostMapping("create-account")
    public ResponseEntity<String> createAccount(@RequestBody Map<String, String> createPayload) {
        LOGGER.debug("Create account for user: {}", createPayload.get("email"));
        return userService.createAccount(createPayload);
    }

    // Method to change user options
    @PostMapping("update-account")
    public ResponseEntity<String> changeSettings(@RequestBody Map<String, String> updatePayload) {
        LOGGER.debug("Updating settings for user: {}", updatePayload.get("email"));
        return userService.updateAccount(updatePayload);
    }

    // Gets user info
    @GetMapping("get-user")
    public ResponseEntity<User> getUser(@RequestBody Map<String, String> userPayload) {
        LOGGER.debug("Getting user {}", userPayload.get("email"));
        ResponseEntity<User> response;
        User user = userService.getUser(userPayload.get("email"));

        // Deals with the possibility of the user no longer existing
        if (user == null) {
            response = ResponseEntity.badRequest().body(null);
        } else {
            response = ResponseEntity.ok(user);
        }

        return response;
    }

    // Deletes a user account as well sa activities posted by said user
    @DeleteMapping("delete-user/{user-email}")
    public ResponseEntity<String> deleteUser(@PathVariable("user-email") String userEmail){
        LOGGER.debug("Deleting user {}, along with activities posted by the user", userEmail);
        userService.deleteUser(userEmail);
        return ResponseEntity.ok().body("User Deleted");
    }
}
