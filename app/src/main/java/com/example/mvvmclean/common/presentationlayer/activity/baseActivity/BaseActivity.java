package com.example.mvvmclean.common.presentationlayer.activity.baseActivity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatDelegate;

import com.example.mvvmclean.common.di.module.UtilitiesModule;
import com.example.mvvmclean.common.presentationlayer.uiHelper.LoaderLayout;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.annotations.Nullable;

public abstract class BaseActivity extends DaggerAppCompatActivity {
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Inject
    public LoaderLayout loaderLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getContentResource() != -1) {
            setContentView(getContentResource());
            ButterKnife.bind(this);
            setLoaderView();
        }
        init(savedInstanceState);
    }

    /**
     * Layout resource to be inflated
     *
     * @return layout resource
     */
    @LayoutRes
    public abstract int getContentResource();

    /**
     * Initialisations
     */
    public abstract void init(@Nullable Bundle state);

    public void setLoaderView() {
        loaderLayout.setLoaderLayout(this);
    }

    public void showLoadingView() {
        loaderLayout.showLoadingView();
    }

    public void hideLoadingView() {
        loaderLayout.hideLoadingView();
    }
}
