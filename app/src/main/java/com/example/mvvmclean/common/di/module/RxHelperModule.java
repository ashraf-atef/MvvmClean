package com.example.mvvmclean.common.di.module;

import com.example.mvvmclean.common.datalayer.local.authenticationManager.AuthenticationManager;
import com.example.mvvmclean.common.datalayer.remote.rxHelper.RxHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RxHelperModule {
    @Provides
    @Singleton
    public RxHelper providesRxHelper(AuthenticationManager authenticationManagerContract) {
        return new RxHelper(authenticationManagerContract);
    }
}
