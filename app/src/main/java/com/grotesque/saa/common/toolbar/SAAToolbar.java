package com.grotesque.saa.common.toolbar;

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3)


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.grotesque.saa.R;
import com.grotesque.saa.util.FontManager;
import com.grotesque.saa.util.DensityScaleUtil;

import static com.grotesque.saa.util.LogUtils.LOGE;
import static com.grotesque.saa.util.LogUtils.makeLogTag;

public class SAAToolbar extends RelativeLayout implements View.OnClickListener {
    private static final String TAG = makeLogTag(SAAToolbar.class);

    public static final int COMMON_BAR_CLICK_CANCEL = 0;
    public static final int COMMON_BAR_CLICK_MORE = 1;
    public static final int COMMON_BAR_CLICK_DELETE = 2;
    public static final int COMMON_BAR_CLICK_SEARCH = 3;
    public static final int COMMON_BAR_CLICK_REFRESH = 4;

    private boolean d = false;
    private Context context = null;
    private TextView mTitleView;
    private TextView mCountView;
    private ImageButton mLeftBtn;
    private ImageButton mRightBtn;
    private ImageButton mSearchBtn;
    private ImageButton mWriteBtn;
    private ImageButton mDeleteBtn;
    private ImageButton mRefreshBtn;
    private LinearLayout mBackgroundLayout;
    private LinearLayout mAnimationLayout;
    private String mTitle;
    private AnimatorSet t;
    private AnimatorSet u;
    private OnCommonToolBarClickListener mClickListener = null;

    public SAAToolbar(Context context)
    {
        super(context);
        this.context = context;
        registerView();
    }

    public SAAToolbar(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public SAAToolbar(Context context, AttributeSet attributeset, int k)
    {
        super(context, attributeset, k);
        this.context = context;
        registerView();
        registerView(context, attributeset);
    }

    private void registerView()
    {
        LayoutInflater.from(context).inflate(R.layout.layout_comment_toolbar, this, true);
        mBackgroundLayout = (LinearLayout) findViewById(R.id.commentTitleBar);
        mAnimationLayout = (LinearLayout) findViewById(R.id.animationLayer);
        mTitleView = (TextView)findViewById(R.id.toolbar_titleView);
        mCountView = (TextView)findViewById(R.id.toolbar_titleCountView);
        mRightBtn = (ImageButton)findViewById(R.id.toolbar_applyButton);
        mLeftBtn = (ImageButton)findViewById(R.id.toolbar_cancelButton);
        mWriteBtn = (ImageButton) findViewById(R.id.toolbar_write);
        mDeleteBtn = (ImageButton) findViewById(R.id.toolbar_delete);

        mLeftBtn.setOnClickListener(this);
        mRightBtn.setOnClickListener(this);

        mTitleView.setTypeface(FontManager.getInstance(context).getTypeface());
        mCountView.setTypeface(FontManager.getInstance(context).getTypeface());

        registerAnimation();
    }

    private void registerView(Context context, AttributeSet attributeset)
    {
        TypedArray styledAttributes = context.obtainStyledAttributes(attributeset, R.styleable.BaseActionBar);
        setToolbarType(styledAttributes.getInt(R.styleable.BaseActionBar_actionbarType, 0));
        mRightBtn.setImageDrawable(styledAttributes.getDrawable(R.styleable.BaseActionBar_rightIcon));
        mLeftBtn.setImageDrawable(styledAttributes.getDrawable(R.styleable.BaseActionBar_leftIcon));
        if(styledAttributes.getBoolean(R.styleable.BaseActionBar_refreshEnable, false))
            mRightBtn.setImageResource(R.drawable.selector_comm_btn_search);
        mTitleView.setText(styledAttributes.getString(R.styleable.BaseActionBar_actionbarTitle));
        mTitleView.setTextColor(styledAttributes.getInt(R.styleable.BaseActionBar_textColor, 0));
        mBackgroundLayout.setBackground(styledAttributes.getDrawable(R.styleable.BaseActionBar_actionbarBackground));

    }

    private void registerAnimation()
    {
        ObjectAnimator objectanimator = ObjectAnimator.ofFloat(mAnimationLayout, View.TRANSLATION_Y, (float) DensityScaleUtil.dipToPixel(context, -50F), 0.0F);
        objectanimator.setInterpolator(new DecelerateInterpolator());
        ObjectAnimator objectanimator1 = ObjectAnimator.ofFloat(mRightBtn, View.ROTATION, -90F, 0.0F);
        objectanimator1.setInterpolator(new LinearInterpolator());
        u = new AnimatorSet();
        u.setDuration(200L);
        u.playTogether(objectanimator, objectanimator1);


        objectanimator = ObjectAnimator.ofFloat(mAnimationLayout, View.TRANSLATION_Y, 0.0F, (float) DensityScaleUtil.dipToPixel(context, -50F));
        objectanimator.setInterpolator(new DecelerateInterpolator());
        objectanimator1 = ObjectAnimator.ofFloat(mRightBtn, View.ROTATION, 0.0F, -90F);
        objectanimator1.setInterpolator(new LinearInterpolator());
        t = new AnimatorSet();
        t.setDuration(200L);
        t.playTogether(objectanimator, objectanimator1);
    }
    public void startAnimation()
    {
        if(d){
            if(!t.isRunning()){
                u.start();
                d = false;
            }
        } else if(!u.isRunning()){
            t.start();
            d = true;
            return;
        }
    }
    public boolean getClicked(){
        return d;
    }
    public void setClicked(boolean clicked, boolean equal, boolean delete){

        if(equal){
            mRefreshBtn.setVisibility(VISIBLE);
            mDeleteBtn.setVisibility(delete ? VISIBLE : GONE);
            mWriteBtn.setVisibility(VISIBLE);
        }else{
            mWriteBtn.setVisibility(VISIBLE);
            mDeleteBtn.setVisibility(GONE);
            mRefreshBtn.setVisibility(GONE);
        }
        if(clicked){
            if(!d){
                if(!u.isRunning()){
                    t.start();
                    d = true;
                    return;
                }
            }
        }else{
            if(d){
                if(!t.isRunning()){
                    u.start();
                    d = false;
                }
            }
        }
    }
    public void setClicked(boolean clicked, boolean equal){
        if(clicked){
            mRightBtn.setVisibility(GONE);
            if(equal){
                mWriteBtn.setVisibility(VISIBLE);
                mDeleteBtn.setVisibility(VISIBLE);

                mRefreshBtn.setImageResource(R.drawable.selector_editor_btn_text);
                mRefreshBtn.setVisibility(VISIBLE);
            }else{
                mWriteBtn.setVisibility(VISIBLE);
                mDeleteBtn.setVisibility(GONE);
                mRefreshBtn.setVisibility(GONE);
            }

            if(!d){
                if(!u.isRunning()){
                    t.start();
                    d = true;
                    return;
                }
            }
        }else{
            mRightBtn.setVisibility(VISIBLE);
            mWriteBtn.setVisibility(GONE);
            mDeleteBtn.setVisibility(GONE);
            mRefreshBtn.setVisibility(VISIBLE);
            //mRefreshBtn.setImageResource(R.drawable.selector_comm_btn_refresh);
            if(d){
                if(!t.isRunning()){
                    u.start();
                    d = false;
                }
            }
        }
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
    }

    public void setTitle(SpannableString spannablestring)
    {
        mTitle = spannablestring.toString();
        if(spannablestring != null && spannablestring.length() > 0)
            mTitleView.setText(spannablestring);
    }

    public void setTitle(String s)
    {
        mTitle = s;
        if(s != null && s.length() > 0)
            mTitleView.setText(s);
    }

    public void setTitleCount(int k)
    {
        if(k < 1)
        {
            mCountView.setText("");
            mCountView.setVisibility(GONE);
            return;
        } else
        {
            mCountView.setVisibility(VISIBLE);
            mCountView.setText(String.valueOf(k));
            return;
        }
    }
    public void setToolbarType(int type){
        if(type == 0){
            mRefreshBtn.setImageResource(R.drawable.selector_editor_btn_text);
            mRightBtn.setVisibility(INVISIBLE);
            mCountView.setVisibility(INVISIBLE);

            mCountView.setVisibility(GONE);
            mRefreshBtn.setVisibility(GONE);

            mWriteBtn.setVisibility(VISIBLE);
            mDeleteBtn.setVisibility(VISIBLE);
        }else if(type == 1){
            mRightBtn.setVisibility(VISIBLE);
            mCountView.setVisibility(GONE);
            mRefreshBtn.setVisibility(VISIBLE);

            mWriteBtn.setVisibility(GONE);
            mDeleteBtn.setVisibility(GONE);

            mBackgroundLayout.setBackgroundColor(context.getResources().getColor(R.color.brunchTransparent));
        }else if(type == 2){
            mRightBtn.setVisibility(VISIBLE);
            mCountView.setVisibility(INVISIBLE);

            mRefreshBtn.setVisibility(GONE);
            mWriteBtn.setVisibility(GONE);
            mDeleteBtn.setVisibility(GONE);
        }else if(type == 3){
            mTitleView.setVisibility(GONE);
            mRightBtn.setVisibility(GONE);
            mCountView.setVisibility(GONE);

            mRefreshBtn.setVisibility(GONE);
            mWriteBtn.setVisibility(GONE);
            mDeleteBtn.setVisibility(GONE);

            mBackgroundLayout.setBackgroundColor(context.getResources().getColor(R.color.brunchTransparent));
        }else if(type == 4){

            mRightBtn.setVisibility(INVISIBLE);
            mCountView.setVisibility(GONE);
            mRefreshBtn.setVisibility(GONE);
            mWriteBtn.setVisibility(GONE);
            mDeleteBtn.setVisibility(GONE);

            mBackgroundLayout.setBackgroundColor(context.getResources().getColor(R.color.brunchTransparent));
        }else{
            mRightBtn.setVisibility(INVISIBLE);
            mCountView.setVisibility(GONE);
            mRefreshBtn.setVisibility(GONE);
            mWriteBtn.setVisibility(GONE);
            mDeleteBtn.setVisibility(GONE);
        }
    }

    public void setOnCommonToolBarClickListener(OnCommonToolBarClickListener commonToolBarClickListener){
        this.mClickListener = commonToolBarClickListener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toolbar_cancelButton:
                mClickListener.onCommonToolBarClick(COMMON_BAR_CLICK_CANCEL);
                return;
            case R.id.toolbar_applyButton:
                mClickListener.onCommonToolBarClick(COMMON_BAR_CLICK_MORE);
                return;
            case R.id.toolbar_delete:
                mClickListener.onCommonToolBarClick(COMMON_BAR_CLICK_DELETE);
                return;
        }
    }

    public interface OnCommonToolBarClickListener{
        void onCommonToolBarClick(int i);
    }
}
