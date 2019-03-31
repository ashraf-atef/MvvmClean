package com.example.mvvmclean.common.presentationlayer.uiHelper;

import android.view.View;
import android.widget.ProgressBar;

import com.example.mvvmclean.R;
import com.example.mvvmclean.common.presentationlayer.activity.baseActivity.BaseActivity;

public class LoaderLayout {

    private ProgressBar pb_easyLoader;

    public void setLoaderLayout(View view) {
        pb_easyLoader = view.findViewById(R.id.pb_loader);
    }

    public void setLoaderLayout(BaseActivity baseActivity) {
        pb_easyLoader = baseActivity.findViewById(R.id.pb_loader);
    }

    public void showLoadingView() {
        if (pb_easyLoader != null) {
            pb_easyLoader.setVisibility(View.VISIBLE);
        }
    }

    public void hideLoadingView() {
        if (pb_easyLoader != null) {
            pb_easyLoader.setVisibility(View.INVISIBLE);
        }
    }
}
