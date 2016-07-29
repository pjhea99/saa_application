package com.grotesque.saa.board.adapter;

import android.view.ViewGroup;

import com.grotesque.saa.board.holder.BoardBasicHolder;
import com.grotesque.saa.board.holder.BoardCardHolder;
import com.grotesque.saa.board.holder.BoardFooterHolder;
import com.grotesque.saa.board.holder.BoardHeaderHolder;
import com.grotesque.saa.board.holder.BoardNoticeHolder;
import com.grotesque.saa.common.adapter.MultiItemAdapter;
import com.grotesque.saa.common.holder.BaseViewHolder;

/**
 * Created by 경환 on 2016-05-12.
 */
public class NewBoardAdapter extends MultiItemAdapter {

    public static final int HEADER_HOLDER = 0;
    public static final int NOTICE_HOLDER = 1;
    public static final int BASIC_HOLDER = 2;
    public static final int CARD_HOLDER = 3;
    public static final int FOOTER_HOLDER = 4;

    private String mMid;
    public NewBoardAdapter(String mid) {
        this.mMid = mid;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                return BoardHeaderHolder.newInstance(parent.getContext(), parent);
            case 1:
                return BoardNoticeHolder.newInstance(parent.getContext(), parent);
            case 2:
                return BoardBasicHolder.newInstance(parent.getContext(), parent);
            case 3:
                return BoardCardHolder.newInstance(parent.getContext(), parent);
            case 4:
                return BoardFooterHolder.newInstance(parent.getContext(), parent);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBindView(getAllItem(), position, mMid);
    }
}
