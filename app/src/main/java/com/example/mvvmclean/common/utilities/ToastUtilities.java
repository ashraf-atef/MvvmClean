package com.example.mvvmclean.common.utilities;

import android.app.Application;
import android.widget.Toast;

import javax.inject.Inject;

public class ToastUtilities {
    Application application;

    @Inject
    public ToastUtilities(Application application) {
        this.application = application;
    }

    public void showLongToast(String message) {
        Toast.makeText(application.getBaseContext(), message, Toast.LENGTH_LONG).show();
    }

    public void showShortToast(String message) {
        Toast.makeText(application.getBaseContext(), message, Toast.LENGTH_SHORT).show();
    }
}
