package com.grotesque.saa.common.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.grotesque.saa.R;
import com.grotesque.saa.util.UIUtils;


public class ControllerBrightnessAreaView extends LinearLayout{
    private VerticalSeekBar mBrightnessControlVerticalSeekBar;
    private TextView mCenterBrightnessValueTextView;
    private Context mContext;
    private ImageView mValueBg;
    private WindowManager.LayoutParams mLayoutParam;
    public static final int MAX_BRIGHTNESS = 100;
    public static final int MIN_BRIGHTNESS = 5;


    public ControllerBrightnessAreaView(Context context)
    {
        super(context);
        init(context);
    }

    public ControllerBrightnessAreaView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        init(context);
    }

    private void init(Context context)
    {
        mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.controller_brightness_area, this, true);
        mValueBg = (ImageView)findViewById(R.id.Controller_Center_BrightnessValue_BG);
        mCenterBrightnessValueTextView = (TextView)findViewById(R.id.Controller_Center_BrightnessValue);
        mBrightnessControlVerticalSeekBar = (VerticalSeekBar)findViewById(R.id.Controller_Brightness_Seekbar);
        int i = getResources().getDimensionPixelSize(R.dimen.player_img_shadow03_width);
        int j = getResources().getDimensionPixelSize(R.dimen.player_img_shadow03_height);
        mValueBg.setImageDrawable(UIUtils.getRadialGradient(i, j, Color.parseColor("#4c000000"), 0));
        mBrightnessControlVerticalSeekBar.setColor(Color.parseColor("#27ffffff"), getResources().getColor(R.color.brunch_mint));
    }
    public void initialise() {
        this.setMaxBrightness(MAX_BRIGHTNESS);
        this.mLayoutParam = ((Activity)mContext).getWindow().getAttributes();
        manuallyUpdate(20);
    }

    public void manuallyUpdate(int update) {
        setBrightness(update);
    }

    public int getMaxBrightness()
    {
        return mBrightnessControlVerticalSeekBar.getMax();
    }

    public int getMinBrightness()
    {
        return mBrightnessControlVerticalSeekBar.getMin();
    }

    public void setCurrentBrightness(int i)
    {
        int j;
        int k;
        k = getMaxBrightness();
        j = getMinBrightness();
        if(i >= j){
            j = i;
            if(i > k)
                j = k;
        }
        mBrightnessControlVerticalSeekBar.setProgress(j);
        String s = String.format("%d", j);
        mCenterBrightnessValueTextView.setText(s);
    }

    public void setMaxBrightness(int i)
    {
        mBrightnessControlVerticalSeekBar.setMax(i);
    }

    public void setMinBrightness(int i)
    {
        mBrightnessControlVerticalSeekBar.setMin(i);
    }

    public void setBrightness(int brightness) {
        if (brightness < MIN_BRIGHTNESS) {
            brightness = MIN_BRIGHTNESS;
        } else if (brightness > MAX_BRIGHTNESS) {
            brightness = MAX_BRIGHTNESS;
        }
        mBrightnessControlVerticalSeekBar.setProgress(brightness);
        String s = String.format("%d", brightness);
        mCenterBrightnessValueTextView.setText(s);
        this.mLayoutParam.screenBrightness = (float) brightness/100;
        ((Activity)mContext).getWindow().setAttributes(mLayoutParam);
    }
    public int getBrightness(){
        return mBrightnessControlVerticalSeekBar.getProgress();
    }

}
