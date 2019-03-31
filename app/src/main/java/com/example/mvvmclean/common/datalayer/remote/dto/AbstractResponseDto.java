package com.example.mvvmclean.common.datalayer.remote.dto;

import android.support.annotation.Keep;

import java.io.Serializable;
import java.util.List;

@Keep
public class AbstractResponseDto<T> implements Serializable{

    boolean status;
    T response;
    List<String> errors;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

}
