package com.example.mvvmclean.common.di.component;

import android.app.Application;

import com.example.mvvmclean.common.application.MyApplication;
import com.example.mvvmclean.common.constant.Constant;
import com.example.mvvmclean.common.di.module.ActivityBuilder;
import com.example.mvvmclean.common.di.module.AppModule;
import com.example.mvvmclean.common.di.module.AuthenticationManagerModule;
import com.example.mvvmclean.common.di.module.NetModule;
import com.example.mvvmclean.common.di.module.RxHelperModule;
import com.example.mvvmclean.common.di.module.StorageModule;
import com.example.mvvmclean.common.di.module.UiHelperModule;
import com.example.mvvmclean.common.di.module.UtilitiesModule;
import com.example.mvvmclean.common.presentationlayer.viewmodel.ViewModelModule;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivityBuilder.class,
        AppModule.class,
        AuthenticationManagerModule.class,
        NetModule.class,
        StorageModule.class,
        UtilitiesModule.class,
        UiHelperModule.class,
        RxHelperModule.class,
        ViewModelModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    void inject(MyApplication myApplication);

    @Override
    void inject(DaggerApplication instance);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        @BindsInstance
        @Named(Constant.API_URL_KEY)
        Builder apiUrl(String apiUrl);

        AppComponent build();
    }
}
