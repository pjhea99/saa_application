package com.grotesque.saa.common.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

class SlowScroller extends Scroller {
    private double a = 1.0D;

    public SlowScroller(Context paramContext)
    {
      super(paramContext);
    }

    public SlowScroller(Context paramContext, Interpolator paramInterpolator) {
        super(paramContext, paramInterpolator);
    }

    @SuppressLint({"NewApi"})
    public SlowScroller(Context paramContext, Interpolator paramInterpolator, boolean paramBoolean) {
        super(paramContext, paramInterpolator, paramBoolean);
    }

    public void setScrollDurationFactor(double paramDouble)
    {
      this.a = paramDouble;
    }

    public void startScroll(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
        super.startScroll(paramInt1, paramInt2, paramInt3, paramInt4, (int)(paramInt5 * this.a));
    }
}
