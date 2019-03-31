package com.example.mvvmclean.common.datalayer.remote.dto.error;

import android.support.annotation.Keep;

import com.google.gson.annotations.Expose;

import java.util.List;

@Keep
public class SpecificError extends Throwable {
    @Expose
    private String errorBody;
    private List<String> errors;

    public SpecificError(String errorBody, List<String> errors) {
        this.errorBody = errorBody;
        this.errors = errors;
    }

    public SpecificError() {
    }

    public String getErrorBody() {
        return errorBody;
    }

    public void setErrorBody(String errorBody) {
        this.errorBody = errorBody;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public boolean validate() {
        return errors != null && errors.size() > 0;
    }
}
