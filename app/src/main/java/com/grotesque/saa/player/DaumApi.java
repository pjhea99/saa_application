package com.grotesque.saa.player;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.grotesque.saa.player.service.DaumInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 경환 on 2016-04-29.
 */
public class DaumApi {
    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl("http://videofarm.daum.net/controller/api/open/v1_4/")
                    .addConverterFactory(GsonConverterFactory.create(gson));
    private static DaumInterface mDaumService;
    public static DaumInterface getInstance(){
        if(mDaumService == null) {
            mDaumService = builder.build().create(DaumInterface.class);
        }
        return mDaumService;
    }
}
