package com.grotesque.saa.common.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;

import java.lang.reflect.Field;



public class SlowViewPager extends ViewPager {
    private SlowScroller mSlowScroller = null;
    private int b = 0;

    public SlowViewPager(Context paramContext) {
        super(paramContext);
        a();
    }

    public SlowViewPager(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        a();
    }

    private void a()  {
        try {
          Field localField1 = ViewPager.class.getDeclaredField("mScroller");
          localField1.setAccessible(true);
          Field localField2 = ViewPager.class.getDeclaredField("sInterpolator");
          localField2.setAccessible(true);
          this.mSlowScroller = new SlowScroller(getContext(), (Interpolator)localField2.get(null));
          localField1.set(this, this.mSlowScroller);
          return;
        }
        catch (Exception localException) {}
    }

    protected boolean canScroll(View view, boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3) {
        /*
        if ((view instanceof ImageViewTouch)){
            ImageViewTouch imageViewTouch = (ImageViewTouch)view;
            if (imageViewTouch.getScale() > 1.0F) {
                RectF localRectF;
                Rect localRect;

                localRectF = imageViewTouch.getBitmapRect();
                localRect = new Rect();
                imageViewTouch.getGlobalVisibleRect(localRect);
                paramInt1 = Math.min(getAdapter().getCount(), getOffscreenPageLimit());
                paramInt2 = this.b;
                return !((localRectF.left >= localRect.left) || (localRectF.right <= localRect.right + paramInt1 * paramInt2));
            }

            return false;
      }
      */
      return super.canScroll(view, paramBoolean, paramInt1, paramInt2, paramInt3);
    }

    public void setPageTransformer(boolean paramBoolean, PageTransformer pageTransformer)  {
        this.b = ((CustomPageTransformer)pageTransformer).getPageOffset();
        super.setPageTransformer(paramBoolean, pageTransformer);
    }

    public void setScrollDurationFactor(double paramDouble) {
        this.mSlowScroller.setScrollDurationFactor(paramDouble);
    }
}