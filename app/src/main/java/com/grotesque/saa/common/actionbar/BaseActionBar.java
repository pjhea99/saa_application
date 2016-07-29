package com.grotesque.saa.common.actionbar;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.grotesque.saa.R;
import com.grotesque.saa.model.ActionBarType;
import com.grotesque.saa.util.DensityScaleUtil;

import static com.grotesque.saa.util.LogUtils.makeLogTag;


public class BaseActionBar extends LinearLayout implements View.OnClickListener{

    private static final String TAG = makeLogTag(BaseActionBar.class);

    private int mLeftIconW;
    private int mRightIconW;
    private int mActionBarBackgroundW;
    private boolean mIsBlack;
    private String mLeftTabTitle;
    private String mRightTabTitle;
    private OnActionBarListener mOnActionBarListener;
    private OnSelectedTitleTab H;
    private TitleTap mTitleTap;
    private String a;
    private Context mContext = null;
    private LinearLayout mTopModeLayout;
    private LinearLayout mCommonModeLayout;
    private LinearLayout mSingleImgModeLayout;
    private ImageButton mLeftBtn;
    private ImageButton mRightBtn;
    private RelativeLayout mLeftTabLayout;
    private RelativeLayout mRightTabLayout;
    private TextView mLeftTabTitleView;
    private TextView mRightTabTitleView;
    private ImageView mTitleImgView;
    private TextView mTitleView;
    private TextView mTitleCountView;
    private ImageView mMenuNewIcon;
    private ActionBarType mActionBarType;
    private boolean mSearchEnable;
    private String mActionBarTitle;
    private int mTitleImage;
    private int mLeftIcon;
    private int mRightIcon;
    private int mActionBarBackground;
    private int mTitleColor;
    private String mLeftBtnDescription;
    private String mRightBtnDescription;
    private int mTitleImageW;

    public BaseActionBar(Context context) {
        super(context);
        mTitleTap = TitleTap.LEFT;
        this.mContext = context;
        initUI();
        b();
    }

    public BaseActionBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseActionBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTitleTap = TitleTap.LEFT;
        mContext = context;
        initUI();
        initUI(context, attrs);
        b();
    }

    static OnSelectedTitleTab a(BaseActionBar brunchactionbar)
    {
        return brunchactionbar.H;
    }

    private void initUI()
    {
        LayoutInflater.from(mContext).inflate(R.layout.layout_common_action_bar, this, true);
        mTopModeLayout = (LinearLayout)findViewById(R.id.top_mode);
        mCommonModeLayout = (LinearLayout)findViewById(R.id.common_mode);
        mSingleImgModeLayout = (LinearLayout)findViewById(R.id.singleImg_mode);
        mTitleView = (TextView)findViewById(R.id.title);
        mTitleView.setOnClickListener(this);
        mTitleCountView = (TextView)findViewById(R.id.titleCount);
        if(mActionBarTitle != null)
            mTitleView.setText(mActionBarTitle);
        mLeftTabLayout = (RelativeLayout)findViewById(R.id.leftTabLayout);
        mRightTabLayout = (RelativeLayout)findViewById(R.id.rightTabLayout);
        mLeftTabLayout.setOnClickListener(this);
        mRightTabLayout.setOnClickListener(this);
        mLeftBtn = (ImageButton)findViewById(R.id.leftButton);
        mLeftBtn.setOnClickListener(this);
        mRightBtn = (ImageButton)findViewById(R.id.rightButton);
        mRightBtn.setOnClickListener(this);
        mLeftTabTitleView = (TextView)findViewById(R.id.leftTabTitle);
        mRightTabTitleView = (TextView)findViewById(R.id.rightTabTitle);
        mTitleImgView = (ImageView)findViewById(R.id.titleImageView);
        mMenuNewIcon = (ImageView)findViewById(R.id.menu_new_icon);
    }

    private void initUI(Context context, AttributeSet attributeset)
    {
        TypedArray styledAttributes = context.obtainStyledAttributes(attributeset, R.styleable.BaseActionBar);

        int type = styledAttributes.getInt(R.styleable.BaseActionBar_actionbarType, 0);
        switch (type){
            case 0:
                mActionBarType = ActionBarType.COMMON;
                break;
            case 1:
                mActionBarType = ActionBarType.TOP;
                break;
            case 2:
                mActionBarType = ActionBarType.SINGLE_IMG;
                break;
        }

        mSearchEnable = styledAttributes.getBoolean(R.styleable.BaseActionBar_searchEnable, false);
        mIsBlack = styledAttributes.getBoolean(R.styleable.BaseActionBar_isBlack, true);
        mActionBarTitle = styledAttributes.getString(R.styleable.BaseActionBar_actionbarTitle);
        mTitleColor = styledAttributes.getInt(R.styleable.BaseActionBar_titleColor, 0);
        mTitleImage = styledAttributes.getResourceId(R.styleable.BaseActionBar_titleImage, 0);
        mTitleImageW = styledAttributes.getResourceId(R.styleable.BaseActionBar_titleImage_w, 0);
        mRightIcon = styledAttributes.getResourceId(R.styleable.BaseActionBar_rightIcon, 0);
        mRightIconW = styledAttributes.getResourceId(R.styleable.BaseActionBar_rightIcon_w, 0);
        mLeftIcon = styledAttributes.getResourceId(R.styleable.BaseActionBar_leftIcon, 0);
        mLeftIconW = styledAttributes.getResourceId(R.styleable.BaseActionBar_leftIcon_w, 0);
        mActionBarBackground = styledAttributes.getResourceId(R.styleable.BaseActionBar_actionbarBackground, 0);
        mActionBarBackgroundW = styledAttributes.getResourceId(R.styleable.BaseActionBar_actionbarBackground_w, 0);
        mLeftTabTitle = styledAttributes.getString(R.styleable.BaseActionBar_leftTabTitle);
        mRightTabTitle = styledAttributes.getString(R.styleable.BaseActionBar_rightTabTitle);
        mLeftBtnDescription = styledAttributes.getString(R.styleable.BaseActionBar_leftButtonDescription);
        mRightBtnDescription = styledAttributes.getString(R.styleable.BaseActionBar_rightButtonDescription);
    }

    static OnActionBarListener b(BaseActionBar brunchactionbar)
    {
        return brunchactionbar.mOnActionBarListener;
    }

    private void b() {
        switch (mActionBarType.ordinal()){
            case 0:
                mTopModeLayout.setVisibility(GONE);
                mCommonModeLayout.setVisibility(VISIBLE);
                mSingleImgModeLayout.setVisibility(GONE);
                if(mIsBlack){
                    if(mTitleImage > 0)
                    {
                        mTitleImgView.setImageResource(mTitleImage);
                        mTitleView.setVisibility(GONE);
                        mTitleCountView.setVisibility(GONE);
                    }else if(mActionBarTitle != null){
                        mTitleView.setText(mActionBarTitle);
                        mTitleImgView.setVisibility(GONE);
                    }
                } else if(mTitleImageW > 0) {
                    mTitleImgView.setImageResource(mTitleImageW);
                    mTitleView.setVisibility(GONE);
                    mTitleCountView.setVisibility(GONE);
                } else if(mActionBarTitle != null){
                    mTitleView.setText(mActionBarTitle);
                    mTitleImgView.setVisibility(GONE);
                }
                break;
            case 2:
                mTopModeLayout.setVisibility(GONE);
                mCommonModeLayout.setVisibility(GONE);
                mSingleImgModeLayout.setVisibility(VISIBLE);
                break;
            case 1:
                mTopModeLayout.setVisibility(VISIBLE);
                mCommonModeLayout.setVisibility(GONE);
                mSingleImgModeLayout.setVisibility(GONE);
                if(mLeftTabTitle != null)
                    mLeftTabTitleView.setText(mLeftTabTitle);
                if(mRightTabTitle != null)
                    mRightTabTitleView.setText(mRightTabTitle);
                c();
                break;

        }

        if(mTitleColor != 0)
            mTitleView.setTextColor(mTitleColor);
        if(mIsBlack) {
            if (mRightIcon > 0) {
                mRightBtn.setImageResource(mRightIcon);
                mRightBtn.setVisibility(VISIBLE);
                if (mRightBtnDescription != null)
                    mRightBtn.setContentDescription(mRightBtnDescription);
            } else {
                mRightBtn.setVisibility(INVISIBLE);
            }
            if (mLeftIcon > 0) {
                mLeftBtn.setImageResource(mLeftIcon);
                mLeftBtn.setVisibility(VISIBLE);
                if (mLeftBtnDescription != null)
                    mLeftBtn.setContentDescription(mLeftBtnDescription);
            } else {
                mLeftBtn.setVisibility(INVISIBLE);
            }
            if (mActionBarBackground > 0) {
                setBackgroundResource(mActionBarBackground);
                return;
            } else {
                setBackgroundColor(getResources().getColor(R.color.brunchTransparent));
                return;
            }
        }

        if(mLeftIconW > 0)
        {
            mLeftBtn.setImageResource(mLeftIconW);
            mLeftBtn.setVisibility(VISIBLE);
            if(mLeftBtnDescription != null)
                mLeftBtn.setContentDescription(mLeftBtnDescription);
        } else
        {
            mLeftBtn.setVisibility(INVISIBLE);
        }
        if(mRightIconW > 0)
        {
            mRightBtn.setImageResource(mRightIconW);
            mRightBtn.setVisibility(VISIBLE);
            if(mRightBtnDescription != null)
                mRightBtn.setContentDescription(mRightBtnDescription);
        } else
        {
            mRightBtn.setVisibility(INVISIBLE);
        }

        if(mActionBarBackgroundW > 0)
        {
            setBackgroundResource(mActionBarBackgroundW);
        } else
        {
            setBackgroundColor(getResources().getColor(R.color.brunchTransparent));
        }

    }

    private void c()
    {
        switch(mTitleTap.ordinal()){
            case 0:
                mLeftTabLayout.setSelected(true);
                mRightTabLayout.setSelected(false);
                mLeftTabTitleView.setTextColor(getResources().getColor(R.color.brunch_tap_title_color_selected));
                mRightTabTitleView.setTextColor(getResources().getColor(R.color.brunch_tap_title_color));
                break;
            case 1:
                mLeftTabLayout.setSelected(false);
                mRightTabLayout.setSelected(true);
                mLeftTabTitleView.setTextColor(getResources().getColor(R.color.brunch_tap_title_color));
                mRightTabTitleView.setTextColor(getResources().getColor(R.color.brunch_tap_title_color_selected));
                break;
        }
        invalidate();
        return;
    }

    static boolean c(BaseActionBar brunchactionbar)
    {
        return brunchactionbar.mSearchEnable;
    }

    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        Paint paint = new Paint();
        int j1 = DensityScaleUtil.dipToPixel(mContext, 1.0F);
        int ai[] = new int[2];
        if(mTopModeLayout.getVisibility() == VISIBLE)
        {
            int i1 = 0;
            while(i1 < j1)
            {
                int k1 = canvas.getHeight() - (j1 - i1);
                if(mLeftTabLayout.isSelected())
                {
                    mLeftTabTitleView.getLocationOnScreen(ai);
                    paint.setColor(0xff00c3bd);
                    canvas.drawLine(ai[0], k1, ai[0] + mLeftTabTitleView.getWidth(), k1, paint);
                } else
                {
                    mRightTabTitleView.getLocationOnScreen(ai);
                    paint.setColor(0xff00c3bd);
                    canvas.drawLine(ai[0], k1, ai[0] + mRightTabTitleView.getWidth(), k1, paint);
                }
                i1++;
            }
        }
    }

    public void selectTitleTab(TitleTap titletap)
    {
        mTitleTap = titletap;
       // c();
        if(H != null)
            H.onSelectedTab(titletap);
    }
    public void setActionBarBackgroundResource(int resource){
        setBackgroundResource(resource);
    }

    public void setActionBarColor(int i1)
    {
        mRightBtn.setColorFilter(i1, PorterDuff.Mode.SRC_IN);
        mLeftBtn.setColorFilter(i1, PorterDuff.Mode.SRC_IN);
        mTitleImgView.setColorFilter(i1, PorterDuff.Mode.SRC_IN);
        mTitleView.setTextColor(i1);
    }

    public void setDiscoverVisibility(boolean flag)
    {
        if(flag)
        {
            if(getVisibility() == GONE) {
                ObjectAnimator objectanimator = ObjectAnimator.ofFloat(this, View.ALPHA, 0.0F, 1.0F);
                objectanimator.setInterpolator(new LinearInterpolator());
                objectanimator.setDuration(300L);
                objectanimator.start();
                setVisibility(VISIBLE);
            }
            /*
            mRightBtn.setVisibility(VISIBLE);
            mLeftBtn.setVisibility(VISIBLE);
            mCommonModeLayout.setVisibility(VISIBLE);
            */
            return;
        } else {
            setVisibility(GONE);
            /*
            mRightBtn.setVisibility(INVISIBLE);
            mLeftBtn.setVisibility(INVISIBLE);
            mCommonModeLayout.setVisibility(INVISIBLE);
            */
            return;
        }
    }

    public void setHasNewMessage(boolean flag)
    {
        setSelected(flag);
        if(flag)
        {
            mMenuNewIcon.setVisibility(VISIBLE);
            return;
        } else
        {
            mMenuNewIcon.setVisibility(GONE);
            return;
        }
    }

    public void setLeftButton(int i1)
    {
        mLeftIcon = i1;
        if(mLeftIcon > 0)
            mRightBtn.setImageResource(mLeftIcon);
        if(mLeftIcon > 0)
        {
            mRightBtn.setVisibility(VISIBLE);
            return;
        } else
        {
            mRightBtn.setVisibility(INVISIBLE);
            return;
        }
    }

    public void setLeftButtonContentDescription(int i1)
    {
        mRightBtn.setContentDescription(mContext.getString(i1));
    }

    public void setOnActionBarListener(OnActionBarListener onactionbarlistener)
    {
        mOnActionBarListener = onactionbarlistener;
    }

    public void setOnSelectedTitleTab(OnSelectedTitleTab onselectedtitletab)
    {
        H = onselectedtitletab;
    }

    public void setRightButton(int i1)
    {
        mRightIcon = i1;
        if(mRightIcon > 0)
            mLeftBtn.setImageResource(mRightIcon);
        if(mRightIcon > 0)
        {
            mLeftBtn.setVisibility(VISIBLE);
            return;
        } else
        {
            mLeftBtn.setVisibility(INVISIBLE);
            return;
        }
    }

    public void setRightButtonContentDescription(int i1)
    {
        mLeftBtn.setContentDescription(mContext.getString(i1));
    }

    public void setTitle(String s1)
    {
        mActionBarTitle = s1;
        mTitleView.setText(s1);
        mTitleImgView.setVisibility(GONE);
    }
    public void setTitleColor(int color){
        mTitleColor = color;
        mTitleView.setTextColor(mTitleColor);
    }
    public void setTitleCount(int i1)
    {
        if(i1 < 1)
        {
            mTitleCountView.setText("");
            mTitleCountView.setVisibility(GONE);
            return;
        }
        mTitleCountView.setVisibility(VISIBLE);
        if(i1 > 999)
        {
            mTitleCountView.setText("999+");
            return;
        } else
        {
            mTitleCountView.setText(String.valueOf(i1));
            return;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title:
                mOnActionBarListener.onTitleTextClicked();
                break;
            case R.id.leftButton:
                mOnActionBarListener.onLeftButtonClicked();
                break;
            case R.id.rightButton:
                mOnActionBarListener.onRightButtonClicked();
                break;
            case R.id.leftTabLayout:
                selectTitleTab(TitleTap.LEFT);
                break;
            case R.id.rightTabLayout:
                selectTitleTab(TitleTap.RIGHT);
                break;

        }

    }

    public enum TitleTap
    {
        LEFT ("LEFT", 0),
        RIGHT ("RIGHT", 1);

        private String direction;
        private int num;

        TitleTap(String direction, int num) {
            this.direction = direction;
            this.num = num;
        }
        public String getDirection(){
            return this.direction;
        }
    }

    public interface OnActionBarListener
    {
        void onTitleTextClicked();

        void onLeftButtonClicked();

        void onRightButtonClicked();

        void onSearchButtonClicked();
    }

    public interface OnSelectedTitleTab {
        void onSelectedTab(TitleTap titletap);
    }

}
