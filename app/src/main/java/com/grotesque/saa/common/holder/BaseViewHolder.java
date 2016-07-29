package com.grotesque.saa.common.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

public abstract class BaseViewHolder<ITEM> extends RecyclerView.ViewHolder {

    private Context mContext;
    public BaseViewHolder(Context context, View itemView) {
        super(itemView);
        this.mContext = context;
    }
    protected Context getContext() {
        return mContext;
    }

    public abstract void onBindView(ITEM item);
    public abstract void onBindView(List<ITEM> item, int position);
    public abstract void onBindView(List<ITEM> item, int position, String mid);
}