package com.grotesque.saa.player;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.grotesque.saa.player.service.NaverInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 경환 on 2016-04-24.
 */
public class NaverBlogApi {
    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl("http://serviceapi.nmv.naver.com/mobile/")
                    .addConverterFactory(GsonConverterFactory.create(gson));
    private static NaverInterface mNaverService;

    public static NaverInterface getInstance(){
        if(mNaverService == null) {
            mNaverService = builder.build().create(NaverInterface.class);
        }
        return mNaverService;
    }

}
