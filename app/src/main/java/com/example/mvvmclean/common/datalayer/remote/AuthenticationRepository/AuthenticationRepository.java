package com.example.mvvmclean.common.datalayer.remote.AuthenticationRepository;

import com.example.mvvmclean.common.datalayer.local.authenticationManager.AuthenticationManagerContract;

import javax.inject.Inject;

public class AuthenticationRepository {
    AuthenticationManagerContract authenticationManagerContract;

    @Inject
    public AuthenticationRepository(AuthenticationManagerContract authenticationManagerContract) {
        this.authenticationManagerContract = authenticationManagerContract;
    }

    public boolean isNullableAuthToken() {
        return authenticationManagerContract.getAuthToken() == null;
    }

    public boolean isLoggedin() {
        return authenticationManagerContract.isLoggedIn();
    }
}
