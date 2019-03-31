package com.example.mvvmclean.common.datalayer.remote;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeadersInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder requestBuilder = request.newBuilder();
        requestBuilder.addHeader("Accept", "application/json");
        requestBuilder.addHeader("Content-Type", "application/json");
        requestBuilder.addHeader("client-id:",
                "Zeexh8YMzu4EQE3e9XzEEj6SeHMAfcAnpGGZGDHfisFUSVxJZU");
        requestBuilder.addHeader("client-secret",
                "oClYypmnZMHhr2SufygkoI30yux1VWvtVIZPUWN0JOkgJmgxiq");
        return chain.proceed(requestBuilder.build());
    }
}
