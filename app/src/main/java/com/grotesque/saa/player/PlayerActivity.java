package com.grotesque.saa.player;

import android.annotation.TargetApi;
import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.grotesque.saa.R;
import com.grotesque.saa.common.widget.ControllerBrightnessAreaView;
import com.grotesque.saa.common.widget.ControllerView;
import com.grotesque.saa.common.widget.ControllerVolumeAreaView;

import static com.grotesque.saa.util.LogUtils.LOGE;

/**
 * Created by 경환 on 2016-04-23.
 */
public class PlayerActivity extends Activity implements MediaPlayer.OnCompletionListener, View.OnSystemUiVisibilityChangeListener, ControllerView.Listener {
    private static final String TAG = PlayerActivity.class.getSimpleName();
    private static final int INITIAL_HIDE_DELAY = 300;
    private static final int INITIAL_HIDE_DELAY2 = 2000;
    private static final int GESTURE_IDLE = 0;
    private static final int GESTURE_VOLUME = 2;
    private static final int GESTURE_BRIGHTNESS = 3;

    private int mPrevBrightnessDelta = 0;
    private int mPrevVolumeDelta = 0;
    private int mGestureState = GESTURE_IDLE;

    private int BRIGHTNESS_SENSITIVITY;
    private int BRIGHTNESS_SENSITIVITY_START;
    private int VOLUME_SENSITIVITY;
    private int VOLUME_SENSITIVITY_START;

    private String mUrl = "";
    private ControllerView mControllerView;
    private ControllerBrightnessAreaView mBrightnessAreaView;
    private ControllerVolumeAreaView mVolumeAreaView;
    private VideoView mVideoView;
    private View mDecorView;
    private Handler updateHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        Bundle args = getIntent().getExtras();
        mUrl = args.getString("url");
        LOGE(TAG, mUrl);
        VOLUME_SENSITIVITY = (int)getResources().getDimension(R.dimen.player_gesture_volume);
        VOLUME_SENSITIVITY_START = (int)getResources().getDimension(R.dimen.player_gesture_volume_start);
        BRIGHTNESS_SENSITIVITY = (int)getResources().getDimension(R.dimen.player_gesture_brightness);
        BRIGHTNESS_SENSITIVITY_START = (int)getResources().getDimension(R.dimen.player_gesture_brightness_start);


        mDecorView = getWindow().getDecorView();
        mDecorView.setOnSystemUiVisibilityChangeListener(this);

        mVideoView = (VideoView) findViewById(R.id.videoView);

        mBrightnessAreaView = (ControllerBrightnessAreaView) findViewById(R.id.brightnessSeekBar);
        mBrightnessAreaView.initialise();
        mVolumeAreaView = (ControllerVolumeAreaView) findViewById(R.id.volumeSeekBar);
        mVolumeAreaView.initialise();

        final GestureDetector clickDetector = new GestureDetector(this,
                new GestureDetector.SimpleOnGestureListener() {

                    @Override
                    public boolean onSingleTapUp(MotionEvent e) {
                        boolean visible = (mDecorView.getSystemUiVisibility()
                                & View.SYSTEM_UI_FLAG_HIDE_NAVIGATION) == 0;
                        if (visible) {
                            hideSystemUI();
                        } else {
                            showSystemUI();
                            delayedHide(INITIAL_HIDE_DELAY2);
                        }
                        return true;
                    }

                    @Override
                    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                        if(checkGestureBrightnessControlCondition(e2.getY(), e1.getY()) || checkGestureVolumeControlCondition(e2.getY(), e1.getY())){
                            if (e1.getX() > mControllerView.getWidth() / 2) {
                                mVolumeAreaView.setVisibility(View.VISIBLE);
                                int currentVolumeDelta = checkMoveDistance(e2.getY(), e1.getY(), VOLUME_SENSITIVITY);
                                int volume = mVolumeAreaView.getCurrentVolumn() + (currentVolumeDelta - mPrevVolumeDelta);

                                mVolumeAreaView.manuallyUpdate(volume);
                                mPrevVolumeDelta = currentVolumeDelta;
                                mGestureState = GESTURE_VOLUME;
                            }else{
                                int k = BRIGHTNESS_SENSITIVITY / 4;
                                if (k == 0) {
                                    k = 1;
                                }
                                mBrightnessAreaView.setVisibility(View.VISIBLE);
                                int currentBrightnessDelta = checkMoveDistance(e2.getY(), e1.getY(), k);
                                int brightness = mBrightnessAreaView.getBrightness() + (currentBrightnessDelta - mPrevBrightnessDelta);

                                mBrightnessAreaView.manuallyUpdate(brightness);
                                mPrevBrightnessDelta = currentBrightnessDelta;
                                mGestureState = GESTURE_BRIGHTNESS;
                            }
                        }
                        return true;
                    }
                });


        mControllerView = (ControllerView) findViewById(R.id.controllerView);
        mControllerView.setOnListener(this);
        mControllerView.setClickable(true);
        mControllerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    mBrightnessAreaView.setVisibility(View.GONE);
                    mVolumeAreaView.setVisibility(View.GONE);
                    mPrevBrightnessDelta = 0;
                    mPrevVolumeDelta = 0;
                    mGestureState = GESTURE_IDLE;
                }
                return clickDetector.onTouchEvent(event);
            }
        });


        loadVideo(mVideoView);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void loadVideo(View view) {
        Uri uri = Uri.parse(mUrl);
        LOGE(TAG, "uri : " + uri);

        mVideoView.setVideoURI(uri);
        mVideoView.requestFocus();

        mVideoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra){
                switch (what) {
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                        mControllerView.setPlayPauseSelected(false);
                        break;
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                        mVideoView.start();
                        mControllerView.setPlayPauseSelected(true);
                        break;
                }
                return false;
            }
        });

        // 플레이 준비가 되면, seekBar와 PlayTime을 세팅하고 플레이를 한다.
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                    @Override
                    public void onBufferingUpdate(MediaPlayer mp, int percent) {

                        int max = mControllerView.getSeekBarMax();
                        int current = mControllerView.getSeekBarProgress();
                        if(percent > 95){
                            mControllerView.setSeekBarSecondaryProgress(max);
                        }else {
                            mControllerView.setSeekBarSecondaryProgress(current + (max * percent / 100));
                        }

                    }
                });
                mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                        new MediaPlayer.OnVideoSizeChangedListener() {
                            @Override
                            public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                                        ViewGroup.LayoutParams.FILL_PARENT);
                                mVideoView.setLayoutParams(lp);
                            }
                        };
                    }
                });
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                        ViewGroup.LayoutParams.FILL_PARENT);


                mVideoView.setLayoutParams(lp);
                playPause();
                long finalTime = mVideoView.getDuration();
                mControllerView.setSeekBarMax((int) finalTime);
                mControllerView.setSeekBarProgress(0);
                updateHandler.postDelayed(updateVideoTime, 100);
            }
        });
    }


    public void playVideo(){
        mVideoView.requestFocus();
        mVideoView.start();

    }

    public void pauseVideo(){
        mVideoView.pause();
    }

    // seekBar를 이동시키기 위한 쓰레드 객체
    // 100ms 마다 viewView의 플레이 상태를 체크하여, seekBar를 업데이트 한다.
    private Runnable updateVideoTime = new Runnable(){
        public void run(){
            long currentPosition = mVideoView.getCurrentPosition();
            mControllerView.setSeekBarProgress((int) currentPosition);
            updateHandler.postDelayed(this, 100);

        }
    };
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        // When the window loses focus (e.g. the action overflow is shown),
        // cancel any pending hide action. When the window gains focus,
        // hide the system UI.
        if (hasFocus) {
            delayedHide(INITIAL_HIDE_DELAY);
        } else {
            mHideHandler.removeMessages(0);
        }
    }

    private void hideSystemUI() {
        mDecorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    private void showSystemUI() {
        mDecorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    private final Handler mHideHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
                hideSystemUI();
        }
    };

    private void delayedHide(int delayMillis) {
        mHideHandler.removeMessages(0);
        mHideHandler.sendEmptyMessageDelayed(0, delayMillis);
    }


    private boolean checkGestureBrightnessControlCondition(float f, float f1)
    {
        return ((checkGestureStart(f1, f, BRIGHTNESS_SENSITIVITY_START) && mGestureState == GESTURE_IDLE) || mGestureState == GESTURE_BRIGHTNESS);
    }
    private boolean checkGestureVolumeControlCondition(float f, float f1)
    {
        return ((checkGestureStart(f1, f, VOLUME_SENSITIVITY_START) && mGestureState == GESTURE_IDLE
        ) || mGestureState == GESTURE_VOLUME);
    }

    private boolean checkGestureStart(float f, float f1, int sensi)
    {
        return (int)((f1 - f) / (float)sensi) != 0;
    }

    private int checkMoveDistance(float paramFloat1, float paramFloat2, int sensi)
    {
        float distance = (paramFloat2 - paramFloat1) / sensi;

        if (distance < 0.0F) {
            distance = distance - 1.0F;
        }
        return (int)distance;
    }

    @Override
    public void seekTo(int progress) {
        mVideoView.seekTo(progress);
    }

    @Override
    public void playPause() {
        if (mControllerView.isPlayPauseSelected()) {
            pauseVideo();
            mControllerView.setPlayPauseSelected(false);
        } else {
            playVideo();
            mControllerView.setPlayPauseSelected(true);
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

        mVideoView.pause();
        mVideoView.seekTo(0);
        mControllerView.setSeekBarProgress(0);
        mControllerView.setPlayPauseSelected(false);
        finish();
    }


    MediaPlayer.OnInfoListener infoListener = new MediaPlayer.OnInfoListener() {
        @Override
        public boolean onInfo(MediaPlayer mp, int what, int extra){
            switch (what) {
                case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                    mControllerView.setPlayPauseSelected(false);
                    break;
                case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                    mVideoView.start();
                    mControllerView.setPlayPauseSelected(true);
                    break;
            }
            return false;
        }
    };
    MediaPlayer.OnPreparedListener preparedListener = new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mp) {
            mp.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                @Override
                public void onBufferingUpdate(MediaPlayer mp, int percent) {

                    int max = mControllerView.getSeekBarMax();
                    int current = mControllerView.getSeekBarProgress();
                    if(percent > 95){
                        mControllerView.setSeekBarSecondaryProgress(max);
                    }else {
                        mControllerView.setSeekBarSecondaryProgress(current + (max * percent / 100));
                    }

                }
            });
            mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                @Override
                public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                    new MediaPlayer.OnVideoSizeChangedListener() {
                        @Override
                        public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                                    ViewGroup.LayoutParams.FILL_PARENT);
                            mVideoView.setLayoutParams(lp);
                        }
                    };
                }
            });
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.FILL_PARENT);


            mVideoView.setLayoutParams(lp);
            mVideoView.start();
            long finalTime = mVideoView.getDuration();
            mControllerView.setSeekBarMax((int) finalTime);
            mControllerView.setSeekBarProgress(0);
            updateHandler.postDelayed(updateVideoTime, 100);
        }
    };

    @Override
    public void onSystemUiVisibilityChange(int visibility) {
        boolean visible = (visibility & View.SYSTEM_UI_FLAG_HIDE_NAVIGATION) == 0;
        mControllerView.setVisibility(visible);
    }
}
