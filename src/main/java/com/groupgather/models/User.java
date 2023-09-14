package com.groupgather.models;

import lombok.Data;

@Data
public class User {
    int id;
    String firstName;
    String lastName;
    String nickName;
    String username;
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

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("User [id=");                 sb.append(id);
        sb.append(", firstName=");              sb.append(firstName);
        sb.append(", lastName");                sb.append(lastName);
        sb.append(", nickName");                sb.append(nickName);
        sb.append(", userName");                sb.append(username);
        sb.append(", email");                   sb.append(email);
        sb.append(", password");                sb.append(password);
        sb.append(", description");             sb.append(description);
        sb.append(" drinks");                   sb.append(drinks);
        sb.append(", smokes");                  sb.append(smokes);
        sb.append(", age");                     sb.append(age);
        sb.append(", participantRating");       sb.append(participantRating);
        sb.append(", plannerRating");           sb.append(plannerRating);
        sb.append(", activitiesParticipated");  sb.append(activitiesParticipated);
        sb.append(", activitiesHosted");        sb.append(activitiesHosted);
        sb.append("]");
        return sb.toString();
    }
}
