package com.groupgather.services;

import com.groupgather.utils.SecurityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {
    SecurityUtils securityUtils;

    public UserService(){
        securityUtils = new SecurityUtils();
    }

    // Creates the initial account
    public ResponseEntity<String> createAccount(Map<String, String> body){
        String encodedPassword = securityUtils.encodePassword(body.get("password"));

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
