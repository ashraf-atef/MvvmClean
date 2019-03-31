package com.example.mvvmclean.common.di.module;


import com.example.mvvmclean.login.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {})
    abstract MainActivity bindMainActivity();

}


