package com.example.mvvmclean.common.application;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.example.mvvmclean.common.constant.Constant;
import com.example.mvvmclean.common.di.component.AppComponent;
import com.example.mvvmclean.common.di.component.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;


public class MyApplication extends DaggerApplication {

    AppComponent appComponent;

    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        appComponent = DaggerAppComponent
                .builder()
                .application(this)
                .apiUrl(Constant.API_URL)
                .build();
        appComponent.inject(this);
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
