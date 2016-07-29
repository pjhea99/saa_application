package com.grotesque.saa.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.grotesque.saa.R;
import com.grotesque.saa.content.data.VideoItem;
import com.grotesque.saa.player.DaumApi;
import com.grotesque.saa.player.NaverBlogApi;
import com.grotesque.saa.player.NaverCastApi;
import com.grotesque.saa.player.data.naver.Meta;
import com.grotesque.saa.player.data.naver.NaverData;
import com.grotesque.saa.player.data.naver.Videos;
import com.grotesque.saa.util.NavigationUtils;
import com.grotesque.saa.util.StringUtils;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.grotesque.saa.util.LogUtils.LOGE;

/**
 * Created by 경환 on 2016-04-24.
 */
public class ThumbnailView extends FrameLayout implements View.OnClickListener {
    private static final String TAG = ThumbnailView.class.getSimpleName();

    private Context mContext;
    private HashMap<String, String> mQuery = new HashMap<>();
    private VideoItem mVideoItem;

    private String mVideoUrl;
    private ImageView mThumbnail;
    private ImageView mPlayIcon;
    private TextView mError;


    public ThumbnailView(Context context) {
        super(context);
        mContext = context;
        initUI();
    }

    public ThumbnailView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initUI();
    }

    public ThumbnailView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initUI();
    }

    private void initUI(){
        LayoutInflater.from(mContext).inflate(R.layout.layout_thumbnail_view, this, true);
        mThumbnail = (ImageView) findViewById(R.id.img_thumbnail);
        mPlayIcon = (ImageView) findViewById(R.id.img_play_ico);
        mPlayIcon.setOnClickListener(this);
        mError = (TextView) findViewById(R.id.text_error);
    }
    public void setNaver(final VideoItem naver){
        LOGE(TAG, naver.getVid());
        LOGE(TAG, naver.getOutKey());
        mVideoItem = naver;
        mQuery = StringUtils.setCastQuery(naver.getOutKey());

        NaverCastApi.getInstance()
                .getCastVideoId(naver.getVid()+"?", mQuery)
                .enqueue(castCallback);
    }
    public void setDaum(final String vid){
        mQuery = StringUtils.setDaumQuery(vid);
        DaumApi.getInstance().getDaumVideoId(mQuery).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.body().get("url") != null) {
                    mVideoUrl = response.body().get("url").toString();

                    if (mVideoUrl.contains("http://cdn")) {
                        mVideoUrl = mVideoUrl.replace("http://cdn", "http://tvpot.cdn");
                    }
                    loadImage("http://t1.daumcdn.net/tvpot/thumb/"+vid+"/thumb.png");
                }else {
                    if(response.body().get("name").toString().contains("DataNotFoundException")){
                        mPlayIcon.setVisibility(GONE);
                        mError.setText("삭제된 동영상 입니다.");
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    public void loadImage(String url){
        Glide.with(mThumbnail.getContext())
                .load(url)
                .into(mThumbnail);
    }

    @Override
    public void onClick(View v) {
        LOGE(TAG, mVideoUrl);
        NavigationUtils.goPlayerActivity(mContext, mVideoUrl);
    }
    private Callback<NaverData> castCallback = new Callback<NaverData>() {
        @Override
        public void onResponse(Call<NaverData> call, Response<NaverData> response) {
            NaverData naverData = response.body();
            if(naverData.getVideos() != null) {
                for (com.grotesque.saa.player.data.naver.List list : naverData.getVideos().getList()) {
                    if (list.getEncodingOption().getName().equals("720P"))
                        mVideoUrl = list.getSource();
                }
                loadImage(response.body().getMeta().getCover().getSource());
            }else{
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("videoId", mVideoItem.getVid());
                hashMap.put("outKey", mVideoItem.getOutKey());
                NaverBlogApi
                        .getInstance()
                        .getBlogVideoId(hashMap)
                        .enqueue(blogCallback);
            }
        }

        @Override
        public void onFailure(Call<NaverData> call, Throwable t) {

        }
    };

    private Callback<JsonObject> blogCallback = new Callback<JsonObject>() {
        @Override
        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
            LOGE(TAG, response.body().toString());
            if(response.body().get("errorMessage") == null) {
                Gson gson = new Gson();
                Videos naverVideo = gson.fromJson(response.body().get("videos"), Videos.class);
                for (com.grotesque.saa.player.data.naver.List list : naverVideo.getList()) {
                    LOGE(TAG, list.getEncodingOption().getName());
                    if (list.getEncodingOption().getName().equals("720P"))
                        mVideoUrl = list.getSource();
                }
                loadImage(gson.fromJson(response.body().get("meta"), Meta.class).getCover().getSource());
            }else{
                LOGE(TAG, response.body().get("errorMessage").toString());
            }
        }

        @Override
        public void onFailure(Call<JsonObject> call, Throwable t) {
            LOGE(TAG, t.toString());
        }
    };
}
