package com.grotesque.saa.content.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grotesque.saa.R;
import com.grotesque.saa.common.holder.BaseViewHolder;
import com.grotesque.saa.content.data.ContentItem;

import java.util.List;

/**
 * Created by 경환 on 2016-05-10.
 */
public class DividerHolder extends BaseViewHolder<ContentItem> {

    public static DividerHolder newInstance(Context context, ViewGroup parent){
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_postview_divider, parent, false);
        return new DividerHolder(parent.getContext(), v);
    }

    public DividerHolder(Context context, View itemView) {
        super(context, itemView);
    }

    @Override
    public void onBindView(ContentItem contentItem) {

    }

    @Override
    public void onBindView(List<ContentItem> item, int position) {

    }

    @Override
    public void onBindView(List<ContentItem> item, int position, String mid) {

    }
}
