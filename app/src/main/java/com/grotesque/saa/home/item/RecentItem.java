package com.grotesque.saa.home.item;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.grotesque.saa.R;
import com.grotesque.saa.home.adapter.RecentItemAdapter;
import com.grotesque.saa.home.data.RecentData;
import com.grotesque.saa.util.FontManager;
import com.grotesque.saa.util.NavigationUtils;

import static com.grotesque.saa.util.LogUtils.LOGE;
import static com.grotesque.saa.util.LogUtils.makeLogTag;

public class RecentItem extends AbstractAdapterItem implements RecentData.OnLoadedListener {

    private static final String TAG = makeLogTag(RecentItem.class);
    private RecentItemAdapter recentCalcioItemAdapter;
    private Context mContext;
    private String mid;

    public RecentItem(Context context, String mid)  {
        super();
        mContext = context;
        this.mid = mid;
        recentCalcioItemAdapter = new RecentItemAdapter(mid, mContext);
        onLoadData();
    }

    public View getView(LayoutInflater layoutinflater, View view)
    {
        ViewHolder vh;
        if(view == null){
            view = layoutinflater.inflate(R.layout.listrow_explore_item_keyword_article, null);
            vh = new ViewHolder(mContext, view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder)view.getTag();
        }
        switch (mid){
            case "calcioboard":
                vh.titleView.setText(Html.fromHtml("<font color=#00c3bd>CALCIO</font> <font color=#333333>게시판</font>"));
                break;
            case "freeboard3":
                vh.titleView.setText(Html.fromHtml("<font color=#00c3bd>자유</font> <font color=#333333>게시판</font>"));
                break;
            case "multimedia1":
                vh.titleView.setText(Html.fromHtml("<font color=#00c3bd>미디어</font> <font color=#333333>게시판</font>"));
                break;
            case "qna1":
                vh.titleView.setText(Html.fromHtml("<font color=#00c3bd>질문 </font> <font color=#333333>게시판</font>"));
                break;
            case "rest":
                vh.titleView.setText(Html.fromHtml("<font color=#00c3bd>스포츠 </font> <font color=#333333>게시판</font>"));
                break;
            case "game":
                vh.titleView.setText(Html.fromHtml("<font color=#00c3bd>게임 </font> <font color=#333333>게시판</font>"));
                break;

        }

        vh.titleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationUtils.goBoardActivity(mContext, mid);
            }
        });
        vh.recentArticleList.setAdapter(recentCalcioItemAdapter);
        return view;
    }

    protected void onLoadData() {

        RecentData recentData = new RecentData(mid);
        recentData.setOnLoadedListener(this);
        recentCalcioItemAdapter.setArrayList(recentData.getCalcioList());
        recentData.loadData(mContext);
    }

    @Override
    public void onLoaded() {
        recentCalcioItemAdapter.notifyDataSetChanged();
    }

    class ViewHolder {

        final RecentItem a;
        RecyclerView recentArticleList;
        TextView titleView;

        public ViewHolder(Context context, View view){
            super();
            a = RecentItem.this;
            recentArticleList = (RecyclerView) view.findViewById(R.id.articleList);
            titleView = (TextView) view.findViewById(R.id.titleView);
            FontManager.getInstance(context).setTypeface(titleView);
            recentArticleList.setLayoutManager(new LinearLayoutManager(context, 0, false));
        }
    }

}

