package com.example.mvvmclean.common.di.module;

import android.app.Application;

import com.example.mvvmclean.common.utilities.FragmentUtilities;
import com.example.mvvmclean.common.utilities.ToastUtilities;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class
UtilitiesModule {
    @Provides
    @Singleton
    public FragmentUtilities providesFragmentUtilities() {
        return new FragmentUtilities();
    }

    @Provides
    @Singleton
    public ToastUtilities providesToastUtilities(Application application) {
        return new ToastUtilities(application);
    }
}
