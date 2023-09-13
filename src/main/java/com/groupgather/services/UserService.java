package com.groupgather.services;

import com.groupgather.dao.UserDao;
import com.groupgather.models.User;
import com.groupgather.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {
    SecurityUtils securityUtils;
    UserDao userDao;

    @Autowired
    public UserService(UserDao userDao){
        securityUtils = new SecurityUtils();
        this.userDao = userDao;
    }

    // Creates the initial account
    public ResponseEntity<String> createAccount(Map<String, String> body){
        String encodedPassword = securityUtils.encodePassword(body.get("password"));
        userDao.createUser(body.get("email"), encodedPassword);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Login account
    public ResponseEntity<String> loginAccount(Map<String, String> body){
        User user = userDao.getUser(body.get("email"));
        boolean success = securityUtils.checkPassword(body.get("password"), user.getPassword());

        ResponseEntity<String> response;
        if(success){
            response = ResponseEntity.ok().body("Login Successful");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        return response;
    }
}
