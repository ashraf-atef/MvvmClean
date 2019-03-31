package com.example.mvvmclean.common.di.module;

import android.content.SharedPreferences;

import com.example.mvvmclean.common.datalayer.local.authenticationManager.AuthenticationManager;
import com.example.mvvmclean.common.datalayer.local.authenticationManager.AuthenticationManagerContract;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AuthenticationManagerModule {

    @Provides
    @Singleton
    public AuthenticationManagerContract provideAuthenticationManage(SharedPreferences sharedPreferences) {
        return new AuthenticationManager(sharedPreferences);
    }
}
