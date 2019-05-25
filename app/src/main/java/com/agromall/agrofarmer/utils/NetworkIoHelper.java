package com.agromall.agrofarmer.utils;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.agromall.agrofarmer.BuildConfig;

import java.io.File;
import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class NetworkIoHelper {

    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance(@NonNull String baseUrl, boolean addUserIdToRequest) {

        //Create OkHttpClient Builder and HttpLoggingInterceptor
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

        HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        logger.setLevel(HttpLoggingInterceptor.Level.BODY);

        //Add logger to client if it's DEBUG
        if (BuildConfig.DEBUG)
            clientBuilder.addInterceptor(logger);

        if (addUserIdToRequest) {
            //Add an Authorization (e.g. user_id) Header Interceptior
            clientBuilder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {

                    //get the built request
                    Request newRequest = chain.request();

                    //get a builder on the request
                    Request.Builder newRequestBuilder = newRequest.newBuilder();

                    newRequestBuilder.header("user_id", "user_id");

                    //continue the flow
                    return chain.proceed(newRequestBuilder.build());
                }
            });
        }

        if (retrofit == null)
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(clientBuilder.build())
                    .build();

        return retrofit;
    }

}
