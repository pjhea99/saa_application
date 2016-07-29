package com.grotesque.saa.player.service;

import com.google.gson.JsonObject;
import com.grotesque.saa.player.data.naver.NaverData;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by 경환 on 2016-04-24.
 */
public interface NaverInterface {
    @GET("")
    Call<NaverData> getCastVideoId(@Url String url, @QueryMap HashMap<String, String> query);

    @GET("getVideoInfo.nhn?")
    Call<JsonObject> getBlogVideoId(@QueryMap HashMap<String, String> query);
}
