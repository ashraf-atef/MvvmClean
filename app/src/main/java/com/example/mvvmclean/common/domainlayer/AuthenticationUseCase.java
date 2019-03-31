package com.example.mvvmclean.common.domainlayer;

import com.example.mvvmclean.common.datalayer.remote.AuthenticationRepository.AuthenticationRepository;

import javax.inject.Inject;

public class AuthenticationUseCase {

    AuthenticationRepository authenticationRepository;

    @Inject
    public AuthenticationUseCase(AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }

    public boolean isNullableAuthToken() {
        return authenticationRepository.isNullableAuthToken();
    }

    public boolean isLoggedIn() {
        return authenticationRepository.isLoggedin();
    }
}
