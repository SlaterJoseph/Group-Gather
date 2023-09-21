package com.groupgather.models;

import lombok.Data;
import net.postgis.jdbc.PGgeometry;

import java.time.LocalDate;

@Data
public class Activity {
    int id;
    String name;
    int lowestAge;
    int highestAge;
    int hostId;
    int numOfPeople;
    int currPeople;
    boolean drinking;
    boolean smoking;
    String description;
    PGgeometry location;
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
        sb.append(", currPeople=");                     sb.append(currPeople);
        sb.append(", drinking=");                       sb.append(drinking);
        sb.append(", smoking=");                        sb.append(smoking);
        sb.append(", description=");                    sb.append(description);
        sb.append(", location");                        sb.append(location);
        sb.append(", dateCreated");                     sb.append(dateCreated);
        sb.append(", dateOfEvent");                     sb.append(dateOfEvent);
        sb.append("]");
        return sb.toString();
    }
}
