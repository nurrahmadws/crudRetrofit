package com.example.matatabi.crudretrofit.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String base_url = "http://192.168.43.207/lagi/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;

    private RetrofitClient(){
//        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getmInstance(){
        if (mInstance == null){
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public ApiRequest getApi(){
        return retrofit.create(ApiRequest.class);
    }

}
