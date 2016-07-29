package com.grotesque.saa.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.grotesque.saa.R;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static com.grotesque.saa.util.LogUtils.makeLogTag;


/**
 * Created by KH on 2015-11-17.
 */
public class ControllerView extends RelativeLayout implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {
    private static final String TAG = makeLogTag(ControllerView.class);
    private static final int ACTIVITY_LIVE = 0;
    private static final int ACTIVITY_VOD = 1;
    private boolean mFull = false;
    private boolean mHour = false;
    private boolean mLock = false;
    private int mType = 0;
    private Listener mListener;

    private Context mContext;
    private RelativeLayout mRootView;
    private RelativeLayout mCenterField;
    private LinearLayout mBottomField;

    private SeekBar mSeekBar;
    private ImageButton mPlayButton;
    private View mBaseBG;

    private View mVodFull;
    public ControllerView(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public ControllerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    private void init(){
        LayoutInflater.from(mContext).inflate(R.layout.view_activity_vod_controller, this, true);

        mRootView = (RelativeLayout) findViewById(R.id.ActivityVodController_Main);
        mBaseBG = findViewById(R.id.ActivityVodController_BaseBG);
        mSeekBar = (SeekBar) findViewById(R.id.ActivityVodController_BottomField_Seekbar);
        mBottomField = (LinearLayout) findViewById(R.id.ActivityVodController_BottomField);
        mCenterField = (RelativeLayout) findViewById(R.id.ActivityVodController_CenterField);
        mPlayButton = (ImageButton) findViewById(R.id.ActivityVodController_CenterField_PlayButton);


        mSeekBar.setOnSeekBarChangeListener(this);
        mPlayButton.setOnClickListener(this);
    }
    public boolean isPlayPauseSelected() {
        return mPlayButton.isSelected();
    }

    public int getSeekBarMax() {
        return mSeekBar.getMax();
    }

    public int getSeekBarProgress() {
        return mSeekBar.getProgress();
    }

    public void setVisibility(boolean visibility){
        mBaseBG.setVisibility(visibility ? VISIBLE : GONE);
        mCenterField.setVisibility(visibility ? VISIBLE : GONE);
        mBottomField.setVisibility(visibility ? VISIBLE : GONE);
    }
    public void setOnListener(Listener listener){
        mListener = listener;
    }
    public void setPlayPauseSelected(boolean selected){
            mPlayButton.setSelected(selected);
    }
    public void setSeekBarMax(int max){
        TextView tvTotalTime = (TextView) findViewById(R.id.ActivityVodController_BottomField_Duration_TextView);

        if(TimeUnit.MILLISECONDS.toHours((long) max) > 0){
            mHour = true;
            long hour =  TimeUnit.MILLISECONDS.toHours((long) max);
            long min = TimeUnit.MILLISECONDS.toMinutes((long) max) - TimeUnit.HOURS.toMinutes(hour);
            long sec = TimeUnit.MILLISECONDS.toSeconds((long) max) - TimeUnit.HOURS.toSeconds(hour) - TimeUnit.MINUTES.toSeconds(min);
            tvTotalTime.setText(String.format(Locale.US, "%02d:%02d:%02d", hour, min, sec));
        }else {
            mHour = false;
            tvTotalTime.setText(String.format(Locale.US, "%02d:%02d",
                            TimeUnit.MILLISECONDS.toMinutes((long) max),
                            TimeUnit.MILLISECONDS.toSeconds((long) max) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) max)))
            );
        }
        mSeekBar.setMax(max);
    }
    public void setSeekBarSecondaryProgress(int progress){
        mSeekBar.setSecondaryProgress(progress);
    }
    public void setSeekBarProgress(int progress){
        mSeekBar.setProgress(progress);
    }
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        TextView tvCurrentTime = (TextView) findViewById(R.id.ActivityVodController_BottomField_CurrentPosition_TextView);
        if(mHour){
            long hour =  TimeUnit.MILLISECONDS.toHours((long) progress);
            long min = TimeUnit.MILLISECONDS.toMinutes((long) progress) - TimeUnit.HOURS.toMinutes(hour);
            long sec = TimeUnit.MILLISECONDS.toSeconds((long) progress) - TimeUnit.HOURS.toSeconds(hour) - TimeUnit.MINUTES.toSeconds(min);
            tvCurrentTime.setText(String.format(Locale.US, "%02d:%02d:%02d", hour, min, sec));
        }else{
            tvCurrentTime.setText(String.format(Locale.US, "%02d:%02d",
                            TimeUnit.MILLISECONDS.toMinutes((long) progress),
                            TimeUnit.MILLISECONDS.toSeconds((long) progress) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) progress)))
            );
        }
        if(fromUser){
           mListener.seekTo(progress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ActivityVodController_CenterField_PlayButton:
                mListener.playPause();
                break;
        }
    }



    public interface Listener{
        void seekTo(int progress);
        void playPause();
    }

}
