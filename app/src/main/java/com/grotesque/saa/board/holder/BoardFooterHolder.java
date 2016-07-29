package com.grotesque.saa.board.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grotesque.saa.R;
import com.grotesque.saa.common.holder.BaseViewHolder;
import com.grotesque.saa.home.data.DocumentList;

import java.util.List;

/**
 * Created by KH on 2016-07-26.
 */
public class BoardFooterHolder extends BaseViewHolder<DocumentList> {
    public BoardFooterHolder(Context context, View itemView) {
        super(context, itemView);
    }
    public static BoardFooterHolder newInstance(Context context, ViewGroup parent){
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_footer_loading, parent, false);
        return new BoardFooterHolder(context, v);
    }
    @Override
    public void onBindView(DocumentList documentList) {

    }

    @Override
    public void onBindView(List<DocumentList> item, int position) {

    }

    @Override
    public void onBindView(List<DocumentList> item, int position, String mid) {

    }
}
