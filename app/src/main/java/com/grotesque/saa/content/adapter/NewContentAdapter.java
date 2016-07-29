package com.grotesque.saa.content.adapter;

import android.view.ViewGroup;

import com.grotesque.saa.common.adapter.MultiItemAdapter;
import com.grotesque.saa.common.holder.BaseViewHolder;
import com.grotesque.saa.content.holder.CoverHolder;
import com.grotesque.saa.content.holder.DividerHolder;
import com.grotesque.saa.content.holder.FrescoHolder;
import com.grotesque.saa.content.holder.NextHolder;
import com.grotesque.saa.content.holder.PrevHolder;
import com.grotesque.saa.content.holder.TagHolder;
import com.grotesque.saa.content.holder.TextHolder;
import com.grotesque.saa.content.holder.VideoHolder;

/**
 * Created by 경환 on 2016-05-10.
 */
public class NewContentAdapter extends MultiItemAdapter {

    public static final int VIEW_TYPE_COVER = 0;
    public static final int VIEW_TYPE_IMAGE = 1;
    public static final int VIEW_TYPE_TEXT = 2;
    public static final int VIEW_TYPE_VIDEO = 3;
    public static final int VIEW_TYPE_NEXT = 4;
    public static final int VIEW_TYPE_PREV = 5;
    public static final int VIEW_TYPE_TAGS = 6;
    public static final int VIEW_TYPE_DIVIDER = 7;

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_TEXT){
            return TextHolder.newInstance(parent.getContext(), parent);
        }else if(viewType == VIEW_TYPE_IMAGE) {
            return FrescoHolder.newInstance(parent.getContext(), parent);
        }else if(viewType == VIEW_TYPE_VIDEO) {
            return VideoHolder.newInstance(parent.getContext(), parent);
        }else if(viewType == VIEW_TYPE_PREV) {
            return PrevHolder.newInstance(parent.getContext(), parent);
        }else if(viewType == VIEW_TYPE_NEXT) {
            return NextHolder.newInstance(parent.getContext(), parent);
        }else if(viewType == VIEW_TYPE_COVER) {
            return CoverHolder.newInstance(parent.getContext(), parent);
        }else if(viewType == VIEW_TYPE_TAGS) {
            return TagHolder.newInstance(parent.getContext(), parent);
        }
        return DividerHolder.newInstance(parent.getContext(), parent);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBindView(getAllItem(), position);
    }
}
