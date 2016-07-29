package com.grotesque.saa.content.holder;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.grotesque.saa.R;
import com.grotesque.saa.common.holder.BaseViewHolder;
import com.grotesque.saa.content.data.ContentItem;

import java.util.List;

/**
 * Created by 경환 on 2016-05-10.
 */
public class CoverHolder extends BaseViewHolder<ContentItem> {

    private TextView mTitleView;
    public static CoverHolder newInstance(Context context, ViewGroup parent){
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_postview_content_info_trans, parent, false);
        return new CoverHolder(parent.getContext(), v);
    }

    public CoverHolder(Context context, View itemView) {
        super(context, itemView);
        mTitleView = (TextView) itemView.findViewById(R.id.txt_coverview_title);
        mTitleView.setTextColor(Color.parseColor("#00000000"));
    }

    @Override
    public void onBindView(ContentItem contentItem) {

    }

    @Override
    public void onBindView(List<ContentItem> item, int position) {
        mTitleView.setText(Html.fromHtml(item.get(position).getText()));
    }

    @Override
    public void onBindView(List<ContentItem> item, int position, String mid) {

    }
}
