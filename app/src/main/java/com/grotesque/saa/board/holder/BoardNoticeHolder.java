package com.grotesque.saa.board.holder;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.grotesque.saa.R;
import com.grotesque.saa.common.holder.BaseViewHolder;
import com.grotesque.saa.home.data.DocumentList;
import com.grotesque.saa.util.FontManager;
import com.grotesque.saa.util.NavigationUtils;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by KH on 2016-07-27.
 */
public class BoardNoticeHolder extends BaseViewHolder<DocumentList>  {

    private TextView mTitleView;

    public BoardNoticeHolder(Context context, View itemView) {
        super(context, itemView);

        mTitleView = (TextView) itemView.findViewById(R.id.titleView);
        mTitleView.setTypeface(FontManager.getInstance(context).getTypeface());

    }

    public static BoardNoticeHolder newInstance(Context context, ViewGroup parent) {
        return new BoardNoticeHolder(context, LayoutInflater.from(context).inflate(R.layout.listrow_board_notice, parent, false));
    }

    @Override
    public void onBindView(DocumentList documentList) {

    }

    @Override
    public void onBindView(List<DocumentList> item, int position) {

    }

    @Override
    public void onBindView(final List<DocumentList> item, final int position, final String mid) {

        String title = item.get(position).getTitle();
        mTitleView.setText(Html.fromHtml(title));
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationUtils.goContentActivity(getContext(), mid, (ArrayList<DocumentList>) item, position);
            }
        });
    }
}
