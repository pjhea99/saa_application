package com.grotesque.saa.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLES20;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import static com.grotesque.saa.util.LogUtils.LOGE;
import static com.grotesque.saa.util.LogUtils.makeLogTag;

public class MyTransformation extends BitmapTransformation {
    private static final String TAG = makeLogTag(MyTransformation.class);
    public MyTransformation(Context context) {
       super(context);
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform,
                               int outWidth, int outHeight) {
        int[] maxTextureSize = new int[1];
        GLES20.glGetIntegerv(GLES20.GL_MAX_TEXTURE_SIZE, maxTextureSize, 0);

        int destWidth = 0;
        int destHeight = 0;
        float ratio = maxTextureSize[0] / outHeight;

        if(outHeight > maxTextureSize[0]){
            destHeight = maxTextureSize[0];
            destWidth = (int) (outWidth * ratio);
        }
        LOGE(TAG, "maxTextureSize : " + maxTextureSize[0]);
        LOGE(TAG, "outHeight : " + outHeight);
        LOGE(TAG, "destHeight : " + destHeight);
        Bitmap myTransformedBitmap = toTransform.createScaledBitmap(toTransform, destWidth, destHeight, false);
        return toTransform;
    }

    @Override
    public String getId() {
        // Return some id that uniquely identifies your transformation.
        return "com.example.myapp.MyTransformation";
    }
}