package com.example.mvvmclean.login;

import android.arch.lifecycle.MutableLiveData;

import com.example.mvvmclean.common.datalayer.remote.dto.ApiResponse;
import com.example.mvvmclean.common.presentationlayer.viewmodel.BaseViewModel;
import com.example.mvvmclean.login.data.LoginApis;

import javax.inject.Inject;

import io.reactivex.Single;

public class LoginViewModel extends BaseViewModel {

    MutableLiveData<ApiResponse<String>> loginLiveData = new MutableLiveData<>();

    @Inject
    public LoginViewModel(LoginApis loginApis) {
    }

    public void login() {
        syncSingleWithLiveData(Single.just(new ApiResponse<String>("Astraf")), loginLiveData);
    }

    public MutableLiveData<ApiResponse<String>> getLoginLiveData() {
        return loginLiveData;
    }
}
