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
public class BoardHeaderHolder extends BaseViewHolder<DocumentList> {
    public BoardHeaderHolder(Context context, View itemView) {
        super(context, itemView);
    }

    public static BoardHeaderHolder newInstance(Context context, ViewGroup parent){
        View v = LayoutInflater.from(context).inflate(R.layout.layout_header, parent, false);
        return new BoardHeaderHolder(context, v);
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
