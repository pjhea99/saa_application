package com.grotesque.saa.rank.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.TextView;

import com.grotesque.saa.R;
import com.grotesque.saa.util.FontManager;
import com.grotesque.saa.rank.data.LeagueTable;

import java.util.ArrayList;

public class RankLandAdapter extends RecyclerView.Adapter<RankLandAdapter.RankLandViewHolder> implements AbsListView.RecyclerListener{
    Context context;
    int itemlayout;
    int lastPosition = -1;
    ArrayList<LeagueTable> arrayItems;

    public RankLandAdapter(Context context, int resource, ArrayList<LeagueTable> objects) {
        this.context = context;
        this.itemlayout = resource;
        this.arrayItems = objects;
    }

    public class RankLandViewHolder extends RecyclerView.ViewHolder{
        TextView rankTextView;
        TextView teamTextView;
        TextView matchTextView;
        TextView winTextView;
        TextView drawTextView;
        TextView loseTextView;
        TextView scoreTextView;
        TextView nscoreTextView;
        TextView pmTextView;
        TextView pointsTextView;

        public RankLandViewHolder(View itemView) {
            super(itemView);

            rankTextView = (TextView) itemView.findViewById(R.id.rank);
            rankTextView.setTypeface(FontManager.getInstance(context).getTypeface());
            teamTextView = (TextView) itemView.findViewById(R.id.name);
            teamTextView.setTypeface(FontManager.getInstance(context).getTypeface());
            matchTextView = (TextView) itemView.findViewById(R.id.played);
            matchTextView.setTypeface(FontManager.getInstance(context).getTypeface());
            winTextView = (TextView) itemView.findViewById(R.id.won);
            winTextView.setTypeface(FontManager.getInstance(context).getTypeface());
            drawTextView = (TextView) itemView.findViewById(R.id.drawn);
            drawTextView.setTypeface(FontManager.getInstance(context).getTypeface());
            loseTextView = (TextView) itemView.findViewById(R.id.lost);
            loseTextView.setTypeface(FontManager.getInstance(context).getTypeface());
            scoreTextView = (TextView) itemView.findViewById(R.id.for_);
            scoreTextView.setTypeface(FontManager.getInstance(context).getTypeface());
            nscoreTextView = (TextView) itemView.findViewById(R.id.against);
            nscoreTextView.setTypeface(FontManager.getInstance(context).getTypeface());
            pmTextView = (TextView) itemView.findViewById(R.id.goal_diff);
            pmTextView.setTypeface(FontManager.getInstance(context).getTypeface());
            pointsTextView = (TextView) itemView.findViewById(R.id.points);
            pointsTextView.setTypeface(FontManager.getInstance(context).getTypeface());
        }
    }

    @Override
    public RankLandViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.listrow_rank_land, viewGroup, false);
        return new RankLandViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RankLandViewHolder holder, int position) {

        if (arrayItems.get(position) != null) {
            holder.rankTextView.setText(arrayItems.get(position).getRank());
            holder.teamTextView.setText(arrayItems.get(position).getTeam());
            holder.matchTextView.setText(arrayItems.get(position).getMatch());
            holder.winTextView.setText(arrayItems.get(position).getWin());
            holder.drawTextView.setText(arrayItems.get(position).getDraw());
            holder.loseTextView.setText(arrayItems.get(position).getLose());
            holder.scoreTextView.setText(arrayItems.get(position).getScore());
            holder.nscoreTextView.setText(arrayItems.get(position).getNscore());
            holder.pmTextView.setText(arrayItems.get(position).getPm());
            holder.pointsTextView.setText(arrayItems.get(position).getPoints());
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
}
