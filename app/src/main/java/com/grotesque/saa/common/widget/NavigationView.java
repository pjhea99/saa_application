package com.grotesque.saa.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.grotesque.saa.R;

/**
 * Created by 경환 on 2016-05-13.
 */
public class NavigationView extends LinearLayout implements View.OnClickListener {

    private Context mContext;
    private OnButtonClickListener mListener;

    private ImageButton mClose;
    private TextView mText;
    private FrameLayout mCalcio;
    private FrameLayout mFree;
    private FrameLayout mMedia;
    private FrameLayout mRest;
    private FrameLayout mGame;
    private FrameLayout mQnA;
    private FrameLayout mPlayer;
    private FrameLayout mReport;
    private FrameLayout mGag;

    public NavigationView(Context context) {
        super(context);
        this.mContext = context;
        initUI();
    }

    public NavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initUI();
    }

    public NavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initUI();
    }

    private void initUI(){
        LayoutInflater.from(mContext).inflate(R.layout.layout_board_navigation, this, true);

        mClose = (ImageButton) findViewById(R.id.imgbtn_select_board_dialog_close);
        mText = (TextView) findViewById(R.id.txt_share_title1);

        mCalcio = (FrameLayout) findViewById(R.id.flbtn_select_board_calcio);
        mFree = (FrameLayout) findViewById(R.id.flbtn_select_board_free);
        mMedia = (FrameLayout) findViewById(R.id.flbtn_select_board_media);
        mRest = (FrameLayout) findViewById(R.id.flbtn_select_board_rest);
        mGame = (FrameLayout) findViewById(R.id.flbtn_select_board_game);
        mQnA = (FrameLayout) findViewById(R.id.flbtn_select_board_qna);
        mPlayer = (FrameLayout) findViewById(R.id.flbtn_select_board_player1);
        mReport = (FrameLayout) findViewById(R.id.flbtn_select_board_specialreport);
        mGag = (FrameLayout) findViewById(R.id.flbtn_select_board_gag);

        mClose.setOnClickListener(this);
        mCalcio.setOnClickListener(this);
        mFree.setOnClickListener(this);
        mMedia.setOnClickListener(this);
        mRest.setOnClickListener(this);
        mGame.setOnClickListener(this);
        mQnA.setOnClickListener(this);
        mPlayer.setOnClickListener(this);
        mReport.setOnClickListener(this);
        mGag.setOnClickListener(this);
    }
    private void setSelectedAllFalse(){
        mCalcio.setSelected(false);
        mFree.setSelected(false);
        mMedia.setSelected(false);
        mRest.setSelected(false);
        mGame.setSelected(false);
        mQnA.setSelected(false);
        mPlayer.setSelected(false);
        mReport.setSelected(false);
        mGag.setSelected(false);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgbtn_select_board_dialog_close:
                if(mListener != null)
                    mListener.onClicked("close");
                this.setVisibility(GONE);
                break;
            case R.id.flbtn_select_board_calcio:
                if(mListener != null)
                    mListener.onClicked("calcioboard");
                setSelectedAllFalse();
                mCalcio.setSelected(true);
                this.setVisibility(GONE);
                break;
            case R.id.flbtn_select_board_free:
                if(mListener != null)
                    mListener.onClicked("freeboard3");
                setSelectedAllFalse();
                mFree.setSelected(true);
                this.setVisibility(GONE);
                break;
            case R.id.flbtn_select_board_media:
                if(mListener != null)
                    mListener.onClicked("multimedia1");
                setSelectedAllFalse();
                mMedia.setSelected(true);
                this.setVisibility(GONE);
                break;
            case R.id.flbtn_select_board_rest:
                if(mListener != null)
                    mListener.onClicked("rest");
                setSelectedAllFalse();
                mRest.setSelected(true);
                this.setVisibility(GONE);
                break;
            case R.id.flbtn_select_board_game:
                if(mListener != null)
                    mListener.onClicked("game");
                setSelectedAllFalse();
                mGame.setSelected(true);
                this.setVisibility(GONE);
                break;
            case R.id.flbtn_select_board_qna:
                if(mListener != null)
                    mListener.onClicked("qna1");
                setSelectedAllFalse();
                mQnA.setSelected(true);
                this.setVisibility(GONE);
                break;
            case R.id.flbtn_select_board_player1:
                if(mListener != null)
                    mListener.onClicked("players1");
                setSelectedAllFalse();
                mPlayer.setSelected(true);
                this.setVisibility(GONE);
                break;
            case R.id.flbtn_select_board_specialreport:
                if(mListener != null)
                    mListener.onClicked("specialreport");
                setSelectedAllFalse();
                mReport.setSelected(true);
                this.setVisibility(GONE);
                break;
            case R.id.flbtn_select_board_gag:
                if(mListener != null)
                    mListener.onClicked("gag");
                setSelectedAllFalse();
                mGag.setSelected(true);
                this.setVisibility(GONE);
                break;
        }
    }

    public void setText(String text){
        mText.setText(text);
    }
    public void setSelectedMid(String mid){
        switch (mid){
            case "calcioboard":
                mCalcio.setSelected(true);
                break;
            case "freeboard3":
                mFree.setSelected(true);
                break;
            case "multimedia1":
                mMedia.setSelected(true);
                break;
            case "rest":
                mRest.setSelected(true);
                break;
            case "game":
                mGame.setSelected(true);
                break;
            case "qna1":
                mQnA.setSelected(true);
                break;
            case "players1":
                mPlayer.setSelected(true);
                break;
            case "specialreport":
                mReport.setSelected(true);
                break;
            case "gag":
                mGag.setSelected(true);
                break;

        }
    }
    public void setOnButtonClickListener(OnButtonClickListener listener){
        this.mListener = listener;
    }

    public interface OnButtonClickListener{
        void onClicked(String mid);
    }
}
