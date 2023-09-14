package com.groupgather.models;

import lombok.Data;

@Data
public class Activity {
    int id;
    String name;
    int lowestAge;
    int highestAge;
    int hostId;
    int numOfPeople;
    boolean drinking;
    boolean smoking;
    String description;
    int cost;
    String location;

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Activity [id=");                     sb.append(id);
        sb.append(", name=");                           sb.append(name);
        sb.append(", lowestAge=");                      sb.append(lowestAge);
        sb.append(", highestAge=");                     sb.append(highestAge);
        sb.append(", hostId=");                         sb.append(hostId);
        sb.append(", numOfPeople=");                    sb.append(numOfPeople);
        sb.append(", drinking=");                       sb.append(drinking);
        sb.append(", smoking=");                        sb.append(smoking);
        sb.append(", description=");                    sb.append(description);
        sb.append(", cost");                            sb.append(cost);
        sb.append(", location");                        sb.append(location);
        sb.append("]");
        return sb.toString();
    }
}
