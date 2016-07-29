package com.grotesque.saa.board.holder;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.grotesque.saa.common.holder.BaseViewHolder;
import com.grotesque.saa.common.widget.CustomProgressbarDrawable;
import com.grotesque.saa.home.data.DocumentList;
import com.grotesque.saa.util.FontManager;
import com.grotesque.saa.util.NavigationUtils;
import com.grotesque.saa.util.ParseUtils;
import com.grotesque.saa.util.StringUtils;
import com.grotesque.saa.util.UIUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by KH on 2016-07-26.
 */
public class BoardCardHolder extends BaseViewHolder<DocumentList> {

    TextView mByView;
    TextView mCountView;
    TextView mTimeView;
    TextView mTitleView;
    TextView mSubTitleView;
    TextView mUserView;
    TextView mCommentCountView;
    TextView mVoteCountView;
    SimpleDraweeView mImageView;

    public BoardCardHolder(Context context, View itemView) {
        super(context, itemView);

        mByView = (TextView) itemView.findViewById(R.id.byView);
        mByView.setTypeface(FontManager.getInstance(context).getTypefaceItalic());
        mTitleView = (TextView) itemView.findViewById(R.id.titleView);
        mTitleView.setTypeface(FontManager.getInstance(context).getTypeface());
        mSubTitleView = (TextView) itemView.findViewById(R.id.subtitleView);
        mSubTitleView.setTypeface(FontManager.getInstance(context).getTypeface());
        mUserView = (TextView) itemView.findViewById(R.id.userView);
        mUserView.setTypeface(FontManager.getInstance(context).getTypeface());
        mTimeView = (TextView) itemView.findViewById(R.id.timeView);
        mTimeView.setTypeface(FontManager.getInstance(context).getTypeface());
        mCountView = (TextView)itemView.findViewById(R.id.countView);
        mCountView.setTypeface(FontManager.getInstance(context).getTypefaceItalic());
        mCommentCountView = (TextView) itemView.findViewById(R.id.commentCountView);
        mCommentCountView.setTypeface(FontManager.getInstance(context).getTypeface());
        mVoteCountView = (TextView) itemView.findViewById(R.id.voteCountView);
        mVoteCountView.setTypeface(FontManager.getInstance(context).getTypeface());

        mImageView = (SimpleDraweeView) itemView.findViewById(R.id.imageView);
        GenericDraweeHierarchy gdh = new GenericDraweeHierarchyBuilder(getContext().getResources())
                .setFailureImage(Drawables.sErrorDrawable)
                .setProgressBarImage(new CustomProgressbarDrawable())
                .setPlaceholderImage(Drawables.sPlaceholderDrawable, ScalingUtils.ScaleType.CENTER_CROP)
                .setActualImageScaleType(ScalingUtils.ScaleType.FOCUS_CROP)
                .build();
        mImageView.setHierarchy(gdh);
    }

    public static BoardCardHolder newInstance(Context context, ViewGroup parent){
        return new BoardCardHolder(context, LayoutInflater.from(context).inflate(R.layout.listrow_board_card, parent, false));
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
        mSubTitleView.setText(title.contains("스포") ? "" : Html.fromHtml(item.get(position).getContent().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "")));
        mTimeView.setText(StringUtils.convertDate(item.get(position).getRegdate()));
        mCountView.setText(String.format(Locale.US, "조회 %s", item.get(position).getReadedCount()));
        mUserView.setText(item.get(position).getNickName());
        mCommentCountView.setText(String.format(Locale.US, "%s", item.get(position).getCommentCount()));
        mVoteCountView.setText(item.get(position).getVotedCount());

        if(item.get(position).hasImg()){
            mImageView.setVisibility(View.VISIBLE);
            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(ParseUtils.parseImgUrl(item.get(position).getContent())))
                    .build();
            DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(request)
                    .setOldController(mImageView.getController())
                    .build();
            mImageView.setController(draweeController);
        }else{
            mImageView.setVisibility(View.GONE);
        }


        mCommentCountView.setTextColor(UIUtils.getCategoryColor(item.get(position).getCategorySrl()));
        mVoteCountView.setTextColor(UIUtils.getCategoryColor(item.get(position).getCategorySrl()));


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationUtils.goContentActivity(getContext(), mid, (ArrayList<DocumentList>) item, position);
            }
        });
    }
}
