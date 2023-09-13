package com.groupgather.utils;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {
    BCryptPasswordEncoder encoder;

    public SecurityUtils(){
        encoder = new BCryptPasswordEncoder(16);
    }

    // Encoded passwords for db storage
    public String encodePassword(String password){
        return encoder.encode(password);
    }

    // Verifies that passwords match
    public boolean checkPassword(String inputPassword, String dbEncodedPassword){
        return encoder.matches(inputPassword, dbEncodedPassword);
    }
}
