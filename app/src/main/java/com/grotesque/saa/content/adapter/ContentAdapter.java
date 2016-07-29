package com.grotesque.saa.content.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.grotesque.saa.R;
import com.grotesque.saa.common.widget.ThumbnailView;
import com.grotesque.saa.common.widget.YoutubeThumbnailView;
import com.grotesque.saa.content.data.ContentItem;
import com.grotesque.saa.content.tall_image.ImageChunkAdapter;
import com.grotesque.saa.imageloader.MyProgressTarget;
import com.grotesque.saa.imageloader.ProgressTarget;
import com.grotesque.saa.imgur.ImgurApi;
import com.grotesque.saa.imgur.data.ImageItem;
import com.grotesque.saa.imgur.response.ImageItemResponse;
import com.grotesque.saa.post.helper.Constants;
import com.grotesque.saa.util.FontManager;
import com.grotesque.saa.util.NavigationUtils;
import com.grotesque.saa.util.ParseUtils;
import com.grotesque.saa.util.StringUtils;
import com.grotesque.saa.util.UIUtils;
import com.grotesque.saa.util.UrlRouter;
import com.grotesque.saa.util.YouTubeUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.grotesque.saa.util.LogUtils.LOGE;
import static com.grotesque.saa.util.LogUtils.makeLogTag;


public class ContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements AbsListView.RecyclerListener{
    private static final String TAG = makeLogTag("ContentAdapter");

    public static final int VIEW_TYPE_IMGUR = 0;
    public static final int VIEW_TYPE_IMAGE = 1;
    public static final int VIEW_TYPE_TEXT = 2;
    public static final int VIEW_TYPE_VIDEO = 3;
    public static final int VIEW_TYPE_NEXT = 4;
    public static final int VIEW_TYPE_PREV = 5;
    public static final int VIEW_TYPE_COVER_TEXT = 6;
    public static final int VIEW_TYPE_TAGS = 7;
    public static final int VIEW_TYPE_WRITER_INFO = 10;

    private ArrayList<ContentItem> mArrayList = new ArrayList<>();
    protected Context mContext;
    protected LayoutInflater mInflater;
    int widthPixels;

    public ContentAdapter(Context context, ArrayList<ContentItem> arrayList){
        this.mContext = context;
        this.mArrayList = arrayList;
        this.mInflater = LayoutInflater.from(context);
        this.widthPixels = mContext.getResources().getDisplayMetrics().widthPixels;
    }
    public class ViewCoverTextHolder extends RecyclerView.ViewHolder{
        LinearLayout coverLayout;
        LinearLayout coverBodyLayout;
        LinearLayout titleLayout;
        TextView titleView;
        public ViewCoverTextHolder(View itemView) {
            super(itemView);
            coverLayout = (LinearLayout) itemView.findViewById(R.id.ll_trans_body);
            coverBodyLayout = (LinearLayout) itemView.findViewById(R.id.view_trans_inner);
            titleLayout = (LinearLayout) itemView.findViewById(R.id.ll_title);
            titleView = (TextView) itemView.findViewById(R.id.txt_coverview_title);
            titleView.setTextColor(Color.parseColor("#00000000"));
        }
    }
    public class ViewTextHolder extends RecyclerView.ViewHolder{
        TextView text;
        public ViewTextHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.txt_postview_text);
            text.setTypeface(FontManager.getInstance(mContext).getTypeface());
            text.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }
    public class ViewImageGlideHolder extends RecyclerView.ViewHolder{
        private String url;
        private FrameLayout btn_gif;
        private ImageView imageView01;
        private TextView txt_error;
        private TextView txt_progress;
        private RecyclerView list;
        private ProgressTarget<String, GlideDrawable> glideDrawableTarget;
        public ViewImageGlideHolder(final View itemView) {
            super(itemView);
            imageView01 = (ImageView) itemView.findViewById(R.id.iv_photo1);
            txt_error = (TextView) itemView.findViewById(R.id.text_error);
            txt_progress = (TextView) itemView.findViewById(R.id.text_progress);
            btn_gif = (FrameLayout) itemView.findViewById(R.id.gif_btn_play);
            list = (RecyclerView) itemView.findViewById(R.id.tall_image_list);
            list.setLayoutManager(new LinearLayoutManager(mContext));
            glideDrawableTarget = new MyProgressTarget<>(new GlideDrawableImageViewTarget(imageView01){

                /**
                 * 이미지의 가로 폭은 화면 최대로 고정
                 * 원본 이미지의 가로 폭이 화면과 다를 경우 비율 값을 통해 변경될 세로폭 사이즈를 구한다
                 * 리사이즈된 가로 세로폭을 이미지뷰에 적용
                 * fitXY를 통해 resource를 이미지뷰에 꽉 채운다
                 *
                 * @param resource
                 * @param animation
                 */
                @Override
                public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                    LOGE(TAG, "resource height : " + resource.getIntrinsicHeight());
                    LOGE(TAG, "resource width : " + resource.getIntrinsicWidth());

                    float resized_w = UIUtils.getScreenSize().x;
                    float ratio =  resized_w / (float)resource.getIntrinsicWidth();
                    float resized_h = ratio * resource.getIntrinsicHeight();
                    int maxTextureSize = UIUtils.getMaxTextureSize();

                    /**
                     * 이미지의 세로폭이 maxTextureSize 보다 큰 경우
                     */

                    if (resource.getIntrinsicHeight() > maxTextureSize) {
                        imageView01.setVisibility(View.GONE);
                        list.setVisibility(View.VISIBLE);
                        list.setAdapter(new ImageChunkAdapter(UIUtils.getScreenSize(), url, new Point(resource.getIntrinsicWidth(), resource.getIntrinsicHeight())));
                    }

                    view.getLayoutParams().height = (int) resized_h;
                    view.getLayoutParams().width = (int) resized_w;

                    super.onResourceReady(resource, animation);
                }

                @Override
                public void onLoadFailed(Exception e, Drawable errorDrawable) {
                    super.onLoadFailed(e, errorDrawable);
                    if(e != null) {
                        LOGE(TAG, e.toString());
                        if(e.toString().contains("code: 403")){
                            txt_error.setText("403 ERROR");
                        }else if(e.toString().contains("code: 404")){
                            txt_error.setText("404 ERROR");
                        }else if(e.toString().contains("TimeOut")){
                            txt_error.setText("TimeOut");
                        }
                    }
                }

                @Override
                protected void setResource(GlideDrawable resource) {


                    super.setResource(resource);
                }
            }, imageView01, txt_progress);
        }
        void bindUrl() {
            glideDrawableTarget.setModel(url); // update target's cache
            if(url.contains(".gif")){
                imageView01.setVisibility(View.GONE);
                btn_gif.setVisibility(View.VISIBLE);
                btn_gif.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        imageView01.setVisibility(View.VISIBLE);
                        btn_gif.setVisibility(View.GONE);
                        Glide
                                .with(imageView01.getContext())
                                .load(url)
                                .override(UIUtils.getScreenSize().x, Target.SIZE_ORIGINAL)
                                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                .placeholder(R.drawable.progress_download_img)
                                .error(R.drawable.ic_error)
                                .into(glideDrawableTarget);
                    }
                });
            }else{
                imageView01.setVisibility(View.VISIBLE);
                btn_gif.setVisibility(View.GONE);
                Glide
                        .with(imageView01.getContext())
                        .load(url)
                        .skipMemoryCache(true)
                        .override(UIUtils.getScreenSize().x, Target.SIZE_ORIGINAL)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .placeholder(R.drawable.progress_download_img)
                        .error(R.drawable.ic_error)
                        .into(glideDrawableTarget);
            }

        }
    }

    public class ViewImageFrescoHolder extends RecyclerView.ViewHolder{
        public ViewImageFrescoHolder(View itemView) {
            super(itemView);
        }
    }

    public class ViewVideoHolder extends RecyclerView.ViewHolder{        
        LinearLayout rootView;
        public ViewVideoHolder(View itemView) {
            super(itemView);
            
            rootView = (LinearLayout) itemView.findViewById(R.id.root_view);            
        }
    }
    public class ViewTagsHolder extends RecyclerView.ViewHolder{
        TextView tagsView;
        LinearLayout wrapperView;
        public ViewTagsHolder(View itemView) {
            super(itemView);
            tagsView = (TextView) itemView.findViewById(R.id.txt_postview_tags);
            wrapperView = (LinearLayout) itemView.findViewById(R.id.ll_postview_tags);

        }
    }
    public class ViewWriterInfoHolder extends RecyclerView.ViewHolder{
        TextView nickName;
        TextView idName;
        View sendView;
        public ViewWriterInfoHolder(View itemView) {
            super(itemView);
         
            nickName = (TextView) itemView.findViewById(R.id.txt_postview_writer_info_name);
            idName = (TextView) itemView.findViewById(R.id.txt_postview_writer_info_id);
            nickName.setTypeface(FontManager.getInstance(mContext).getTypeface());
            idName.setTypeface(FontManager.getInstance(mContext).getTypefaceItalic());
            sendView = itemView.findViewById(R.id.imgbtn_postview_writer_info_send_memo);

        }
    }
    public class ViewPrevHolder extends RecyclerView.ViewHolder{
        View topMargin;
        View coverView;
        ImageView imageView;
        TextView prevTitleView;
        TextView prevWriterView;
        public ViewPrevHolder(View itemView) {
            super(itemView);
            topMargin = itemView.findViewById(R.id.view_top_amount);
            coverView = itemView.findViewById(R.id.cover_prev);
            imageView = (ImageView) itemView.findViewById(R.id.img_prev);
            prevTitleView = (TextView) itemView.findViewById(R.id.txt_postview_prev_post_title);
            prevWriterView = (TextView) itemView.findViewById(R.id.txt_postview_prev_post_subtitle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavigationUtils.goContentActivity(mContext, "prev");
                }
            });
        }
    }
    public class ViewNextHolder extends RecyclerView.ViewHolder{
        View topMargin;
        View coverView;
        ImageView imageView;
        TextView nextTitleView;
        TextView nextWriterView;
        public ViewNextHolder(View itemView) {
            super(itemView);
            topMargin = itemView.findViewById(R.id.view_top_amount);
            coverView = itemView.findViewById(R.id.cover_next);
            imageView = (ImageView) itemView.findViewById(R.id.img_next);
            nextTitleView = (TextView) itemView.findViewById(R.id.txt_postview_next_post_title);
            nextWriterView = (TextView) itemView.findViewById(R.id.txt_postview_next_post_subtitle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavigationUtils.goContentActivity(mContext, "next");
                }
            });
        }
    }    
    public long getItemId(int position)
    {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if(viewType == VIEW_TYPE_TEXT){
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.layout_postview_text, viewGroup, false);
            return new ViewTextHolder(v);
        }else if(viewType == VIEW_TYPE_IMAGE) {
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.layout_postview_glide, viewGroup, false);
            return new ViewImageGlideHolder(v);
        }else if(viewType == VIEW_TYPE_VIDEO){
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.layout_postview_video_iframe, viewGroup, false);
            return new ViewVideoHolder(v);
        }else if(viewType == VIEW_TYPE_PREV){
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.layout_postview_prev_post, viewGroup, false);
            return new ViewPrevHolder(v);
        }else if(viewType == VIEW_TYPE_WRITER_INFO) {
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.layout_postview_writer_info, viewGroup, false);
            return new ViewWriterInfoHolder(v);
        }else if(viewType == VIEW_TYPE_COVER_TEXT) {
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.layout_postview_content_info_trans, viewGroup, false);
            return new ViewCoverTextHolder(v);
        }else if(viewType == VIEW_TYPE_TAGS) {
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.layout_postview_tags, viewGroup, false);
            return new ViewTagsHolder(v);
        }
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_postview_next_post, viewGroup, false);
        return new ViewNextHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof ViewCoverTextHolder){
            ViewCoverTextHolder vch = (ViewCoverTextHolder) holder;
            vch.titleView.setText(Html.fromHtml(mArrayList.get(position).getText()));
        }else if (holder instanceof ViewTextHolder){
            ViewTextHolder vth = (ViewTextHolder) holder;
            CharSequence trimmed = StringUtils.trimTrailingWhitespace(Html.fromHtml(mArrayList.get(position).getText()));
            vth.text.setText(trimmed);
        }else if(holder instanceof ViewImageGlideHolder) {
            final ViewImageGlideHolder vih = (ViewImageGlideHolder) holder;
            Glide.clear(vih.imageView01);
            final String imgUrl = StringUtils.convertImgUrl(mArrayList.get(position).getImg());

            if(imgUrl.startsWith("http://imgur.com")){
                String img_id = UrlRouter.getIdFromUrl(Uri.parse(mArrayList.get(position).getImg()));
                ImgurApi.getInstance()
                        .imageItem(Constants.getClientAuth(), img_id)
                        .enqueue(new Callback<ImageItemResponse>() {
                            @Override
                            public void onResponse(Call<ImageItemResponse> call, Response<ImageItemResponse> response) {
                                if(response.code() == 200) {
                                    ImageItem imageitem = response.body().getData();
                                    LOGE(TAG, imageitem.getLink());
                                    vih.url = imageitem.getLink();
                                    vih.bindUrl();
                                }else{
                                    LOGE(TAG, response.message());
                                }

                            }

                            @Override
                            public void onFailure(Call<ImageItemResponse> call, Throwable t) {

                            }
                        });
            }else{
                vih.url = imgUrl;
                vih.bindUrl();
            }

            vih.imageView01.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<ContentItem> temp = new ArrayList<>();
                    int i = 0;
                    int j = 0;

                    for (ContentItem c : mArrayList) {
                        if (c.getType().equals("image")) {
                            temp.add(c);
                            i++;
                            if(c == mArrayList.get(position))
                                j = i;
                        }
                    }
                    NavigationUtils.goImageViewerActivity(mContext, temp, j-1);
                }
            });
        }else if(holder instanceof ViewVideoHolder){
            final ViewVideoHolder vvh = (ViewVideoHolder) holder;
            vvh.rootView.removeAllViews();
            int videoType = mArrayList.get(position).getVideo().getType();
            LinearLayout webviewLayout = (LinearLayout) mInflater.inflate(R.layout.layout_webview, null);
            vvh.rootView.addView(webviewLayout);
            WebView webView = (WebView) webviewLayout.findViewById(R.id.wv_video);
            setWebview(webView);

            if(videoType == StringUtils.NAVER_VOD){
                vvh.rootView.removeView(webviewLayout);
                final ThumbnailView thumbnailView = new ThumbnailView(mContext);
                vvh.rootView.addView(thumbnailView);
                thumbnailView.setNaver(mArrayList.get(position).getVideo());

            }else if(videoType == StringUtils.DAUM_VOD){
                vvh.rootView.removeView(webviewLayout);
                final ThumbnailView thumbnailView = new ThumbnailView(mContext);
                vvh.rootView.addView(thumbnailView);
                thumbnailView.setDaum(mArrayList.get(position).getVideo().getVid());
            }else if(videoType == StringUtils.YOUTUBE_VOD){
                YoutubeThumbnailView youtubeThumbnailView = new YoutubeThumbnailView(mContext);
                vvh.rootView.addView(youtubeThumbnailView);
                final String videoId = mArrayList.get(position).getVideo().getVid();
                LOGE(TAG, videoId);
                youtubeThumbnailView.setYouTubeThumbnailListener(new YoutubeThumbnailView.OnClickListener() {
                    @Override
                    public void playYoutube() {
                        YouTubeUtils.showYouTubeVideo(videoId, (Activity) mContext);
                    }
                });
                youtubeThumbnailView.loadImage(videoId);
            }else if(videoType == StringUtils.IMGUR_VOD){
                //webView.loadDataWithBaseURL("http://highbury.co.kr", "<body style='margin:0px;padding:0px;'> <img src=" + StringUtils.convertVideo(mArrayList.get(position).getVideo(), videoType) + " style=\"max-width: 100%;width: 100%; height: auto\"></img>", "text/html", "UTF-8", null);
            }else if(videoType == StringUtils.GOOGLE_VOD){
                if(mArrayList.get(position).getVideo().toString().contains("<img")){
                    //webView.loadDataWithBaseURL("http://highbury.co.kr", "<body style='margin:0px;padding:0px;'> <img src=" + StringUtils.convertVideo(mArrayList.get(position).getVideo(), videoType) + " style=\"max-width: 100%;width: 100%; height: auto\"></img>", "text/html", "UTF-8", null);
                }else if(mArrayList.get(position).getVideo().toString().contains("<embed")){
                    //webView.loadDataWithBaseURL("http://highbury.co.kr", "<body style='margin:0px;padding:0px;'> <embed src=" + StringUtils.convertVideo(mArrayList.get(position).getVideo(), videoType) + " style=\"max-width: 100%;width: 100%; height: auto\"></embed>", "text/html", "UTF-8", null);
                }
            }else if(videoType == StringUtils.DAILY_VOD){
                //webView.loadDataWithBaseURL("http://highbury.co.kr", "<body style='margin:0px;padding:0px;'> <embed src=" + StringUtils.convertVideo(mArrayList.get(position).getVideo(), videoType) + " style=\"max-width: 100%;width: 100%; height: auto\"></embed>", "text/html", "UTF-8", null);
            }else if(videoType > 4){
                //webView.loadDataWithBaseURL("http://highbury.co.kr", StringUtils.convertVideo(mArrayList.get(position).getVideo(), videoType), "text/html", "UTF-8", null);
            }

        }else if(holder instanceof ViewTagsHolder){
            ViewTagsHolder vth = (ViewTagsHolder) holder;
            if(mArrayList.get(position).getText().equals(""))
                vth.wrapperView.setVisibility(View.GONE);
            else
                vth.tagsView.setText(Html.fromHtml(mArrayList.get(position).getText()));
        }else if(holder instanceof ViewPrevHolder){
            ViewPrevHolder vph = (ViewPrevHolder) holder;
            if(mArrayList.get(position).getDocumentData().hasImg()){
                Glide
                        .with(vph.imageView.getContext())
                        .load(ParseUtils.parseImgUrl(mArrayList.get(position).getDocumentData().getContent()))
                        .into(vph.imageView);
                vph.coverView.setBackgroundColor(Color.parseColor("#4c000000"));
            }else{
                Glide.clear(vph.imageView);
                vph.coverView.setBackgroundColor(Color.parseColor("#4c1e90ff"));
            }
            vph.prevTitleView.setText(Html.fromHtml(mArrayList.get(position).getDocumentData().getTitle()));
            vph.prevWriterView.setText(mArrayList.get(position).getDocumentData().getNickName());
        }else if(holder instanceof ViewNextHolder){
            ViewNextHolder vnh = (ViewNextHolder) holder;
            if(mArrayList.get(position).getDocumentData().hasImg()){
                Glide
                        .with(vnh.imageView.getContext())
                        .load(ParseUtils.parseImgUrl(mArrayList.get(position).getDocumentData().getContent()))
                        .into(vnh.imageView);
                vnh.coverView.setBackgroundColor(Color.parseColor("#4c000000"));
            }else{
                Glide.clear(vnh.imageView);
                vnh.coverView.setBackgroundColor(Color.parseColor("#4cFF0000"));
            }
            vnh.nextTitleView.setText(Html.fromHtml(mArrayList.get(position).getDocumentData().getTitle()));
            vnh.nextWriterView.setText(mArrayList.get(position).getDocumentData().getNickName());
        }else if(holder instanceof ViewWriterInfoHolder){
            ViewWriterInfoHolder vwih = (ViewWriterInfoHolder) holder;
            vwih.nickName.setText(mArrayList.get(position).getDocumentData().getNickName());
            vwih.idName.setText("@"+mArrayList.get(position).getDocumentData().getUserId());
        }
    }

    public int getItemViewType(int position) {
        ContentItem contentItem = mArrayList.get(position);
        if ("text".equals(contentItem.getType()))
            return VIEW_TYPE_TEXT;
        if ("image".equals(contentItem.getType()))
            return VIEW_TYPE_IMAGE;
        if ("video".equals(contentItem.getType()))
            return VIEW_TYPE_VIDEO;
        if ("PREV_POST".equals(contentItem.getType()))
            return VIEW_TYPE_PREV;
        if ("NEXT_POST".equals(contentItem.getType()))
            return VIEW_TYPE_NEXT;
        if ("WRITER_INFO".equals(contentItem.getType()))
            return VIEW_TYPE_WRITER_INFO;
        if ("COVER_TEXT".equals(contentItem.getType()))
            return VIEW_TYPE_COVER_TEXT;
        if ("imgur".equals(contentItem.getType()))
            return VIEW_TYPE_IMGUR;
        if ("tags".equals(contentItem.getType()))
            return VIEW_TYPE_TAGS;
        return !"POST_ITEM_TYPE_ERROR".equals(contentItem.getType()) ? 10 : 17;
    }

    @Override
    public void onMovedToScrapHeap(View view) {

    }




    private void setWebview(WebView wv) {

        //웹뷰 글씨 크기 지정
        wv.getSettings().setTextZoom(85);

        //웹뷰 캐시 관련
        wv.clearCache(true);
        wv.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setLoadWithOverviewMode(true);
        wv.getSettings().getPluginState();
        wv.getSettings().setPluginState(WebSettings.PluginState.ON);
        wv.getSettings().setSupportZoom(true);

        wv.getSettings().setSupportMultipleWindows(true);

        wv.setFocusable(false);
        wv.setHorizontalScrollBarEnabled(false);
        wv.setVerticalScrollBarEnabled(false);

        wv.setWebChromeClient(new WebChromeClient());
        wv.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                LOGE(TAG, url);
                if( url.startsWith("http:") || url.startsWith("https:") ) {
                    return false;
                }else if(url.contains("naverplayer")){
                    LOGE(TAG, "naverplayer");
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    PackageManager pMgr = mContext.getPackageManager();
                    List<ResolveInfo> activities = pMgr.queryIntentActivities(intent, 0);
                    boolean isIntentSafe = activities.size() > 0;
                    if (isIntentSafe) {
                        LOGE(TAG, "실행 가능한 앱 확인");
                        mContext.startActivity(intent);
                    }
                }

                return true;
            }
        });
    }
}
