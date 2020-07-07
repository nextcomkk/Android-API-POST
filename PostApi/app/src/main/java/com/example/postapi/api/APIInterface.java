package com.example.postapi.api;

import com.example.postapi.model.Request;
import com.example.postapi.model.Response;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface APIInterface {
    @Headers({
            "Accept: application/json",
            "Content-type: application/json"
    })
    @POST()
    Call<Response> getPoint(
            @Url String url,
            @Body Request request
            );
}