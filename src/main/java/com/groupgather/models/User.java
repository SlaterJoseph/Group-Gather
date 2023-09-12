package com.groupgather.models;

import lombok.Data;

@Data
public class User {
    int id;
    String firstName;
    String lastName;
    String nickName;
    String email;
    String password;
    String description;
    boolean drinks;
    boolean smokes;
    int age;
    long participantRating;
    long plannerRating;
    int activitiesParticipated;
    int activitiesHosted;
}
