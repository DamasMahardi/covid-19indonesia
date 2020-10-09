package com.example.covid19.network;

import com.example.covid19.MainModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndpoint {
//    @GET("indonesia")
//    Call<List<MainModel>> getData();

    @GET("indonesia")
    Call<List<MainModel>> getData();
}
