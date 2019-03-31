package com.example.mvvmclean.login.data;

import com.example.mvvmclean.login.data.dto.LoginRequest;
import com.example.mvvmclean.login.data.dto.LoginResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginApis {
    @POST("auth/login")
    Observable<LoginResponse> login(@Body LoginRequest loginRequest);
}
