package com.yakisan.springrestapitest.service;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {
    //BASE URL -> https://appdemobmkint.azurewebsites.net/
    private static String BASE_URL = "http://10.0.2.2:8080/";

    private static Retrofit retrofit = null;

    public static APIService getRetrofitClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(APIService.class);

    }
}
