package com.grotesque.saa.rank.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.grotesque.saa.R;
import com.grotesque.saa.rank.data.LeagueTable;

import java.util.ArrayList;

/**
 * Created by 0614_000 on 2015-05-29.
 */


public class RankPortAdapter extends RecyclerView.Adapter<RankPortAdapter.RankPortViewHolder> implements AbsListView.RecyclerListener {
    Context context;
    int itemlayout;
    int lastPosition = -1;
    ArrayList<LeagueTable> arrayItems;
    Callback callback;
    public RankPortAdapter(Context context, int resource, ArrayList<LeagueTable> objects, Callback callback) {
        this.context = context;
        this.itemlayout = resource;
        this.arrayItems = objects;
        this.callback = callback;
    }
    public class RankPortViewHolder extends RecyclerView.ViewHolder{
        TextView rankTextView;
        TextView teamTextView;
        TextView playedTextView;
        TextView pmTextView;
        TextView pointsTextView;
        RelativeLayout wrapperView;
        public RankPortViewHolder(View itemView) {
            super(itemView);
            wrapperView = (RelativeLayout) itemView.findViewById(R.id.rl_wrapper_rank);
            rankTextView = (TextView) itemView.findViewById(R.id.rank);
            teamTextView = (TextView) itemView.findViewById(R.id.name);
            playedTextView = (TextView) itemView.findViewById(R.id.played);
            pmTextView = (TextView) itemView.findViewById(R.id.goal_diff);
            pointsTextView = (TextView) itemView.findViewById(R.id.points);
        }
    }

    @Override
    public RankPortViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.listrow_rank, viewGroup, false);
        return new RankPortViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RankPortViewHolder holder, int position) {
        if (arrayItems.get(position) != null) {
            holder.rankTextView.setText(arrayItems.get(position).getRank());
            holder.teamTextView.setText(arrayItems.get(position).getTeam());
            holder.playedTextView.setText(arrayItems.get(position).getMatch());
            holder.pmTextView.setText(arrayItems.get(position).getPm());
            holder.pointsTextView.setText(arrayItems.get(position).getPoints());
        }

        if(position < 2){
            holder.wrapperView.setBackgroundColor(Color.parseColor("#4c0000FF"));
        }else if(position == 2){
            holder.wrapperView.setBackgroundColor(Color.parseColor("#4c00a4ff"));
        }else if(2 < position && position < 5){
            holder.wrapperView.setBackgroundColor(Color.parseColor("#4cFF0000"));
        }else if(position > 16){
            holder.wrapperView.setBackgroundColor(Color.parseColor("#4c000000"));
        }else{
            holder.wrapperView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }

        if(arrayItems.get(position).isSelected())
            holder.itemView.setSelected(true);
        else
            holder.itemView.setSelected(false);
        if(position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.image_fade_in);
            holder.itemView.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return arrayItems.size();
    }

    @Override
    public void onMovedToScrapHeap(View view) {

    }
    public interface Callback{
        void clicked(String teamId);
    }

}
