package com.example.mvvmclean.login.data.dto;

import android.support.annotation.NonNull;

public class LoginResponse {
    @NonNull
    private String accessToken;
    @NonNull
    private User user;

    @NonNull
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(@NonNull String accessToken) {
        this.accessToken = accessToken;
    }

    @NonNull
    public User getUser() {
        return user;
    }

    public void setUser(@NonNull User user) {
        this.user = user;
    }
}

