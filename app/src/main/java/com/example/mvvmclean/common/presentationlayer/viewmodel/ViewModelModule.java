package com.example.mvvmclean.common.presentationlayer.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.mvvmclean.login.LoginViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory provideViewModelFactory(MyViewModelFactory myViewModelFactory);

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    abstract ViewModel bindLoginViewModel(LoginViewModel loginViewModel);

}