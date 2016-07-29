package com.grotesque.saa.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.TextureView;
import java.lang.reflect.Method;


public class StableTextureView extends TextureView {
    private static final boolean IS_PRE_LOLLIPOP = android.os.Build.VERSION.SDK_INT < 21;

    public StableTextureView(Context context)
    {
        super(context);
    }

    public StableTextureView(Context context, AttributeSet attributeset) {
        super(context, attributeset);
    }

    public StableTextureView(Context context, AttributeSet attributeset, int i) {
        super(context, attributeset, i);
    }

    private void preemptivelyDestroySurface() {
        try {
            Method method = getClass().getSuperclass().getDeclaredMethod("destroySurface");
            method.setAccessible(true);
            method.invoke(this, (Object[])null);
        } catch(Exception ignored) {
        }

    }

    protected void onDetachedFromWindow() {
        if (IS_PRE_LOLLIPOP)
            preemptivelyDestroySurface();
        try {
            super.onDetachedFromWindow();
        } catch (Exception ignored) {
        }
    }


}
