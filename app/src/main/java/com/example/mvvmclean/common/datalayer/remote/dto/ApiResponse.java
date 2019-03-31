package com.example.mvvmclean.common.datalayer.remote.dto;

public class ApiResponse<T> {
    T response;
    Throwable throwable;

    public ApiResponse() {
    }

    public ApiResponse(T response) {
        this.response = response;
    }

    public ApiResponse(Throwable throwable) {
        this.throwable = throwable;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public boolean isError() {
       return throwable != null;
    }
}
