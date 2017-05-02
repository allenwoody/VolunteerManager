package com.allen.web.model;

public class VolunteerActivity {
    private String vaId;

    private String volunteerId;

    private String volunteerName;

    private String activityId;

    private String activityName;

    public String getVaId() {
        return vaId;
    }

    public void setVaId(String vaId) {
        this.vaId = vaId == null ? null : vaId.trim();
    }

    public String getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(String volunteerId) {
        this.volunteerId = volunteerId == null ? null : volunteerId.trim();
    }

    public String getVolunteerName() {
        return volunteerName;
    }

    public void setVolunteerName(String volunteerName) {
        this.volunteerName = volunteerName == null ? null : volunteerName.trim();
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId == null ? null : activityId.trim();
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName == null ? null : activityName.trim();
    }
}