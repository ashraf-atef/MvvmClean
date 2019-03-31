package com.example.mvvmclean.common.datalayer.remote.dto;

import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

@Keep
public class RefreshTokenResponseDto {
    @SerializedName("access_token")
    private Token accessToken;
    @SerializedName("refresh_token")
    private Token refreshToken;

    public Token getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(Token accessToken) {
        this.accessToken = accessToken;
    }

    public Token getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(Token refreshToken) {
        this.refreshToken = refreshToken;
    }

}
