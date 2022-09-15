package com.example.digitalturbinedtwalltest;

import com.example.digitalturbinedtwalltest.Network.DWWallApiService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static Retrofit retrofit = null;

    static Retrofit getClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        retrofit = new Retrofit.Builder()
                .baseUrl(DWWallApiService.BASE_URl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();


//        retrofit = new Retrofit.Builder()
//                .baseUrl(DWWallApiService.BASE_URl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();


        return retrofit;
    }
}
