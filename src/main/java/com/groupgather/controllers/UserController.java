package com.groupgather.controllers;

import com.groupgather.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    // Method to login a user
    @PostMapping("login")
    public ____ login(){

    }

    // Method to create an account
    @PostMapping("create-account")
    public ___ createAccount(){

    }

    // Method to change user options
    @PostMapping("change-options")
    public ___ changeSettings(){

    }
}
