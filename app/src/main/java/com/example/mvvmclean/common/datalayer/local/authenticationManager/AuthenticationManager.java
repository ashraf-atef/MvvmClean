package com.example.mvvmclean.common.datalayer.local.authenticationManager;

import android.content.SharedPreferences;

import com.example.mvvmclean.common.constant.Constant;
import com.example.mvvmclean.common.datalayer.remote.dto.Token;

import javax.inject.Inject;

public class AuthenticationManager implements AuthenticationManagerContract {

    SharedPreferences mSharedPreferences;

    @Inject
    public AuthenticationManager(SharedPreferences sharedPreferences) {
        this.mSharedPreferences = sharedPreferences;
    }

    @Override
    public void logout() {
      removeAuthToken();
      removeRefreshToken();
      removeCustomerId();
      removeHasPin();
    }

    private void removeAuthToken() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.remove(Constant.AUTH_TOKEN_KEY);
        editor.remove(Constant.EXPIRY_CHECK_AUTH_TOKEN);
        editor.remove(Constant.EXPIRY_DATE_AUTH_TOKEN);
        editor.apply();
    }

    private void removeRefreshToken() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.remove(Constant.REFRESH_TOKEN_KEY);
        editor.remove(Constant.EXPIRY_CHECK_REFRESH_TOKEN);
        editor.remove(Constant.EXPIRY_DATE_REFRESH_TOKEN);
        editor.apply();
    }

    private void removeCustomerId() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.remove(Constant.USER_ID);
        editor.apply();
    }

    private void removeHasPin() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.remove(Constant.HAS_PIN);
        editor.apply();
    }

    @Override
    public boolean isLoggedIn() {
        return mSharedPreferences.getString(Constant.AUTH_TOKEN_KEY, null) != null &&
                mSharedPreferences.getString(Constant.REFRESH_TOKEN_KEY, null) != null;
    }

    @Override
    public Token getAuthToken() {
        String authToken = mSharedPreferences.getString(Constant.AUTH_TOKEN_KEY, null);
        if (authToken == null) {
            return null;
        }
        return new Token(authToken,
                mSharedPreferences.getBoolean(Constant.EXPIRY_CHECK_AUTH_TOKEN, false),
                mSharedPreferences.getLong(Constant.EXPIRY_DATE_AUTH_TOKEN, 0L));
    }

    @Override
    public void setAuthToken(Token authToken) {
        if (authToken == null) {
           removeAuthToken();
        } else {
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putString(Constant.AUTH_TOKEN_KEY, authToken.getToken());
            editor.putLong(Constant.EXPIRY_DATE_AUTH_TOKEN, authToken.getExpiryDate());
            editor.putBoolean(Constant.EXPIRY_CHECK_AUTH_TOKEN, authToken.isExpiryCheck());
            editor.commit();
        }
    }

    @Override
    public Token getRefreshToken() {
        String refreshToken = mSharedPreferences.getString(Constant.REFRESH_TOKEN_KEY, null);
        if (refreshToken == null) {
            return null;
        }
        return new Token(refreshToken,
                mSharedPreferences.getBoolean(Constant.EXPIRY_CHECK_REFRESH_TOKEN, false),
                mSharedPreferences.getLong(Constant.EXPIRY_DATE_REFRESH_TOKEN, 0L));
    }

    @Override
    public void setRefreshToken(Token refreshToken) {
        if (refreshToken == null) {
           removeRefreshToken();
        } else {
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putString(Constant.REFRESH_TOKEN_KEY, refreshToken.getToken());
            editor.putLong(Constant.EXPIRY_DATE_REFRESH_TOKEN, refreshToken.getExpiryDate());
            editor.putBoolean(Constant.EXPIRY_CHECK_REFRESH_TOKEN, refreshToken.isExpiryCheck());
            editor.commit();
        }
    }

    @Override
    public void setUserId(String driverId) {
        if (driverId == null) {
           removeCustomerId();
        } else {
            mSharedPreferences.edit().putString(Constant.USER_ID, driverId).commit();
        }
    }

    @Override
    public String getUserId() {
        return mSharedPreferences.getString(Constant.USER_ID, null);
    }

}
