package com.grotesque.saa.board.adapter;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.grotesque.saa.R;
import com.grotesque.saa.common.Drawables;
import com.grotesque.saa.home.data.DocumentList;
import com.grotesque.saa.util.FontManager;
import com.grotesque.saa.util.NavigationUtils;
import com.grotesque.saa.util.ParseUtils;
import com.grotesque.saa.util.StringUtils;
import com.grotesque.saa.util.UIUtils;

import java.util.ArrayList;
import java.util.Locale;

import static com.grotesque.saa.util.LogUtils.makeLogTag;


/**
 * Provide views to RecyclerView with data from arrayItems.
 */
public class LinearBoardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements AbsListView.RecyclerListener{
    private static final String TAG = makeLogTag(LinearBoardAdapter.class);

    private static final int FOOTER_VIEWHOLDER = 0;
    private static final int ITEM_VIEWHOLDER = 1;
    private static final int HEADER_VIEWHOLDER = 2;

    private Context context;
    private ArrayList<DocumentList> arrayItems = new ArrayList<>();
    private String mid;

    public LinearBoardAdapter(String mid, ArrayList<DocumentList> arrayItems, Context context) {
        this.mid = mid;
        this.arrayItems = arrayItems;
        this.context = context;
    }

    @Override
    public void onMovedToScrapHeap(View view) {

    }
    public class ViewHeaderHolder extends RecyclerView.ViewHolder{

        public ViewHeaderHolder(View itemView) {
            super(itemView);
        }
    }
    public class ViewFooterHolder extends RecyclerView.ViewHolder{
        RelativeLayout relativeLayoutFooter;
        ImageView imageLoading;
        public ViewFooterHolder(View itemView) {
            super(itemView);
            relativeLayoutFooter = (RelativeLayout) itemView.findViewById(R.id.rl_footer);
            imageLoading = (ImageView) itemView.findViewById(R.id.image_loading);
            imageLoading.startAnimation(AnimationUtils.loadAnimation(context, R.anim.rotate_loading));
        }
    }

    public class ViewItemHolder extends RecyclerView.ViewHolder {
        TextView mByView;
        TextView mCountView;
        TextView mTimeView;
        TextView mTitleView;
        TextView mSubTitleView;
        TextView mUserView;
        TextView mCommentCountView;
        SimpleDraweeView mImageView;

        public ViewItemHolder(View v) {
            super(v);

            mImageView = (SimpleDraweeView) v.findViewById(R.id.imageView);
            mByView = (TextView) v.findViewById(R.id.byView);
            mByView.setTypeface(FontManager.getInstance(context).getTypefaceItalic());
            mTitleView = (TextView) v.findViewById(R.id.titleView);
            mTitleView.setTypeface(FontManager.getInstance(context).getTypeface());
            mSubTitleView = (TextView) v.findViewById(R.id.subtitleView);
            mSubTitleView.setTypeface(FontManager.getInstance(context).getTypeface());
            mUserView = (TextView) v.findViewById(R.id.userView);
            mUserView.setTypeface(FontManager.getInstance(context).getTypeface());
            mTimeView = (TextView) v.findViewById(R.id.timeView);
            mTimeView.setTypeface(FontManager.getInstance(context).getTypeface());
            mCountView = (TextView)v.findViewById(R.id.countView);
            mCountView.setTypeface(FontManager.getInstance(context).getTypefaceItalic());
            mCommentCountView = (TextView) v.findViewById(R.id.commentCountView);
            mCommentCountView.setTypeface(FontManager.getInstance(context).getTypeface());

            GenericDraweeHierarchy gdh = new GenericDraweeHierarchyBuilder(context.getResources())
                    .setFailureImage(Drawables.sErrorDrawable)
                    .setPlaceholderImage(Drawables.sPlaceholderDrawable, ScalingUtils.ScaleType.CENTER_CROP)
                    .build();
            mImageView.setHierarchy(gdh);
        }
    }

    public class ViewDetailItemHolder extends RecyclerView.ViewHolder{
        TextView mByView;
        TextView mCountView;
        TextView mTimeView;
        TextView mTitleView;
        TextView mSubTitleView;
        TextView mUserView;
        TextView mCommentCountView;
        TextView mVoteCountView;

        public ViewDetailItemHolder(View v) {
            super(v);

            mByView = (TextView) v.findViewById(R.id.byView);
            mByView.setTypeface(FontManager.getInstance(context).getTypefaceItalic());
            mTitleView = (TextView) v.findViewById(R.id.titleView);
            mTitleView.setTypeface(FontManager.getInstance(context).getTypeface());
            mSubTitleView = (TextView) v.findViewById(R.id.subtitleView);
            mSubTitleView.setTypeface(FontManager.getInstance(context).getTypeface());
            mUserView = (TextView) v.findViewById(R.id.userView);
            mUserView.setTypeface(FontManager.getInstance(context).getTypeface());
            mTimeView = (TextView) v.findViewById(R.id.timeView);
            mTimeView.setTypeface(FontManager.getInstance(context).getTypeface());
            mCountView = (TextView)v.findViewById(R.id.countView);
            mCountView.setTypeface(FontManager.getInstance(context).getTypefaceItalic());
            mCommentCountView = (TextView) v.findViewById(R.id.commentCountView);
            mCommentCountView.setTypeface(FontManager.getInstance(context).getTypeface());
            mVoteCountView = (TextView) v.findViewById(R.id.voteCountView);
            mVoteCountView.setTypeface(FontManager.getInstance(context).getTypeface());
        }
    }

    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if(viewType == FOOTER_VIEWHOLDER){
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.layout_footer_loading, viewGroup, false);
            return new ViewFooterHolder(v);
        }else if(viewType == HEADER_VIEWHOLDER){
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.layout_header, viewGroup, false);
            return new ViewFooterHolder(v);
        }
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.listrow_board_card, viewGroup, false);
        return new ViewDetailItemHolder(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof ViewFooterHolder){
            ViewFooterHolder vh = (ViewFooterHolder) holder;

        }else if(holder instanceof ViewDetailItemHolder){
            final ViewDetailItemHolder vh = (ViewDetailItemHolder) holder;
            String title = arrayItems.get(position).getTitle();
            vh.mTitleView.setText(Html.fromHtml(title));
            vh.mSubTitleView.setText(title.contains("스포") ? "" : Html.fromHtml(arrayItems.get(position).getContent().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "")));
            vh.mTimeView.setText(StringUtils.convertDate(arrayItems.get(position).getRegdate()));
            vh.mCountView.setText(String.format(Locale.US, "조회 %s", arrayItems.get(position).getReadedCount()));
            vh.mUserView.setText(arrayItems.get(position).getNickName());
            vh.mCommentCountView.setText(String.format(Locale.US, "%s", arrayItems.get(position).getCommentCount()));
            vh.mVoteCountView.setText(arrayItems.get(position).getVotedCount());
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavigationUtils.goContentActivity(context, mid, arrayItems, position);
                }
            });

            vh.mCommentCountView.setTextColor(UIUtils.getCategoryColor(arrayItems.get(position).getCategorySrl()));
            vh.mVoteCountView.setTextColor(UIUtils.getCategoryColor(arrayItems.get(position).getCategorySrl()));
        }else{
            final ViewItemHolder vh = (ViewItemHolder) holder;
            String title = arrayItems.get(position).getTitle();
            vh.mTitleView.setText(Html.fromHtml(title));
            vh.mSubTitleView.setText(title.contains("스포") ? "" : Html.fromHtml(arrayItems.get(position).getContent().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "")));
            vh.mTimeView.setText(StringUtils.convertDate(arrayItems.get(position).getRegdate()));
            vh.mCountView.setText(String.format(Locale.US, "%s hit", arrayItems.get(position).getReadedCount()));
            vh.mUserView.setText(arrayItems.get(position).getNickName());
            vh.mCommentCountView.setText(String.format(Locale.US, "+ %s", arrayItems.get(position).getCommentCount()));
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavigationUtils.goContentActivity(context, mid, arrayItems, position);
                }
            });

            vh.mCountView.setTextColor(UIUtils.getCategoryColor(arrayItems.get(position).getCategorySrl()));
            vh.mCommentCountView.setTextColor(UIUtils.getCategoryColor(arrayItems.get(position).getCategorySrl()));


            if(arrayItems.get(position).hasImg()){
                vh.mImageView.setVisibility(View.VISIBLE);
                ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(ParseUtils.parseImgUrl(arrayItems.get(position).getContent())))
                        .setResizeOptions(new ResizeOptions(75, 75))
                        .build();
                DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                        .setImageRequest(request)
                        .setOldController(vh.mImageView.getController())
                        .build();
                vh.mImageView.setController(draweeController);
                vh.mCommentCountView.setTextColor(Color.parseColor("#FFFFFF"));
            }else{
                vh.mImageView.setVisibility(View.GONE);
            }

        }
    }
    public DocumentList getItem(int position){
        return arrayItems.get(position+1);
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? HEADER_VIEWHOLDER : arrayItems.get(position) == null ? FOOTER_VIEWHOLDER : ITEM_VIEWHOLDER;
    }

    @Override
    public int getItemCount() {
        return arrayItems.size()+1;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(hasStableIds);
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        if(holder instanceof ViewItemHolder){
            final ViewItemHolder vh = (ViewItemHolder) holder;
            if (vh.mImageView.getController() != null) {
                vh.mImageView.getController().onDetach();
            }
            if (vh.mImageView.getTopLevelDrawable() != null) {
                vh.mImageView.getTopLevelDrawable().setCallback(null);
            }
        }

    }
}


