package com.example.categories.api.service;


import com.example.categories.api.models.Data;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataClient {

    //https://api.myjson.com/bins/chou4

    @GET("bins/chou4")
    Call<Data> getData();
}