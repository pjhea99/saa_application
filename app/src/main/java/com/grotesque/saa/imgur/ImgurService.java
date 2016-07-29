package com.grotesque.saa.imgur;

import com.grotesque.saa.imgur.response.ImageItemResponse;
import com.grotesque.saa.post.data.ImageResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by AKiniyalocts on 2/23/15.
 * <p/>
 * This is our imgur API. It generates a rest API via Retrofit from Square inc.
 * <p/>
 * more here: http://square.github.io/retrofit/
 */
public interface ImgurService {
    String server = "https://api.imgur.com/";


    /****************************************
     * Upload
     * Image upload API
     */
    @GET("3/image/{itemId}")
    Call<ImageItemResponse> imageItem(@Header("Authorization") String auth, @Path("itemId") String paramString);

    @POST("3/image")
    Call<ImageResponse> postImage(
            @Header("Authorization") String auth,
            @Body RequestBody body
    );
}
