package com.grotesque.saa.common;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.grotesque.saa.R;

public class Drawables {
  public static void init(final Resources resources) {
    if (sPlaceholderDrawable == null) {
      sPlaceholderDrawable = resources.getDrawable(R.drawable.ic_placeholder);
    }
    if (sErrorDrawable == null) {
      sErrorDrawable = resources.getDrawable(R.drawable.img_loading_error);
    }
  }

  public static Drawable sPlaceholderDrawable;
  public static Drawable sErrorDrawable;

  private Drawables() {
  }
}