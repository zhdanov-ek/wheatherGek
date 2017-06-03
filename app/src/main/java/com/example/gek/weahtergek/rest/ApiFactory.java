package com.example.gek.weahtergek.rest;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactory {

    public static WeatherInterface getWeatherInterface() {
        OkHttpClient.Builder builderHttp = new OkHttpClient.Builder();
        builderHttp.readTimeout(10, TimeUnit.SECONDS);
        builderHttp.writeTimeout(10, TimeUnit.SECONDS);
        OkHttpClient client = builderHttp.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://dataservice.accuweather.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit.create(WeatherInterface.class);
    }

    public static ConditionInterface getConditionInterface(){
        OkHttpClient.Builder builderHttp = new OkHttpClient.Builder();
        builderHttp.readTimeout(10, TimeUnit.SECONDS);
        builderHttp.writeTimeout(10, TimeUnit.SECONDS);
        OkHttpClient client = builderHttp.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://dataservice.accuweather.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit.create(ConditionInterface.class);
    }

}