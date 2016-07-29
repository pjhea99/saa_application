package com.grotesque.saa.player.service;

import com.google.gson.JsonObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by 경환 on 2016-04-24.
 */
public interface DaumInterface {
    @GET("MovieLocation.json")
    Call<JsonObject> getDaumVideoId(@QueryMap HashMap<String, String> query);
}
