package com.example.mvvmclean.common.datalayer.local.authenticationManager;

import com.example.mvvmclean.common.datalayer.remote.dto.Token;

public interface AuthenticationManagerContract {
        void logout();
        boolean isLoggedIn();
        Token getAuthToken();
        void setAuthToken(Token setAuthToken);
        Token getRefreshToken();
        void setRefreshToken(Token refreshToken);
        void setUserId(String driverId);
        String getUserId();
}
