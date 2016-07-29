package com.grotesque.saa.content.holder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.load.resource.bitmap.StreamBitmapDecoder;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.grotesque.saa.R;
import com.grotesque.saa.common.holder.BaseViewHolder;
import com.grotesque.saa.content.data.ContentItem;
import com.grotesque.saa.imageloader.MyProgressTarget;
import com.grotesque.saa.imageloader.ProgressTarget;
import com.grotesque.saa.imgur.ImgurApi;
import com.grotesque.saa.imgur.data.ImageItem;
import com.grotesque.saa.imgur.response.ImageItemResponse;
import com.grotesque.saa.post.helper.Constants;
import com.grotesque.saa.util.BitmapBitmapResourceDecoder;
import com.grotesque.saa.util.PassthroughModelLoader;
import com.grotesque.saa.util.StringUtils;
import com.grotesque.saa.util.UIUtils;
import com.grotesque.saa.util.UrlRouter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.grotesque.saa.util.LogUtils.LOGE;

/**
 * Created by 경환 on 2016-05-10.
 */
public class GlideHolder extends BaseViewHolder<ContentItem> {
    private static final String TAG = GlideHolder.class.getSimpleName();
    private String mUrl;
    private FrameLayout mGifView;
    private LinearLayout mImageLayout;
    private ImageView mImageView;
    private TextView mError;
    private TextView mProgress;
    private RecyclerView mRecyclerView;
    private ProgressTarget<String, GlideDrawable> mTarget;
    private GenericRequestBuilder<Bitmap, Bitmap, Bitmap, Bitmap> mBitmapRequest;

    public static GlideHolder newInstance(Context context, ViewGroup parent){
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_postview_glide, parent, false);
        return new GlideHolder(parent.getContext(), v);
    }

    public GlideHolder(Context context, View itemView) {
        super(context, itemView);
        this.mImageLayout = (LinearLayout) itemView.findViewById(R.id.ll_image);
        this.mImageView = (ImageView) itemView.findViewById(R.id.iv_photo1);
        this.mError = (TextView) itemView.findViewById(R.id.text_error);
        this.mProgress = (TextView) itemView.findViewById(R.id.text_progress);
        this.mGifView = (FrameLayout) itemView.findViewById(R.id.gif_btn_play);
        this.mRecyclerView = (RecyclerView) itemView.findViewById(R.id.tall_image_list);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.mTarget = new MyProgressTarget<>(new GlideDrawableImageViewTarget(mImageView){
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {

                float resized_w = UIUtils.getScreenSize().x;
                float ratio =  resized_w / (float)resource.getIntrinsicWidth();
                float resized_h = ratio * resource.getIntrinsicHeight();
                int maxTextureSize = UIUtils.getMaxTextureSize();

                LOGE(TAG, "ratio : " + ratio);
                LOGE(TAG, "resized height : " + resized_h);
                LOGE(TAG, "resized width : " + resized_w);

                if (resource.getIntrinsicHeight() > maxTextureSize) {
                    resized_h = maxTextureSize ;

                    Bitmap bitmap = Bitmap.createBitmap(resource.getIntrinsicWidth(), resource.getIntrinsicHeight(), Bitmap.Config.RGB_565);
                    Canvas canvas = new Canvas(bitmap);
                    resource.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                    resource.draw(canvas);

                    int image_num = (int)(bitmap.getHeight() / resized_h)+1;
                    LOGE(TAG, "image_num : " +  image_num);
                    Bitmap[] images = new Bitmap[image_num];
                    for(int i=0; i<image_num; i++){
                        if(i+1 == image_num)
                            images[i] = Bitmap.createBitmap(bitmap, 0, i*(int)resized_h, bitmap.getWidth(), bitmap.getHeight() - i*(int)resized_h);
                        else
                            images[i] = Bitmap.createBitmap(bitmap, 0, i*(int)resized_h, bitmap.getWidth(), (int)resized_h);

                    }
                    bitmap.recycle();
                    for(int i=1; i<image_num; i++){
                        ImageView iv = new ImageView(getContext());
                        iv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        iv.setScaleType(ImageView.ScaleType.FIT_XY);
                        iv.setAdjustViewBounds(true);
                        mImageLayout.addView(iv);
                        bindBitmap(images[i], iv);
                    }
                    resource = new GlideBitmapDrawable(null, images[0]);
                    resized_h = resized_h*ratio;
                }

                view.getLayoutParams().height = (int) resized_h;
                view.getLayoutParams().width = (int) resized_w;

                super.onResourceReady(resource, animation);
            }

            @Override
            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                super.onLoadFailed(e, errorDrawable);
                if(e != null) {

                    if(e.toString().contains("code: 403")){
                        mError.setText("403 ERROR");
                    }else if(e.toString().contains("code: 404")){
                        mError.setText("404 ERROR");
                    }else if(e.toString().contains("TimeOut")){
                        mError.setText("TimeOut");
                    }
                }
            }

            @Override
            protected void setResource(GlideDrawable resource) {


                super.setResource(resource);
            }
        }, mImageView, mProgress);

    }

    @Override
    public void onBindView(ContentItem contentItem) {

    }

    @Override
    public void onBindView(List<ContentItem> item, int position) {
        mUrl = StringUtils.convertImgUrl(item.get(position).getImg());

        if(mUrl.startsWith("http://imgur.com")){
            String img_id = UrlRouter.getIdFromUrl(Uri.parse(item.get(position).getImg()));
            ImgurApi.getInstance()
                    .imageItem(Constants.getClientAuth(), img_id)
                    .enqueue(new Callback<ImageItemResponse>() {
                        @Override
                        public void onResponse(Call<ImageItemResponse> call, Response<ImageItemResponse> response) {
                            if(response.code() == 200) {
                                ImageItem imageitem = response.body().getData();
                                mUrl = imageitem.getLink();
                                bindUrl();
                            }else{

                            }

                        }

                        @Override
                        public void onFailure(Call<ImageItemResponse> call, Throwable t) {

                        }
                    });
        }else{
            bindUrl();
        }
    }

    @Override
    public void onBindView(List<ContentItem> item, int position, String mid) {

    }

    void bindUrl() {
        mTarget.setModel(mUrl); // update target's cache
        if(mUrl.contains(".gif")){
            mImageView.setVisibility(View.GONE);
            mGifView.setVisibility(View.VISIBLE);
            mGifView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mImageView.setVisibility(View.VISIBLE);
                    mGifView.setVisibility(View.GONE);
                    Glide
                            .with(mImageView.getContext())
                            .load(mUrl)
                            .override(UIUtils.getScreenSize().x, Target.SIZE_ORIGINAL)
                            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                            .placeholder(R.drawable.progress_download_img)
                            .error(R.drawable.ic_error)
                            .into(mTarget);
                }
            });
        }else{
            mImageView.setVisibility(View.VISIBLE);
            mGifView.setVisibility(View.GONE);
            Glide
                    .with(mImageView.getContext())
                    .load(mUrl)
                    .skipMemoryCache(true)
                    .override(UIUtils.getScreenSize().x, Target.SIZE_ORIGINAL)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .placeholder(R.drawable.progress_download_img)
                    .error(R.drawable.ic_error)
                    .into(mTarget);
        }

    }
    void bindBitmap(Bitmap bitmap, ImageView view) {
        mBitmapRequest = Glide
                .with(view.getContext())
                .using(new PassthroughModelLoader<Bitmap, Bitmap>(), Bitmap.class)
                .from(Bitmap.class)
                .as(Bitmap.class)
                .decoder(new BitmapBitmapResourceDecoder(view.getContext()))
                .cacheDecoder(new FileToStreamDecoder<Bitmap>(new StreamBitmapDecoder(view.getContext())))
                .encoder(new BitmapEncoder());
        mBitmapRequest
                .clone()
                .load(bitmap)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(view);
    }
}
