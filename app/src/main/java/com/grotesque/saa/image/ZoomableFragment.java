package com.grotesque.saa.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.facebook.drawee.view.SimpleDraweeView;
import com.grotesque.saa.R;
import com.grotesque.saa.content.data.ContentItem;
import com.grotesque.saa.imageloader.MyProgressTarget;
import com.grotesque.saa.imageloader.ProgressTarget;
import com.grotesque.saa.util.BitmapBitmapResourceDecoder;
import com.grotesque.saa.util.HasViews;
import com.grotesque.saa.util.OnViewChangedNotifier;
import com.grotesque.saa.util.PassthroughModelLoader;
import com.grotesque.saa.util.StringUtils;
import com.grotesque.saa.util.UIUtils;


import static com.grotesque.saa.util.LogUtils.LOGE;
import static com.grotesque.saa.util.LogUtils.makeLogTag;

/**
 * Created by 경환 on 2016-04-10.
 */
public class ZoomableFragment extends Fragment implements View.OnLongClickListener, OnViewChangedNotifier.OnViewChangedListener, HasViews {

    private final String TAG = makeLogTag(ZoomableFragment.class);

    private final OnViewChangedNotifier mOnViewChangedNotifier = new OnViewChangedNotifier();
    private GenericRequestBuilder<Bitmap, Bitmap, Bitmap, Bitmap> glide;
    private ProgressTarget<String, GlideDrawable> glideTarget;
    private int mPosition;
    private Context mContext;
    private ContentItem mContentItem;
    private SimpleDraweeView mImageView;
    private LinearLayout mImageLayout;
    private TextView mProgressTextView;
    private FrameLayout mRootView;
    private View mView;
    private Rect d = new Rect();

    public static ZoomableFragment newInstance(ContentItem contentItem, int position){
        ZoomableFragment fragment = new ZoomableFragment();
        Bundle args = new Bundle();
        args.putParcelable("img", contentItem);
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }

    public ZoomableFragment()   {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.replaceNotifier(mOnViewChangedNotifier);
        OnViewChangedNotifier.registerOnViewChangedListener(this);
        Bundle args = getArguments();
        if(args != null){
            mContentItem = args.getParcelable("img");
            mPosition = args.getInt("position");
        }
        super.onCreate(savedInstanceState);
        OnViewChangedNotifier.replaceNotifier(onViewChangedNotifier);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = super.onCreateView(inflater, container, savedInstanceState);
        if(mView == null)
            mView = inflater.inflate(R.layout.fragment_zoomable_image, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = view.getContext();
        mOnViewChangedNotifier.notifyViewChanged(this);
    }

    public void bindImageUri(ContentItem attachment) {
        String imgUrl = StringUtils.convertImgUrl(attachment.getImg());
        glideTarget.setModel(imgUrl);
        Glide.with(mImageView.getContext())
                .load(imgUrl)
                .override(UIUtils.getScreenSize().x, com.bumptech.glide.request.target.Target.SIZE_ORIGINAL)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.drawable.progress_download_img)
                .into(glideTarget);

    }

    public void setDropShadow(int i, int k, int l, int i1) {
        d.set(i, k, l, i1);
        if(mRootView != null)
            mRootView.setPadding(i, k, l, i1);
    }
/*
    @Override
    public void onSingleTapConfirmed() {
        if (getActivity() instanceof Listener) {
            ((Listener) getActivity()).onSingleTouch();
        }
    }
*/
    @Override
    public boolean onLongClick(View v) {
        if (getActivity() instanceof Listener) {
            ((Listener) getActivity()).onLongClicked();
        }
        return false;
    }

    @Override
    public void onViewChanged(HasViews paramHasViews) {
        mRootView = (FrameLayout) paramHasViews.findViewById(R.id.root_view);
        mImageLayout = (LinearLayout) paramHasViews.findViewById(R.id.ll_image_layout);
        mImageView = (SimpleDraweeView) paramHasViews.findViewById(R.id.zoomable_image_view01);
        mImageView.setOnLongClickListener(this);

        mProgressTextView = (TextView) paramHasViews.findViewById(R.id.text_progress);


        glideTarget = new MyProgressTarget<>(new GlideDrawableImageViewTarget(mImageView){
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                float resized_w;
                float resized_h;
                float ratio;
                int maxTextureSize = UIUtils.getMaxTextureSize();

                resized_w = UIUtils.getScreenSize().x;
                ratio =  resized_w / (float)resource.getIntrinsicWidth();
                resized_h = ratio * resource.getIntrinsicHeight();
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
                        if(i!=0){
                            ImageView iv = new ImageView(mContext);
                            iv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            iv.setScaleType(ImageView.ScaleType.FIT_XY);
                            iv.setAdjustViewBounds(true);
                            mImageLayout.addView(iv);
                            iv.setImageBitmap(images[i]);
                            bindBitmap(images[i], iv);
                        }

                    }
                    resource = new GlideBitmapDrawable(null, images[0]);
                    resized_h = resized_h*ratio;
                }


                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = (int) resized_h;
                layoutParams.width = (int) resized_w;
                view.setLayoutParams(layoutParams);

                super.onResourceReady(resource, animation);
            }
        }, mImageView, mProgressTextView);
        bindImageUri(mContentItem);
        setDropShadow(d.left, d.top, d.right, d.bottom);
    }
    void bindBitmap(Bitmap bitmap, ImageView view) {
        glide = Glide
                .with(view.getContext())
                .using(new PassthroughModelLoader<Bitmap, Bitmap>(), Bitmap.class)
                .from(Bitmap.class)
                .as(Bitmap.class)
                .decoder(new BitmapBitmapResourceDecoder(view.getContext()))
                .cacheDecoder(new FileToStreamDecoder<Bitmap>(new StreamBitmapDecoder(view.getContext())))
                .encoder(new BitmapEncoder());
        glide
                .clone()
                .load(bitmap)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(view);
    }
    @Override
    public View findViewById(int paramInt) {
        if (this.mView == null) {
            return null;
        }
        return this.mView.findViewById(paramInt);
    }


    public interface Listener {
        void onLongClicked();
        void onSingleTouch();
    }

}
