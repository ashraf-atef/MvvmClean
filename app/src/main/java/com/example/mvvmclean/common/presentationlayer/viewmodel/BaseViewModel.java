package com.example.mvvmclean.common.presentationlayer.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.mvvmclean.common.datalayer.remote.dto.ApiResponse;

import io.reactivex.Single;

public abstract class BaseViewModel extends ViewModel {

    private MutableLiveData<Boolean> LoaderMutableLiveData = new MutableLiveData<>();

    public <T> void syncSingleWithLiveData(Single<ApiResponse<T>> single, MutableLiveData<ApiResponse<T>> mutableLiveData) {

        single.doOnSubscribe(disposable ->  showLoader())
                .doFinally(() -> hideLoader())
                .subscribe(
                tApiResponse -> {
                    mutableLiveData.postValue(tApiResponse);
                },
                throwable -> {
                    mutableLiveData.postValue(new ApiResponse<>(throwable));
                }
        );
    }

    public void showLoader() {
        getLoaderMutableLiveData().postValue(true);
    }

    public void hideLoader() {
        getLoaderMutableLiveData().postValue(false);
    }

    public MutableLiveData<Boolean> getLoaderMutableLiveData() {
        return LoaderMutableLiveData;
    }

}
