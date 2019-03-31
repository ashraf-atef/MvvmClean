package com.example.mvvmclean.common.di.module;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.mvvmclean.common.datalayer.local.AppDatabase;
import com.example.mvvmclean.common.datalayer.local.SharedPreferenceProvider;
import com.example.mvvmclean.common.datalayer.local.SharedPreferenceProviderContract;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class StorageModule {

    @Provides
    @Singleton
    public SharedPreferences providesSharedPreference(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    public SharedPreferenceProviderContract providesSharedPreferenceProvider(
            SharedPreferences sharedPreferences) {
        return new SharedPreferenceProvider(sharedPreferences);
    }

    @Provides
    public AppDatabase providesAppDatabase(Application application) {
        return AppDatabase.getAppDatabase(application.getBaseContext());
    }
}
