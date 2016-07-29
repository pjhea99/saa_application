package com.grotesque.saa.post;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.grotesque.saa.post.service.YoutubeService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 경환 on 2016-04-27.
 */
public class YoutubeApi {
    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl("https://www.googleapis.com/youtube/v3/")
                    .addConverterFactory(GsonConverterFactory.create(gson));
    private static YoutubeService mService;

    public static YoutubeService getInstance() {
        if (mService == null)
            mService = builder.build().create(YoutubeService.class);
        return mService;
    }
}
