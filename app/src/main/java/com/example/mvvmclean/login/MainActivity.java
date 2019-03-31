package com.example.mvvmclean.login;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mvvmclean.R;
import com.example.mvvmclean.common.presentationlayer.activity.baseActivity.BaseMvvmActivity;
import com.example.mvvmclean.common.presentationlayer.viewmodel.BaseViewModel;

import javax.inject.Inject;

public class MainActivity extends BaseMvvmActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    LoginViewModel loginViewModel;

    @Override
    public int getContentResource() {
        return R.layout.activity_main;
    }

    @Override
    public void init(Bundle state) {
        loginViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel.class);
        loginViewModel.getLoginLiveData().observe(this, new BaseApiObserver<String>() {
            @Override
            public void onSuccess(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSpecificError(Throwable throwable) {

            }
        });
        loginViewModel.login();
    }

    @Override
    public BaseViewModel getViewModel() {
        return null;
    }
}
