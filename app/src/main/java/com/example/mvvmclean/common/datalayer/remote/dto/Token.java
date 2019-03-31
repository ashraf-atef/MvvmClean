package com.example.mvvmclean.common.datalayer.remote.dto;

import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

@Keep
public class Token {
    String token;
    @SerializedName("check_expiration")
    boolean expiryCheck;
    @SerializedName("expiration_date")
    long expiryDate;

    public Token(String token, boolean expiryCheck, long expiryDate) {
        this.token = token;
        this.expiryCheck = expiryCheck;
        this.expiryDate = expiryDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isExpiryCheck() {
        return expiryCheck;
    }

    public void setExpiryCheck(boolean expiryCheck) {
        this.expiryCheck = expiryCheck;
    }

    public long getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(long expiryDate) {
        this.expiryDate = expiryDate;
    }
}
