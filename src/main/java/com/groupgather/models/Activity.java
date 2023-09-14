package com.groupgather.models;

import lombok.Data;
import org.springframework.cglib.core.Local;

import java.sql.Date;
import java.time.LocalDate;

@Data
public class Activity {
    int id;
    String name;
    int lowestAge;
    int highestAge;
    int hostId;
    int numOfPeople;
    String listOfPeople; // Organized as "email|||email|||email...|||email
    boolean drinking;
    boolean smoking;
    String description;
    String location;
    LocalDate dateCreated;
    LocalDate dateOfEvent;

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Activity [id=");                     sb.append(id);
        sb.append(", name=");                           sb.append(name);
        sb.append(", lowestAge=");                      sb.append(lowestAge);
        sb.append(", highestAge=");                     sb.append(highestAge);
        sb.append(", hostId=");                         sb.append(hostId);
        sb.append(", numOfPeople=");                    sb.append(numOfPeople);
        sb.append(", listOfPeople=");                   sb.append(listOfPeople);
        sb.append(", drinking=");                       sb.append(drinking);
        sb.append(", smoking=");                        sb.append(smoking);
        sb.append(", description=");                    sb.append(description);
        sb.append(", location");                        sb.append(location);
        sb.append(", dateCreated");                     sb.append(dateCreated);
        sb.append(", dateOfEvent");                   sb.append(dateOfEvent);
        sb.append("]");
        return sb.toString();
    }
}
