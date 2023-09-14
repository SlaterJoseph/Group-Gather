package com.groupgather.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityUtils.class);
    BCryptPasswordEncoder encoder;

    public SecurityUtils(){
        encoder = new BCryptPasswordEncoder(16);
    }

    // Encoded passwords for db storage
    public String encodePassword(String password){
        LOGGER.debug("Encoding password for db storage");
        return encoder.encode(password);
    }

    // Verifies that passwords match
    public boolean checkPassword(String inputPassword, String dbEncodedPassword){
        LOGGER.debug("Checking if inputted password matches encoded password");
        return encoder.matches(inputPassword, dbEncodedPassword);
    }
}
