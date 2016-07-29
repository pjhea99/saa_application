package com.grotesque.saa.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.grotesque.saa.BuildConfig;
import com.grotesque.saa.R;

/**
 * Created by 경환 on 2016-04-24.
 */
public class YoutubeThumbnailView extends RelativeLayout implements View.OnClickListener {
    private YouTubeThumbnailView mThumbnailView;
    private ImageView mPlayIcon;
    private Context mContext;
    private OnClickListener mOnClickListener;

    public YoutubeThumbnailView(Context context) {
        super(context);
        mContext = context;
        initUI();
    }

    public YoutubeThumbnailView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initUI();
    }

    public YoutubeThumbnailView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initUI();
    }
    private void initUI(){
        LayoutInflater.from(mContext).inflate(R.layout.layout_youtubue_thumbnail_view, this, true);
        mThumbnailView = (YouTubeThumbnailView) findViewById(R.id.img_youtube_thumbnail);
        mPlayIcon = (ImageView) findViewById(R.id.img_play_ico);
        mPlayIcon.setOnClickListener(this);
    }
    public void loadImage(final String videoId){
        mThumbnailView.initialize(BuildConfig.YOUTUBE_API_KEY, new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {
                youTubeThumbnailLoader.setVideo(videoId);
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }
    public void setYouTubeThumbnailListener(OnClickListener onClickListener){
        mOnClickListener = onClickListener;
    }
    @Override
    public void onClick(View v) {
        if(mOnClickListener != null){
            mOnClickListener.playYoutube();
        }
    }
    public interface OnClickListener{
        void playYoutube();
    }
}
