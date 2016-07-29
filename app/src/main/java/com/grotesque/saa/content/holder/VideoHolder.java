package com.grotesque.saa.content.holder;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.grotesque.saa.R;
import com.grotesque.saa.common.holder.BaseViewHolder;
import com.grotesque.saa.common.widget.ThumbnailView;
import com.grotesque.saa.common.widget.YoutubeThumbnailView;
import com.grotesque.saa.content.data.ContentItem;
import com.grotesque.saa.util.WebViewUtils;
import com.grotesque.saa.util.YouTubeUtils;

import java.util.List;

import static com.grotesque.saa.util.LogUtils.LOGE;

/**
 * Created by 경환 on 2016-05-10.
 */
public class VideoHolder extends BaseViewHolder<ContentItem> {

    private static final String TAG = VideoHolder.class.getSimpleName();

    private LinearLayout mRootView;
    private ThumbnailView thumbnailView;
    private YoutubeThumbnailView youtubeThumbnailView;
    private WebView webView;

    public static VideoHolder newInstance(Context context, ViewGroup parent){
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_postview_video_iframe, parent, false);
        return new VideoHolder(context, v);
    }
    
    public VideoHolder(Context context, View itemView) {
        super(context, itemView);
        mRootView = (LinearLayout) itemView.findViewById(R.id.root_view);
    }

    @Override
    public void onBindView(ContentItem contentItem) {

    }

    @Override
    public void onBindView(List<ContentItem> item, int position) {

        mRootView.removeAllViews();
        int videoType = item.get(position).getVideo().getType();



        switch (videoType){
            case 0:
                thumbnailView = new ThumbnailView(getContext());
                mRootView.addView(thumbnailView);
                thumbnailView.setNaver(item.get(position).getVideo());
                break;
            case 1:
                thumbnailView = new ThumbnailView(getContext());
                mRootView.addView(thumbnailView);
                thumbnailView.setDaum(item.get(position).getVideo().getVid());
                break;
            case 2:
                youtubeThumbnailView = new YoutubeThumbnailView(getContext());
                mRootView.addView(youtubeThumbnailView);
                final String videoId = item.get(position).getVideo().getVid();
                youtubeThumbnailView.setYouTubeThumbnailListener(new YoutubeThumbnailView.OnClickListener() {
                    @Override
                    public void playYoutube() {
                        YouTubeUtils.showYouTubeVideo(videoId, (Activity) getContext());
                    }
                });
                youtubeThumbnailView.loadImage(videoId);
                break;
            default:
                webView = new WebView(getContext());
                WebViewUtils.setWebview(webView);
                mRootView.addView(webView);

                LOGE(TAG, item.get(position).getVideo().getUrl().attr("src"));
                webView.loadDataWithBaseURL("http://highbury.co.kr", "<body style='margin:0px;padding:0px;'> <embed src=\"" + item.get(position).getVideo().getUrl().attr("src") + "\" style=\"max-width: 100%;width: 100%; height: auto\"></embed>" , "text/html", "UTF-8", null);

        }
    }

    @Override
    public void onBindView(List<ContentItem> item, int position, String mid) {

    }
}
