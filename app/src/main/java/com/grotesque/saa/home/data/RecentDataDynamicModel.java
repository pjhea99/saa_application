package com.grotesque.saa.home.data;

import android.content.Context;

public abstract class RecentDataDynamicModel
  implements RecentDataInterface
{
  public abstract int getViewType();
  
  public abstract void loadData(Context paramContext);
}