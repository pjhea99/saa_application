package com.grotesque.saa.post.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.grotesque.saa.R;
import com.grotesque.saa.post.data.YouTubeData;

import java.util.List;

/**
 * Created by 경환 on 2016-04-27.
 */
public class YoutubeAdapter extends RecyclerView.Adapter {
    private List<YouTubeData.Item> arrayList;
    private Context mContext;
    private Listener mListener;

    public YoutubeAdapter(List<YouTubeData.Item> arrayList, Context mContext, Listener mListener) {
        this.arrayList = arrayList;
        this.mContext = mContext;
        this.mListener = mListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow_youtube_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ItemViewHolder ivh = (ItemViewHolder) holder;
        Glide
                .with(ivh.mThumbnailView.getContext())
                .load(arrayList.get(position).getSnippet().getThumbnails().getHigh().getUrl())
                .into(ivh.mThumbnailView);
        ivh.mTitleView.setText(arrayList.get(position).getSnippet().getTitle());
        ivh.mDescriptionView.setText(arrayList.get(position).getSnippet().getPublishedAt());
        ivh.mUploaderView.setText(arrayList.get(position).getSnippet().getChannelTitle());
        ivh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.selectVideo(arrayList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        private ImageView mThumbnailView;
        private TextView mTitleView;
        private TextView mDescriptionView;
        private TextView mUploaderView;
        public ItemViewHolder(View itemView) {
            super(itemView);
            mThumbnailView = (ImageView) itemView.findViewById(R.id.thumbnail_image_view);
            mTitleView = (TextView) itemView.findViewById(R.id.title_text_view);
            mDescriptionView = (TextView) itemView.findViewById(R.id.tv_description);
            mUploaderView = (TextView) itemView.findViewById(R.id.tv_uploader);
        }
    }

    public interface Listener{
        void selectVideo(YouTubeData.Item videoItem);
    }

}
