package com.example.mvvmclean.login.data.dto;

public class User {
    private long id;
    private String name;
    private String email;
    private String locale;
    private boolean seenWalkThrough;
    private boolean newRestaurantMemberNotification;
    private boolean newRestaurantListNotification;
    private boolean restaurantListReminderNotification;
    private boolean verified;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public boolean isSeenWalkThrough() {
        return seenWalkThrough;
    }

    public void setSeenWalkThrough(boolean seenWalkThrough) {
        this.seenWalkThrough = seenWalkThrough;
    }

    public boolean isNewRestaurantMemberNotification() {
        return newRestaurantMemberNotification;
    }

    public void setNewRestaurantMemberNotification(boolean newRestaurantMemberNotification) {
        this.newRestaurantMemberNotification = newRestaurantMemberNotification;
    }

    public boolean isNewRestaurantListNotification() {
        return newRestaurantListNotification;
    }

    public void setNewRestaurantListNotification(boolean newRestaurantListNotification) {
        this.newRestaurantListNotification = newRestaurantListNotification;
    }

    public boolean isRestaurantListReminderNotification() {
        return restaurantListReminderNotification;
    }

    public void setRestaurantListReminderNotification(boolean restaurantListReminderNotification) {
        this.restaurantListReminderNotification = restaurantListReminderNotification;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
