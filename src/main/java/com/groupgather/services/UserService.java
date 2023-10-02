package com.groupgather.services;

import com.groupgather.dao.UserDao;
import com.groupgather.models.User;
import com.groupgather.utils.JsonUtils;
import com.groupgather.utils.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final SecurityUtils securityUtils;
    private final JsonUtils jsonUtils;
    private final UserDao userDao;

    @Autowired
    public UserService(SecurityUtils securityUtils, JsonUtils jsonUtils, UserDao userDao){
        this.securityUtils = securityUtils;
        this.jsonUtils = jsonUtils;
        this.userDao = userDao;
    }

    // Creates the initial account
    public ResponseEntity<String> createAccount(Map<String, String> createPayload){
        String encodedPassword = securityUtils.encodePassword(createPayload.get("password"));
        userDao.createUser(createPayload.get("email"), encodedPassword);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Login account
    public ResponseEntity<String> loginAccount(Map<String, String> loginPayload){
        User user = userDao.getUser(loginPayload.get("email"));
        boolean success = securityUtils.checkPassword(loginPayload.get("password"),
                                                      user.getPassword());

        ResponseEntity<String> response;
        if(success){
            response = ResponseEntity.ok().body("Login Successful");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        LOGGER.debug("User's inputted password matched encoded password: {}", success);
        return response;
    }

    // Update User Settings
    public ResponseEntity<String> updateAccount(Map<String, String> updatePayload){
        String email = updatePayload.get("email");
        updatePayload.remove("email");

        // Get a list of column names and their associated values
        List<List<String>> settings = jsonUtils.mapToLists(updatePayload);
        List<String> columns = settings.get(0);
        List<String> values = settings.get(1);

        userDao.updateUser(email, columns, values);
        return ResponseEntity.ok().body("User Updated");
    }

    public User getUser(String email){
        return userDao.getUser(email);
    }

    // Deletes a given user
    public void deleteUser(String userEmail) {
        userDao.deleteUser(userEmail);
    }
}
