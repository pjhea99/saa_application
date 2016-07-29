package com.grotesque.saa.common.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public abstract class BaseListAdapter<T>
  extends BaseAdapter
{
  protected Context mContext = null;
  protected ArrayList<T> mDataList = null;
  protected LayoutInflater mInflater = null;
  
  public BaseListAdapter(Context paramContext)
  {
    this.mContext = paramContext;
    this.mInflater = LayoutInflater.from(paramContext);
  }
  
  public void addAllDataSetList(ArrayList<T> paramArrayList)
  {
    if (this.mDataList == null) {
      this.mDataList = new ArrayList();
    }
    this.mDataList.addAll(paramArrayList);
  }
  
  public void clearDataSet()
  {
    if (this.mDataList != null) {
      this.mDataList.clear();
    }
  }
  
  public int getCount()
  {
    if (this.mDataList != null) {
      return this.mDataList.size();
    }
    return 0;
  }
  
  public ArrayList<T> getDataSetList()
  {
    return this.mDataList;
  }
  
  public Object getItem(int paramInt)
  {
    if (this.mDataList != null) {
      return this.mDataList.get(paramInt);
    }
    return null;
  }
  
  public long getItemId(int paramInt)
  {
    return paramInt;
  }
  
  public abstract View getItemView(int paramInt, View paramView, ViewGroup paramViewGroup);
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    return getItemView(paramInt, paramView, paramViewGroup);
  }
  
  public void setDataSetList(ArrayList<T> paramArrayList)
  {
    this.mDataList = paramArrayList;
  }
}