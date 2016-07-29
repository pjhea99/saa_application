package com.grotesque.saa.imgur;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.grotesque.saa.MyApplication;
import com.grotesque.saa.post.data.ImageResponse;
import com.grotesque.saa.post.data.Upload;
import com.grotesque.saa.post.helper.Constants;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by AKiniyalocts on 1/12/15.
 * <p/>
 * Our upload service. This creates our restadapter, uploads our image, and notifies us of the response.
 */
public class ImgurApi {
    public final static String TAG = ImgurApi.class.getSimpleName();

    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(ImgurService.server)
                    .addConverterFactory(GsonConverterFactory.create(gson));
    private static ImgurService mService;

    public static void uploadImage(Upload upload, Callback<ImageResponse> callback) {
        final Callback<ImageResponse> cb = callback;

        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", upload.image.getName(),
                        RequestBody.create(MediaType.parse("multipart/form-data"), upload.image))
                .build();

        try {
            Log.e(TAG, "image length : " + body.contentLength());
            Log.e(TAG, "image type : " + body.contentType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(mService == null){
            mService = getInstance();
        }
        mService.postImage(
                Constants.getClientAuth(),
                body
        ).enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                if (response == null) {
                            /*
                             Notify image was NOT uploaded successfully
                            */
                    Toast.makeText(MyApplication.getInstance(), "이미지 업로드에 실패하였습니다.", Toast.LENGTH_LONG).show();
                    return;
                }
                        /*
                        Notify image was uploaded successfully
                        */


                if (response.body().success) {
                    if (cb != null) cb.onResponse(call, response);
                    Log.e("image height : ", String.valueOf(response.body().data.height));
                    Log.e("image width : ", String.valueOf(response.body().data.width));
                }

            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {
                if (cb != null) cb.onFailure(call, t);
                Log.d(TAG, String.valueOf(t));

            }
        });
    }

    public static ImgurService getInstance(){
        if(mService == null) {
            mService = builder.build().create(ImgurService.class);
        }
        return mService;
    }
}
