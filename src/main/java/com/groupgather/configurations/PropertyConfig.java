package com.groupgather.configurations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Configuration
public class PropertyConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyConfig.class);
    private final String filePath = "env.properties";

    // Gets properties (Only used until I figure out how to work Paramstore with Springboot)
    public String getFileData(String query){
        LOGGER.debug("Searching for {}", query);

        String result = null;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] lineChuncks = line.split("=");
                if(lineChuncks[0].equals(query)){
                    result = lineChuncks[1];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        LOGGER.debug("Result: {}", result);

        return result;
    }
}
