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
}
