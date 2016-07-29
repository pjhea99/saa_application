package com.grotesque.saa.common.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.ImageView;

public class FitWidthImageView extends ImageView
{

  public FitWidthImageView(Context context)
  {
    super(context);
    aspectRatio = -1F;
  }

  public FitWidthImageView(Context context, AttributeSet attributeset)
  {
    super(context, attributeset);
    aspectRatio = -1F;
  }

  public FitWidthImageView(Context context, AttributeSet attributeset, int i)
  {
    super(context, attributeset, i);
    aspectRatio = -1F;
  }

  protected void onMeasure(int i, int j)
  {
    Drawable drawable = getDrawable();
    if(aspectRatio == -1F)
    {
      if(drawable != null)
      {
        float f;
        if(drawable.getIntrinsicWidth() != 0)
          f = (float)drawable.getIntrinsicHeight() / (float)drawable.getIntrinsicWidth();
        else
          f = 0.0F;
        i = android.view.View.MeasureSpec.getSize(i);
        setMeasuredDimension(i, (int)(f * (float)i));
        return;
      } else
      {
        super.onMeasure(i, j);
        return;
      }
    } else
    {
      i = android.view.View.MeasureSpec.getSize(i);
      setMeasuredDimension(i, (int)((float)i * aspectRatio));
      return;
    }
  }

  public void setAspectRatio(float f)
  {
    boolean flag;
    if(aspectRatio != f)
      flag = true;
    else
      flag = false;
    aspectRatio = f;
    if(flag)
      requestLayout();
  }

  public static final float USE_DRAWABLE_ASPECT_RATIO = -1F;
  private float aspectRatio;
}
