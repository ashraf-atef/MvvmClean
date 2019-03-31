package com.example.mvvmclean.common.di.module;

import android.util.Log;

import com.example.mvvmclean.common.datalayer.remote.HeadersInterceptor;
import com.example.mvvmclean.login.data.LoginApis;
import com.example.mvvmclean.common.constant.Constant;
import com.example.mvvmclean.common.datalayer.local.authenticationManager.AuthenticationManagerContract;
import com.example.mvvmclean.common.datalayer.remote.dto.AbstractResponseDto;
import com.example.mvvmclean.common.datalayer.remote.dto.RefreshTokenResponseDto;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.lang.reflect.Type;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public abstract class NetModule {

    @Binds
    @Named(Constant.API_URL_KEY)
    public abstract String provideApiUrl(String apiUrl);

    @Provides
    @Singleton
    public static OkHttpClient providesOkHttpClient(AuthenticationManagerContract
                                                                authenticationManagerContract) {
        return new OkHttpClient
                .Builder()
                .addInterceptor(new HeadersInterceptor())
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Interceptor.Chain chain) throws IOException {
                        Request original = chain.request();
                        if (authenticationManagerContract.getAuthToken() != null) {
                            String strAuthToken = authenticationManagerContract.getAuthToken().getToken();
                            Request.Builder requestBuilder = original.newBuilder()
                                    .header(
                                            "Authorization", authenticationManagerContract.getAuthToken().getToken()
                                    );
                            Request request = requestBuilder.build();
                            Response response = chain.proceed(request);

                            if (response.code() == 401) {
                                synchronized (this) {
                                    if (authenticationManagerContract.getAuthToken() != null) {
                                        String currentAuthToken = authenticationManagerContract.getAuthToken().getToken();
                                        if (currentAuthToken != null && currentAuthToken.equals(strAuthToken)) {
                                            int refreshTokenResponseCode = refreshToken();
                                            if (refreshTokenResponseCode / 100 != 2) {
                                                return response;
                                            }
                                        }
                                    }

                                    if (authenticationManagerContract.getAuthToken() != null) {
                                        requestBuilder = original.newBuilder()
                                                .header(
                                                        "Authorization", authenticationManagerContract.getAuthToken().getToken()
                                                );
                                        request = requestBuilder.build();
                                        response = chain.proceed(request);
                                    }
                                }
                            }
                            return response;
                        } else {
                            return chain.proceed(original);
                        }

                    }

                    private int refreshToken() {
                        if (authenticationManagerContract.getRefreshToken() != null) {
                            String strRefreshToken = authenticationManagerContract.getRefreshToken().getToken();
                            OkHttpClient client = new OkHttpClient.Builder()
                                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                                    .build();

                            MediaType jsonType = MediaType.parse("application/json; charset=utf-8");
                            JsonObject requestBody = new JsonObject();
                            requestBody.addProperty(
                                    "refresh_token",
                                    strRefreshToken
                            );
                            RequestBody body = RequestBody.create(jsonType, requestBody.toString());
                            Request refreshTokenRequest = new Request.Builder()
                                    .url(Constant.REFRESH_TOKEN_ENDPOINT)
                                    .post(body)
                                    .build();

                            Response refreshTokenResponse;

                            int refreshTokenResponseCode = 0;

                            try {
                                refreshTokenResponse = client.newCall(refreshTokenRequest).execute();

                                if (refreshTokenResponse != null) {
                                    refreshTokenResponseCode = refreshTokenResponse.code();
                                }

                                if (refreshTokenResponseCode / 100 == 2) {
                                    Type complexType = new TypeToken<AbstractResponseDto<RefreshTokenResponseDto>>() {
                                    }.getType();
                                    AbstractResponseDto<RefreshTokenResponseDto> abstractResponseDto =
                                            new Gson().fromJson(refreshTokenResponse.body().string(), complexType);
                                    RefreshTokenResponseDto refreshTokenResponseDto = abstractResponseDto.getResponse();
                                    authenticationManagerContract.setAuthToken(
                                            refreshTokenResponseDto.getAccessToken());
                                    if (refreshTokenResponseDto.getRefreshToken() != null) {
                                        authenticationManagerContract.setRefreshToken(
                                                refreshTokenResponseDto.getRefreshToken());
                                    }
                                    return refreshTokenResponseCode;
                                }
                            } catch (IOException e) {
                                Log.d("Refresh Token Failed: ", e.getMessage());
                            }

                        }
                        return -1;
                    }
                })
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    @Provides
    @Singleton
    public static Gson providesGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    public static Retrofit providesRetrofit(@Named(Constant.API_URL_KEY) String apiUrl, Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(apiUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    public static LoginApis provideLoginApis(Retrofit retrofit) {
        return retrofit.create(LoginApis.class);
    }

}