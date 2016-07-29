package com.grotesque.saa.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.grotesque.saa.home.item.AbstractAdapterItem;
import com.grotesque.saa.common.widget.BaseListAdapter;

import static com.grotesque.saa.util.LogUtils.LOGE;
import static com.grotesque.saa.util.LogUtils.makeLogTag;

public class ExploreAdapter extends BaseListAdapter
{
  private static final String TAG = makeLogTag(ExploreAdapter.class);
  public ExploreAdapter(Context paramContext)
  {
    super(paramContext);
  }
  
  public View getItemView(int i, View paramView, ViewGroup paramViewGroup) {
    AbstractAdapterItem abstractAdapterItem = (AbstractAdapterItem)getItem(i);
    if (abstractAdapterItem != null) {
      return abstractAdapterItem.getView(this.mInflater, null);
    }
    return null;
  }
  
  public int getViewTypeCount()
  {
    return 3;
  }
}