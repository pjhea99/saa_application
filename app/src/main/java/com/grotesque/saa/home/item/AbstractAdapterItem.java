package com.grotesque.saa.home.item;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.grotesque.saa.home.data.RecentDataDynamicModel;
import com.grotesque.saa.home.data.RecentDataInterface;

public abstract class AbstractAdapterItem<T extends RecentDataInterface> {

  private T mType;

  
  public AbstractAdapterItem(){
  }
  
  private void getType(T paramT)
  {
    this.mType = paramT;
  }
  
  public T getExploreDataModel()
  {
    return this.mType;
  }
  
  public abstract View getView(LayoutInflater paramLayoutInflater, View paramView);
  
  public int getViewType()
  {
    if (this.mType != null) {
      return this.mType.getViewType();
    }
    return -1;
  }

  protected abstract void onLoadData();


}
