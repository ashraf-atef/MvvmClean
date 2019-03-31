package com.example.mvvmclean.login.data.dto;

import android.support.annotation.NonNull;

public class LoginRequest {
    @NonNull
    private String email;
    @NonNull
    private String password;

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }
}
