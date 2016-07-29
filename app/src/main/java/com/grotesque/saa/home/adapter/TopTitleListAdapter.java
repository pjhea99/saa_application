package com.grotesque.saa.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.grotesque.saa.R;
import com.grotesque.saa.util.FontManager;
import com.grotesque.saa.util.NavigationUtils;
import com.grotesque.saa.util.StringUtils;

import java.util.ArrayList;

public class TopTitleListAdapter extends RecyclerView.Adapter<TopTitleListAdapter.KeywordTopViewHolder>
{

  private ArrayList<String> arrayList;
  private Context mContext;


  public class KeywordTopViewHolder extends RecyclerView.ViewHolder
  {
    TextView keywordText;
    public KeywordTopViewHolder(TopTitleListAdapter topTitleListAdapter, View paramView)
    {
      super(paramView);

      keywordText = (TextView) paramView.findViewById(R.id.keywordText);
      keywordText.setTypeface(FontManager.getInstance(topTitleListAdapter.mContext).getTypeface());
    }
  }

  public TopTitleListAdapter(Context paramContext)
  {
    this.mContext = paramContext;
  }
  
  public int getItemCount()
  {
    if (this.arrayList != null) {
      return this.arrayList.size();
    }
    return 0;
  }
  
  public void onBindViewHolder(TopTitleListAdapter. KeywordTopViewHolder paramKeywordTopViewHolder, int paramInt)
  {
    final String str1 = this.arrayList.get(paramInt);
    paramKeywordTopViewHolder.keywordText.setText(str1);
    paramKeywordTopViewHolder.keywordText.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        NavigationUtils.goBoardActivity(mContext, StringUtils.getMid(str1));
      }
    });
  }
  
  public TopTitleListAdapter.KeywordTopViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt)
  {
    return new TopTitleListAdapter.KeywordTopViewHolder(this, (ViewGroup)LayoutInflater.from(paramViewGroup.getContext()).inflate(R.layout.item_keyword_top_title, paramViewGroup, false));
  }
  
  public void setArrayList(ArrayList<String> paramArrayList)
  {
    this.arrayList = paramArrayList;
    notifyDataSetChanged();
  }
}
