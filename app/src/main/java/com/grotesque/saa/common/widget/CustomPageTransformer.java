package com.grotesque.saa.common.widget;

import android.support.v4.view.ViewPager;
import android.view.View;

import static com.grotesque.saa.util.LogUtils.makeLogTag;

public class CustomPageTransformer implements ViewPager.PageTransformer{
    private final static String TAG = makeLogTag(CustomPageTransformer.class);
    private ViewPager mViewPager;
    private int mPageOffset = 40;

    public CustomPageTransformer(ViewPager viewPager)
    {
      this.mViewPager = viewPager;
    }

    public int getPageOffset(){
      return this.mPageOffset;
    }

    public void setPageOffset(int paramInt) {
      this.mPageOffset = paramInt;
    }

    public void transformPage(View view, float position)  {

        float i = view.getWidth();
        float j = Math.min(this.mViewPager.getAdapter().getCount(), this.mViewPager.getOffscreenPageLimit());
        float k = this.mPageOffset;

        view.setPivotX(0.0F);
        view.setScaleX(((i - (j - 1) * k)) / i);

        if (position >= 0.0F) {
            view.setTranslationX((this.mPageOffset - i) * position);
        }
    }
}