package com.groupgather.services;

import com.groupgather.dao.UserDao;
import com.groupgather.models.User;
import com.groupgather.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    public ResponseEntity<String> createAccount(Map<String, String> payload){
        String encodedPassword = securityUtils.encodePassword(payload.get("password"));
        userDao.createUser(payload.get("email"), encodedPassword);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Login account
    public ResponseEntity<String> loginAccount(Map<String, String> payload){
        User user = userDao.getUser(payload.get("email"));
        boolean success = securityUtils.checkPassword(payload.get("password"), user.getPassword());

        ResponseEntity<String> response;
        if(success){
            response = ResponseEntity.ok().body("Login Successful");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        return response;
    }

    // Update User Settings
    public ResponseEntity<String> updateAccount(Map<String, String> payload){
        String email = payload.get("email");

        // Get a list of column names and their associated values
        List<String> columns = new ArrayList<>(payload.keySet());
        List<String> values = new ArrayList<>();
        for(String column : columns){
            values.add(payload.get(column));
        }

        userDao.updateUser(email, columns, values);

        return ResponseEntity.ok().body("User Updated");
    }
}
