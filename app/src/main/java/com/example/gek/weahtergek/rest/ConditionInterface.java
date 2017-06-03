package com.example.gek.weahtergek.rest;


import com.example.gek.weahtergek.models.Condition;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ConditionInterface {

    @GET("currentconditions/v1/1-321985_1_AL")
    Call<List<Condition>> getCondition(@Query("apikey") String apiKey);
}


