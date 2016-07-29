package com.grotesque.saa.common.widget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.grotesque.saa.R;
import com.grotesque.saa.util.UIUtils;

import static com.grotesque.saa.util.LogUtils.LOGE;


public class ControllerVolumeAreaView extends LinearLayout{
    private AudioManager audioManager;
    private TextView mCenterSoundValueTextView;
    private Context mContext;
    private VerticalSeekBar mSoundControlVerticalSeekBar;
    private ImageView mValueBg;
    private ImageView mVolumeImage;


    private BroadcastReceiver volumeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateVolumeProgress();
        }
    };

    public ControllerVolumeAreaView(Context context)
    {
        super(context);
        init(context);
    }

    public ControllerVolumeAreaView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        init(context);
    }

    private void init(Context context)
    {
        mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.controller_volumn_area, this, true);
        mValueBg = (ImageView)findViewById(R.id.Controller_Center_SoundValue_BG);
        mCenterSoundValueTextView = (TextView)findViewById(R.id.Controller_Center_SoundValue);
        mSoundControlVerticalSeekBar = (VerticalSeekBar)findViewById(R.id.Controller_Sound_Seekbar);
        mVolumeImage = (ImageView)findViewById(R.id.Controller_Center_SoundIcon);
        int i = getResources().getDimensionPixelSize(R.dimen.player_img_shadow03_width);
        int j = getResources().getDimensionPixelSize(R.dimen.player_img_shadow03_height);
        mValueBg.setImageDrawable(UIUtils.getRadialGradient(i, j, Color.parseColor("#4c000000"), 0));
        mSoundControlVerticalSeekBar.setColor(Color.parseColor("#27ffffff"), getResources().getColor(R.color.brunch_mint));
    }

    public void initialise() {
        this.audioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
        this.setMaxVolumn(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        LOGE("MAX VOLUME", "-" + audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        this.setCurrentVolume(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
    }

    @Override
    public void onInitializeAccessibilityEvent(final AccessibilityEvent event) {
        super.onInitializeAccessibilityEvent(event);
        event.setClassName(ControllerVolumeAreaView.class.getName());
    }

    @Override
    public void onInitializeAccessibilityNodeInfo(final AccessibilityNodeInfo info) {
        super.onInitializeAccessibilityNodeInfo(info);
        info.setClassName(ControllerVolumeAreaView.class.getName());
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        registerVolumeReceiver();
    }

    @Override
    protected void onDetachedFromWindow() {
        unregisterVolumeReceiver();
        super.onDetachedFromWindow();
    }

    private void updateVolumeProgress() {
        this.setCurrentVolume(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
    }

    private void registerVolumeReceiver() {
        getContext().registerReceiver(volumeReceiver, new IntentFilter("android.media.VOLUME_CHANGED_ACTION"));
    }

    private void unregisterVolumeReceiver() {
        getContext().unregisterReceiver(volumeReceiver);
    }

    public void manuallyUpdate(int update) {
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, update, 0);
    }

    public int getCurrentVolumn()
    {
        return mSoundControlVerticalSeekBar.getProgress();
    }

    public int getMaxVolumn()
    {
        return mSoundControlVerticalSeekBar.getMax();
    }

    public int getMinVolumn()
    {
        return mSoundControlVerticalSeekBar.getMin();
    }

    public void setCurrentVolume(int i)
    {
        mSoundControlVerticalSeekBar.setProgress(i);
        String s = String.format("%d", i);
        mCenterSoundValueTextView.setText(s);
        if(i <= 0)
        {
            mVolumeImage.setImageResource(R.drawable.player_img_volume_mute);
            return;
        } else
        {
            mVolumeImage.setImageResource(R.drawable.player_img_volume_white);
            return;
        }

    }

    public void setMaxVolumn(int i)
    {
        mSoundControlVerticalSeekBar.setMax(i);
    }

    public void setMinVolumn(int i)
    {
        mSoundControlVerticalSeekBar.setMin(i);
    }


}