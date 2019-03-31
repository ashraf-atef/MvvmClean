package com.example.mvvmclean.common.datalayer.local;

import android.content.SharedPreferences;

import javax.inject.Inject;

public class SharedPreferenceProvider implements SharedPreferenceProviderContract {

    private SharedPreferences sharedPreferences;

    @Inject
    public SharedPreferenceProvider(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }
}
