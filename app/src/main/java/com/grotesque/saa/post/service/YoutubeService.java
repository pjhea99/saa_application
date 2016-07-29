package com.grotesque.saa.post.service;

import com.grotesque.saa.post.data.YouTubeData;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by 경환 on 2016-04-27.
 */
public interface YoutubeService {
    @GET("search")
    Call<YouTubeData> getYoutubeData(@QueryMap HashMap<String, String> query);
}
