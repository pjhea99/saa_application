package com.grotesque.saa.content.holder;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.grotesque.saa.R;
import com.grotesque.saa.common.holder.BaseViewHolder;
import com.grotesque.saa.content.data.ContentItem;

import java.util.List;

/**
 * Created by 경환 on 2016-05-10.
 */
public class TagHolder extends BaseViewHolder<ContentItem> {
    private TextView mTagView;
    private LinearLayout mWrapperView;
    public static TagHolder newInstance(Context context, ViewGroup parent){
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_postview_tags, parent, false);
        return new TagHolder(parent.getContext(), v);
    }

    public TagHolder(Context context, View itemView) {
        super(context, itemView);
        mTagView = (TextView) itemView.findViewById(R.id.txt_postview_tags);
        mWrapperView = (LinearLayout) itemView.findViewById(R.id.ll_postview_tags);
    }

    @Override
    public void onBindView(ContentItem contentItem) {

    }

    @Override
    public void onBindView(List<ContentItem> item, int position) {
        if(item.get(position).getText().equals(""))
            mWrapperView.setVisibility(View.GONE);
        else
            mTagView.setText(Html.fromHtml(item.get(position).getText()));
    }

    @Override
    public void onBindView(List<ContentItem> item, int position, String mid) {

    }
}
