package com.grotesque.saa.content.holder;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.common.util.UriUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.grotesque.saa.R;
import com.grotesque.saa.common.Drawables;
import com.grotesque.saa.common.holder.BaseViewHolder;
import com.grotesque.saa.common.widget.CustomAlertDialog;
import com.grotesque.saa.common.widget.CustomProgressbarDrawable;
import com.grotesque.saa.content.data.ContentItem;
import com.grotesque.saa.util.NavigationUtils;
import com.grotesque.saa.util.StringUtils;
import com.grotesque.saa.util.UIUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 경환 on 2016-05-10.
 */
public class FrescoHolder extends BaseViewHolder<ContentItem> {
    private static final String TAG = FrescoHolder.class.getSimpleName();
    private SimpleDraweeView mImageView;
    private String mUri;
    private int mImageType;

    public static FrescoHolder newInstance(Context context, ViewGroup parent){
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_postview_fresco, parent, false);
        return new FrescoHolder(parent.getContext(), v);
    }

    public FrescoHolder(Context context, View itemView) {
        super(context, itemView);

        mImageView = (SimpleDraweeView) itemView.findViewById(R.id.imageView);


        GenericDraweeHierarchy gdh = new GenericDraweeHierarchyBuilder(getContext().getResources())
                .setFailureImage(Drawables.sErrorDrawable, ScalingUtils.ScaleType.CENTER_CROP)
                .setProgressBarImage(new CustomProgressbarDrawable())
                .setPlaceholderImage(Drawables.sPlaceholderDrawable, ScalingUtils.ScaleType.CENTER_CROP)
                .setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER)
                .build();
        mImageView.setHierarchy(gdh);
    }

    @Override
    public void onBindView(ContentItem contentItem) {

    }

    @Override
    public void onBindView(final List<ContentItem> item, final int position) {
        mUri = StringUtils.convertImgUrl(item.get(position).getImg());
        Uri uri = Uri.parse(mUri);

        ImageRequestBuilder imageRequestBuilder =
                ImageRequestBuilder.newBuilderWithSource(uri);
        if (UriUtil.isNetworkUri(uri)) {
            imageRequestBuilder.setProgressiveRenderingEnabled(true);
        }


        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                .setImageRequest(imageRequestBuilder.build())
                .setOldController(mImageView.getController())
                .setControllerListener(mControllerListener)
                .setAutoPlayAnimations(true)
                .setTapToRetryEnabled(true)
                .build();
        mImageView.setController(draweeController);
        mImageView.setOnClickListener(mImageClickListener);
        mImageView.setOnLongClickListener(mImageLongClickListener);
    }

    @Override
    public void onBindView(List<ContentItem> item, int position, String mid) {

    }

    private ControllerListener mControllerListener = new ControllerListener<ImageInfo>() {
        @Override
        public void onSubmit(String id, Object callerContext) {

        }

        @Override
        public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable animatable) {

            if (imageInfo != null) {
                int maxTextureSize = UIUtils.getMaxTextureSize();
                int screenWidth = UIUtils.getScreenWidth(getContext());
                /*
                LOGE(TAG, "image.height : " + imageInfo.getHeight());
                LOGE(TAG, "image.width : " + imageInfo.getWidth());
                LOGE(TAG, "maxTextureSize : " + maxTextureSize);
                LOGE(TAG, "screenWidth : " + screenWidth);
                */
                if(imageInfo.getHeight() >= maxTextureSize || imageInfo.getWidth() >= maxTextureSize){
                    mImageType = 0;
                }else{
                    mImageView.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
                    mImageView.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    mImageView.setAspectRatio((float) imageInfo.getWidth() / imageInfo.getHeight());
                    float ratio = (float) imageInfo.getHeight() / (float) imageInfo.getWidth();
                    //LOGE(TAG, "imageView height : " + ratio * screenWidth);
                    mImageType = ratio * screenWidth >= maxTextureSize ? 0 : animatable != null ? 2 : 1;
                }
            }

        }

        @Override
        public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {
            if (imageInfo != null) {
                mImageView.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
                mImageView.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
                mImageView.setAspectRatio((float) imageInfo.getWidth() / imageInfo.getHeight());
            }
        }

        @Override
        public void onIntermediateImageFailed(String id, Throwable throwable) {

        }

        @Override
        public void onFailure(String id, Throwable throwable) {
        }

        @Override
        public void onRelease(String id) {

        }
    };
    private View.OnClickListener mImageClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) { NavigationUtils.goImageViewActivity(getContext(), mUri, mImageType); }
    };
    private View.OnLongClickListener mImageLongClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            ArrayList arraylist = new ArrayList();
            arraylist.add("인터넷으로 열기");
            arraylist.add("이미지 저장");

            CharSequence acharsequence[] = (CharSequence[])arraylist.toArray(new CharSequence[arraylist.size()]);
            (new CustomAlertDialog(getContext())).setItems(acharsequence, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case 0:
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mUri));
                            getContext().startActivity(intent);
                            break;
                        case 1:
                            break;
                    }
                }
            }).show();


            return false;
        }
    };
}
