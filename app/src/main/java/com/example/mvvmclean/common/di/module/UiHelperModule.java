package com.example.mvvmclean.common.di.module;

import com.example.mvvmclean.common.presentationlayer.uiHelper.LoaderLayout;

import dagger.Module;
import dagger.Provides;

@Module
public class UiHelperModule {

    @Provides
    public LoaderLayout provideLoaderLayout() {
        return new LoaderLayout();
    }
}
