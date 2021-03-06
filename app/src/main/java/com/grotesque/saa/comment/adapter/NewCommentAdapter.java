package com.grotesque.saa.comment.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.IntegerRes;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Layout;
import android.text.Spannable;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.common.util.UriUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.grotesque.saa.R;
import com.grotesque.saa.comment.data.CommentData;
import com.grotesque.saa.common.Drawables;
import com.grotesque.saa.common.widget.ThumbnailView;
import com.grotesque.saa.common.widget.YoutubeThumbnailView;
import com.grotesque.saa.content.data.ContentItem;
import com.grotesque.saa.util.FontManager;
import com.grotesque.saa.util.NavigationUtils;
import com.grotesque.saa.util.ParseUtils;
import com.grotesque.saa.util.StringUtils;
import com.grotesque.saa.util.WebViewUtils;
import com.grotesque.saa.util.YouTubeUtils;

import java.util.ArrayList;
import java.util.Locale;

import static com.grotesque.saa.util.LogUtils.LOGE;
import static com.grotesque.saa.util.LogUtils.makeLogTag;

/**
 * Created by KH on 2016-09-01.
 */
public class NewCommentAdapter extends RecyclerView.Adapter<NewCommentAdapter.CommentViewHolder> {
    private static final String TAG = makeLogTag(CommentAdapter.class);
    private Context mContext;
    private ArrayList<CommentData> mCurrentCommentItems;
    private LayoutInflater mLayoutInflater;
    private Listener mListener;

    public NewCommentAdapter(Context context, ArrayList<CommentData> objects, Listener listener) {
        this.mContext = context;
        this.mCurrentCommentItems = objects;
        this.mListener = listener;
        this.mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    public class CommentViewHolder extends RecyclerView.ViewHolder{

        ImageView mProfileView;
        TextView mNickNameView;
        TextView mTimeView;
        TextView mReplyView;
        View mChainUp;
        View mChainDown;
        View mChainEnd;
        View mDivider;
        LinearLayout mContentLayout;
        LinearLayout mInfoLayout;
        FrameLayout mCommentReplyLayout;
        FrameLayout mCommentLayout;

        public CommentViewHolder(View itemView) {
            super(itemView);

            mProfileView = (ImageView) itemView.findViewById(R.id.comment_profile_image);
            mNickNameView = (TextView) itemView.findViewById(R.id.comment_name);
            mTimeView = (TextView) itemView.findViewById(R.id.comment_time);
            mReplyView = (TextView) itemView.findViewById(R.id.comment_reply);
            mChainUp = itemView.findViewById(R.id.comment_chain_above);
            mChainDown = itemView.findViewById(R.id.comment_chain_below);
            mChainEnd = itemView.findViewById(R.id.comment_chain_end);
            mDivider = itemView.findViewById(R.id.comment_divider);
            mContentLayout = (LinearLayout) itemView.findViewById(R.id.comment_content_view);
            mInfoLayout = (LinearLayout) itemView.findViewById(R.id.ll_comment_info);
            mCommentReplyLayout = (FrameLayout) itemView.findViewById(R.id.comment_reply_body);
            mCommentLayout = (FrameLayout) itemView.findViewById(R.id.comment_body);

            mNickNameView.setTypeface(FontManager.getInstance(mContext).getTypeface());
            mTimeView.setTypeface(FontManager.getInstance(mContext).getTypeface());
            mReplyView.setTypeface(FontManager.getInstance(mContext).getTypeface());
        }
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow_comment_item_reply, parent, false);
        return new NewCommentAdapter.CommentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final CommentViewHolder holder, final int position) {
        if (mCurrentCommentItems.get(position) != null) {
            holder.mCommentLayout.setBackgroundColor(Color.parseColor("#ffffff"));
            holder.mChainUp.setVisibility(View.GONE);
            holder.mChainDown.setVisibility(View.GONE);
            holder.mDivider.setVisibility(View.VISIBLE);
            holder.mProfileView.setVisibility(View.VISIBLE);
            holder.mInfoLayout.setVisibility(View.VISIBLE);

            holder.mTimeView.setText(StringUtils.getCommentTime(mCurrentCommentItems.get(position).getRegdate()));
            holder.mNickNameView.setText(mCurrentCommentItems.get(position).getNickName());

            if(mCurrentCommentItems.get(position).getCommentDatas().size() == 0)
                holder.mReplyView.setVisibility(View.GONE);
            else {
                if(mCurrentCommentItems.size() > position + 1) {
                    if (Integer.parseInt(mCurrentCommentItems.get(position + 1).getDepth()) > Integer.parseInt(mCurrentCommentItems.get(position).getDepth()))
                        holder.mReplyView.setVisibility(View.GONE);
                    else{
                        holder.mReplyView.setVisibility(View.VISIBLE);
                        holder.mReplyView.setText(String.format(Locale.KOREA, "답변 %d", mCurrentCommentItems.get(position).getCommentDatas().size()));
                    }
                }

            }

            holder.mCommentReplyLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.mReplyView.setVisibility(View.GONE);
                    mListener.onReplyClick(position);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mListener.onCommentLongClick(position);
                    return false;
                }
            });

            ArrayList<ContentItem> contentItems = ParseUtils.parseContent(mCurrentCommentItems.get(position).getContent());

            holder.mContentLayout.removeAllViews();

            String toComment = "";
            String parentSrl = mCurrentCommentItems.get(position).getParentSrl();

            if(!parentSrl.equals("0")) {
                holder.mCommentLayout.setBackgroundColor(Color.parseColor("#ececec"));
                for(CommentData c : mCurrentCommentItems){
                    if(c.getCommentSrl().equals(parentSrl))
                        toComment = "<FONT color=#1abc9c>@" + c.getNickName() + "</FONT>";
                }
                ArrayList<ContentItem> tempArray = new ArrayList<>();
                tempArray.add(new ContentItem(toComment, null, null, "text", false));
                tempArray.addAll(contentItems);
                contentItems.clear();
                contentItems.addAll(tempArray);
            }

            for (ContentItem s : contentItems) {
                switch (s.getType()){
                    case "text":
                        LinearLayout childView = (LinearLayout) mLayoutInflater.inflate(R.layout.layout_comment_text, null);
                        TextView textview = (TextView) childView.findViewById(R.id.commentView);
                        textview.setTypeface(FontManager.getInstance(mContext).getTypeface());
                        textview.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                boolean ret = false;
                                CharSequence text = ((TextView) v).getText();
                                Spannable stext = Spannable.Factory.getInstance().newSpannable(text);
                                TextView widget = (TextView) v;
                                int action = event.getAction();

                                if (action == MotionEvent.ACTION_UP ||
                                        action == MotionEvent.ACTION_DOWN) {
                                    int x = (int) event.getX();
                                    int y = (int) event.getY();

                                    x -= widget.getTotalPaddingLeft();
                                    y -= widget.getTotalPaddingTop();

                                    x += widget.getScrollX();
                                    y += widget.getScrollY();

                                    Layout layout = widget.getLayout();
                                    int line = layout.getLineForVertical(y);
                                    int off = layout.getOffsetForHorizontal(line, x);

                                    ClickableSpan[] link = stext.getSpans(off, off, ClickableSpan.class);

                                    if (link.length != 0) {
                                        if (action == MotionEvent.ACTION_UP) {
                                            link[0].onClick(widget);
                                        }
                                        ret = true;
                                    }
                                }
                                return ret;
                            }
                        });
                        CharSequence trimmed = StringUtils.trimTrailingWhitespace(Html.fromHtml(s.getText()));
                        textview.setText(trimmed);
                        holder.mContentLayout.addView(childView);
                        break;
                    case "image":
                        LOGE(TAG, s.getImg());
                        LinearLayout childView1 = (LinearLayout) mLayoutInflater.inflate(R.layout.layout_comment_image, null);
                        SimpleDraweeView imageview = (SimpleDraweeView) childView1.findViewById(R.id.iv_photo);

                        ProgressBarDrawable progressBarDrawable = new ProgressBarDrawable();
                        progressBarDrawable.setPadding(0);
                        progressBarDrawable.setColor(R.color.brunch_mint);

                        GenericDraweeHierarchy gdh = new GenericDraweeHierarchyBuilder(mContext.getResources())
                                .setFailureImage(Drawables.sErrorDrawable)
                                .setProgressBarImage(progressBarDrawable)
                                .setPlaceholderImage(Drawables.sPlaceholderDrawable, ScalingUtils.ScaleType.CENTER_CROP)
                                .setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER)
                                .build();
                        imageview.setHierarchy(gdh);

                        holder.mContentLayout.addView(childView1);

                        final String imgUrl = StringUtils.convertImgUrl(s.getImg());
                        Uri uri = Uri.parse(imgUrl);
                        ImageRequestBuilder imageRequestBuilder =
                                ImageRequestBuilder.newBuilderWithSource(uri);
                        if (UriUtil.isNetworkUri(uri)) {
                            imageRequestBuilder.setProgressiveRenderingEnabled(true);
                        }


                        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                                .setImageRequest(imageRequestBuilder.build())
                                .setOldController(imageview.getController())
                                .setAutoPlayAnimations(true)
                                .setTapToRetryEnabled(true)
                                .build();
                        imageview.setController(draweeController);
                        imageview.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                NavigationUtils.goImageViewActivity(mContext, imgUrl, 1);
                            }
                        });
                        break;
                    case "video":
                        LinearLayout childView2 = (LinearLayout) mLayoutInflater.inflate(R.layout.layout_comment_video, null);

                        int videoType = s.getVideo().getType();
                        switch (videoType){
                            case 0:
                                final ThumbnailView naverView = new ThumbnailView(mContext);
                                childView2.addView(naverView);
                                naverView.setNaver(s.getVideo());
                                break;
                            case 1:
                                final ThumbnailView daumView = new ThumbnailView(mContext);
                                childView2.addView(daumView);
                                daumView.setDaum(s.getVideo().getVid());
                                break;
                            case 2:
                                YoutubeThumbnailView youtubeThumbnailView = new YoutubeThumbnailView(mContext);
                                childView2.addView(youtubeThumbnailView);

                                final String videoId = s.getVideo().getVid();
                                youtubeThumbnailView.setYouTubeThumbnailListener(new YoutubeThumbnailView.OnClickListener() {
                                    @Override
                                    public void playYoutube() {
                                        YouTubeUtils.showYouTubeVideo(videoId, (Activity) mContext);
                                    }
                                });
                                youtubeThumbnailView.loadImage(videoId);
                                break;
                            default:
                                WebView webView = new WebView(mContext);
                                WebViewUtils.setWebview(webView);
                                childView2.addView(webView);
                                webView.loadDataWithBaseURL("http://highbury.co.kr", "<body style='margin:0px;padding:0px;'> " + s.getVideo().getUrl() , "text/html", "UTF-8", null);
                                break;
                        }

                        holder.mContentLayout.addView(childView2);
                        break;

                }
            }
        }
    }


    @Override
    public int getItemCount() {
        return mCurrentCommentItems.size();
    }

    public interface Listener{
        void onCommentLongClick(int position);
        void onReplyClick(int position);
    }
}
