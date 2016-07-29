package com.grotesque.saa.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.grotesque.saa.R;

public class ListFooterLayout
  extends LinearLayout
{
  private RelativeLayout a;
  
  public ListFooterLayout(Context paramContext)
  {
    super(paramContext);
    a(paramContext);
  }
  
  public ListFooterLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    a(paramContext);
  }
  
  public ListFooterLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    a(paramContext);
  }
  
  private void a(Context paramContext)
  {
    LayoutInflater.from(paramContext).inflate(R.layout.layout_footer_loading, this, true);
    this.a = ((RelativeLayout)findViewById(R.id.rl_footer));
    ((ImageView)findViewById(R.id.image_loading)).startAnimation(AnimationUtils.loadAnimation(paramContext, R.anim.rotate_loading));
  }
  
  public void setVisibleFooterLoading(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.a.setVisibility(VISIBLE);
      return;
    }
    this.a.setVisibility(INVISIBLE);
  }
}