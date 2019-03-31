package com.example.mvvmclean.common.utilities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class FragmentUtilities {
    public void addFragment(Context context, int frameId, Fragment fragment, String tag, Bundle bundle) {
        FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(frameId, fragment, tag);
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        fragmentTransaction.commit();
    }

    public void replaceFragment(
            final Context context,
            final int frameId,
            final Fragment fragment,
            final String tag,
            final boolean saveToBackStack,
            final String addToBackStackName
    ) {
        final FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(frameId, fragment, tag);
        if (saveToBackStack) {
            fragmentTransaction.addToBackStack(addToBackStackName);
        }
        fragmentTransaction.commit();
    }

    public void replaceFragmentWithPreventredundancy(
            final Context context,
            final int frameId,
            final Fragment fragment,
            final String tag,
            final boolean saveToBackStack,
            final String addToBackStackName
    ) {
        final FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        if (fragmentManager.findFragmentByTag(fragment.getClass().getName()) == null) {
            replaceFragment(context, frameId, fragment, tag, saveToBackStack, addToBackStackName);
        }
    }

    public Fragment getCurrentFragment(final Context context, int frameId) {
        final FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
        return fragmentManager.findFragmentById(frameId);
    }

}
