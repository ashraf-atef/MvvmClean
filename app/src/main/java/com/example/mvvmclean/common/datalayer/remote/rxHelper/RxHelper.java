package com.example.mvvmclean.common.datalayer.remote.rxHelper;

import com.example.mvvmclean.common.datalayer.local.authenticationManager.AuthenticationManagerContract;
import com.example.mvvmclean.common.datalayer.remote.dto.AbstractResponseDto;
import com.example.mvvmclean.common.datalayer.remote.dto.ApiResponse;
import com.example.mvvmclean.common.datalayer.remote.dto.error.AuthorizationError;
import com.example.mvvmclean.common.datalayer.remote.dto.error.ConnectionError;
import com.example.mvvmclean.common.datalayer.remote.dto.error.SpecificError;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.Result;

import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxHelper {
    AuthenticationManagerContract authenticationManagerContract;

    @Inject
    public RxHelper(AuthenticationManagerContract authenticationManagerContract) {
        this.authenticationManagerContract = authenticationManagerContract;
    }

    public Observable performRequestOutOfBackgroundJob(Observable observable) {
        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //TODO: add on success that will validate the response before forwarding it
    public <T> Single<ApiResponse<T>> performInBackground(Single<Result<AbstractResponseDto<T>>> single) {
        return single.map(tResult -> {
                    ApiResponse<T> apiResponse = new ApiResponse();
                    if (tResult.isError()) {
                        Throwable throwable = tResult.error();
                        if (throwable instanceof UnknownHostException
                                || throwable instanceof ConnectException
                                || throwable instanceof SocketTimeoutException) {
                            apiResponse.setThrowable(new ConnectionError());
                        }else {
                            apiResponse.setThrowable(new UnknownError());
                        }
                    } else {
                        int code = tResult.response().code();

                        if (tResult.response().isSuccessful()) {
                            apiResponse.setResponse(tResult.response().body().getResponse());

                        } else if (authenticationManagerContract.getAuthToken() != null &&
                                (code == HttpURLConnection.HTTP_UNAUTHORIZED
                                        || (code == HttpURLConnection.HTTP_FORBIDDEN))) {
                            authenticationManagerContract.logout();
                            apiResponse.setThrowable(new AuthorizationError());
                        } else {
                            apiResponse.setThrowable(new UnknownError());
                            try {
                                String errorBody = tResult.response().errorBody().string();
                                if (errorBody != null) {
                                    Gson gson = new Gson();
                                    try {
                                        SpecificError specificError = new SpecificError();
                                        specificError = gson.fromJson(errorBody, SpecificError.class);
                                        specificError.setErrorBody(errorBody);
                                        apiResponse.setThrowable(specificError);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            } catch (NullPointerException | IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    return apiResponse;
                })
                .compose(new BackgroundSchedulers<>());
    }

}
