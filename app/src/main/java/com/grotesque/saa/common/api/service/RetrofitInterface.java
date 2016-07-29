package com.grotesque.saa.common.api.service;

import com.google.gson.JsonObject;
import com.grotesque.saa.board.data.NoticeContainer;
import com.grotesque.saa.common.data.ResponseData;
import com.grotesque.saa.content.data.CommentContainer;
import com.grotesque.saa.home.data.DocumentContainer;
import com.grotesque.saa.post.data.ContentContainer;
import com.grotesque.saa.search.data.SearchItemContainer;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by 경환 on 2016-04-03.
 */
public interface RetrofitInterface {
    @Headers({
            "Connection: keep-alive",
            "Accept: application/json",
            "Accept-Encoding: identity",
            "Content-Type: application/json; charset=utf-8",
            "Accept-Language: ko-KR, ko",
    })
    @GET("index.php")
    Call<DocumentContainer> getBoardList(@QueryMap HashMap<String, String> query);

    @Headers({
            "Connection: keep-alive",
            "Accept: application/json",
            "Accept-Encoding: identity",
            "Content-Type: application/json; charset=utf-8",
            "Accept-Language: ko-KR, ko",
    })
    @GET("index.php")
    Call<NoticeContainer> getNoticeList(@QueryMap HashMap<String, String> query);




    @Headers({
            "Connection: keep-alive",
            "Accept: application/json",
            "Accept-Encoding: identity",
            "Content-Type: application/json; charset=utf-8",
            "Accept-Language: ko-KR, ko",
    })
    @GET("index.php")
    Call<SearchItemContainer> getBoardView(@QueryMap HashMap<String, String> query);


    @Headers({
            "Connection: keep-alive",
            "Accept: application/json",
            "Accept-Encoding: identity",
            "Content-Type: application/json; charset=utf-8",
            "Accept-Language: ko-KR, ko",
    })
    @GET("index.php")
    Call<ContentContainer> getPostBoardContent(@QueryMap HashMap<String, String> query);
    @Headers({
            "Connection: keep-alive",
            "Accept: application/json",
            "Accept-Encoding: identity",
            "Content-Type: application/json; charset=utf-8",
            "Accept-Language: ko-KR, ko",
    })
    @GET("index.php")
    Call<CommentContainer> getCommentList(@QueryMap HashMap<String, String> query);

    @Headers({
            "Connection: keep-alive",
            "Accept: application/json",
            "Accept-Encoding: identity",
            "Content-Type: application/json; charset=utf-8",
            "Accept-Language: ko-KR, ko",
    })
    @FormUrlEncoded
    @POST("index.php?act=procMemberLogin")
    Call<ResponseData> procMemberLogin(@Field("user_id") String id, @Field("password") String password, @Field("keep_signed") String keep_signed);

    @Headers({
            "Connection: keep-alive",
            "Accept: application/json",
            "Accept-Encoding: identity",
            "Accept-Language: ko-KR, ko",
            "Content-Type: application/json; charset=utf-8",
    })
    @FormUrlEncoded
    @POST("index.php?act=procBoardInsertDocument")
    Call<JsonObject> submitDocument(@Field("mid") String mid, @Field("document_srl") String document_srl, @Field("title") String title, @Field("content") String content, @Field("tags") String tags, @Header("Referer") String referer);


    @Headers({
            "Connection: keep-alive",
            "Accept: application/json",
            "Accept-Encoding: identity",
            "Accept-Language: ko-KR, ko",
            "Content-Type: application/json; charset=utf-8",
    })
    @FormUrlEncoded
    @POST("index.php?act=procBoardInsertComment")
    Call<JsonObject> submitComment(@Field("mid") String mid, @Field("parent_srl") String parent_srl, @Field("document_srl") String document_srl, @Field("comment_srl") String comment_srl, @Field("content") String content, @Header("Referer") String referer);


    @Headers({
            "Connection: keep-alive",
            "Accept: application/json",
            "Accept-Encoding: identity",
            "Accept-Language: ko-KR, ko",
            "Content-Type: application/json; charset=utf-8",
    })
    @FormUrlEncoded
    @POST("index.php?act=procBoardVoteDocument")
    Call<ResponseData> procBoardVoteDocument(@Field("document_srl") String document_srl);

    @Headers({
            "Connection: keep-alive",
            "Accept: application/json",
            "Accept-Encoding: identity",
            "Accept-Language: ko-KR, ko",
            "Content-Type: application/json; charset=utf-8",
    })
    @FormUrlEncoded
    @POST("index.php?act=procBoardDeleteComment")
    Call<JsonObject> procBoardDeleteComment(@Field("mid") String mid, @Field("document_srl") String document_srl, @Field("comment_srl") String comment_srl, @Header("Referer") String referer);

    @Headers({
            "Connection: keep-alive",
            "Accept: application/json",
            "Accept-Encoding: identity",
            "Accept-Language: ko-KR, ko",
            "Content-Type: application/json; charset=utf-8",
    })
    @GET("index.php?act=dispMemberInfo&mid={mid}")
    Call<String> dispMemberInfo(@Path("mid") String mid, @Header("Referer") String referer);



}
