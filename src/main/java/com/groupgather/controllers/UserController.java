package com.groupgather.controllers;

import com.groupgather.services.UserService;
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

    UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    // Method to log in a user
    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> loginPayload){
        return userService.loginAccount(loginPayload);
    }

    // Method to create an account
    @PostMapping("create-account")
    public ResponseEntity<String> createAccount(@RequestBody Map<String, String> createPayload){
        return userService.createAccount(createPayload);
    }

    // Method to change user options
    @PostMapping("change-options")
    public void changeSettings(){

    }
}
