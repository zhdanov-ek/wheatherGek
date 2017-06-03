package com.example.gek.weahtergek.rest;


import com.example.gek.weahtergek.models.City;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherInterface {
    @GET("locations/v1/cities/search")
    Call<List<City>> getCities(@Query("apikey") String apiKey,
                               @Query("q") String cityName);
}
