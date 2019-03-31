package com.example.mvvmclean.common.presentationlayer.activity.baseActivity;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;
import android.widget.Toast;

import com.example.mvvmclean.R;
import com.example.mvvmclean.common.datalayer.remote.dto.ApiResponse;
import com.example.mvvmclean.common.datalayer.remote.dto.error.AuthorizationError;
import com.example.mvvmclean.common.datalayer.remote.dto.error.ConnectionError;
import com.example.mvvmclean.common.datalayer.remote.dto.error.SpecificError;
import com.example.mvvmclean.common.presentationlayer.viewmodel.BaseViewModel;

public abstract class BaseMvvmActivity extends BaseActivity {
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscribeLoaderLiveData();
    }

    public abstract class BaseApiObserver<T> implements Observer<ApiResponse<T>> {

        /**
         * Called when the data is changed.
         *
         * @param tApiResponse The new data
         */
        @Override
        public void onChanged(@Nullable ApiResponse<T> tApiResponse) {
            if (tApiResponse.isError()) {
                Throwable throwable = tApiResponse.getThrowable();
                if (throwable instanceof ConnectionError)
                    onConnectionError();
                else if (throwable instanceof AuthorizationError)
                    onAuthorizationError();
                else if (throwable instanceof UnknownError)
                    onUnknownError();
                else if (throwable instanceof SpecificError)
                    onSpecificError(throwable);
                else
                    onUnknownError();
                onAnyError();
                throwable.printStackTrace();
            } else {
                onSuccess(tApiResponse.getResponse());
            }
            onFinish();
        }

        public abstract void onSuccess(T t);

        public abstract void onSpecificError(Throwable throwable);

        public void onAnyError() {
        }

        public void onFinish() {
        }
    }

    public void onConnectionError() {
        Toast.makeText(getBaseContext(), getResources().getString(R.string.connection_error_message), Toast.LENGTH_LONG).show();
    }

    public void onAuthorizationError() {
        //TODO: Go To Login Screen
        Toast.makeText(getBaseContext(), getResources().getString(R.string.your_session_expired), Toast.LENGTH_LONG).show();
    }

    public void onUnknownError() {
        Toast.makeText(getBaseContext(), getResources().getString(R.string.error_message), Toast.LENGTH_LONG).show();
    }

    public void subscribeLoaderLiveData() {
        if (getViewModel() == null)
            return;

        getViewModel().getLoaderMutableLiveData().observe(this,
                aBoolean -> {
                    if (aBoolean == true)
                        showLoadingView();
                    else
                        hideLoadingView();
                });
    }

    public abstract BaseViewModel getViewModel();

}


