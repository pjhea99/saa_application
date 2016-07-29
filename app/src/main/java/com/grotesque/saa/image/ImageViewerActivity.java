package com.grotesque.saa.image;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.DownloadManager;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.grotesque.saa.R;
import com.grotesque.saa.common.widget.CustomAlertDialog;
import com.grotesque.saa.common.widget.CustomPageTransformer;
import com.grotesque.saa.common.widget.SlowViewPager;
import com.grotesque.saa.content.data.ContentItem;
import com.grotesque.saa.util.UrlRouter;

import java.util.ArrayList;

import static com.grotesque.saa.util.LogUtils.LOGE;
import static com.grotesque.saa.util.LogUtils.makeLogTag;

/**
 * Created by 경환 on 2016-04-10.
 */
public class ImageViewerActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener, ZoomableFragment.Listener {
    private final String TAG = makeLogTag(ImageViewerActivity.class);
    private final int MY_PERMISSION_REQUEST_STORAGE = 100;

    private int mCurrentPosition = 0;
    private boolean mLast = false;

    private SlowViewPager mViewPager;
    private LinearLayout mNavigationLayout;
    private TextView mImageSrcView;
    private TextView mImagePositionView;
    private ImageButton mCloseBtn;
    private ImageButton mPrevBtn;
    private ImageButton mNextBtn;

    private PagerAdapter mAdapter;

    private ArrayList<ContentItem> mArrayList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageviewer);
        Bundle args = getIntent().getExtras();

        if(args != null){
            mArrayList = args.getParcelableArrayList("array");
            mCurrentPosition = args.getInt("position");
        }
        registerView();
    }
    private void registerView(){
        mViewPager = (SlowViewPager) findViewById(R.id.image_viewpager);
        mNavigationLayout = (LinearLayout) findViewById(R.id.imageviewer_navigation_layout);
        mCloseBtn = (ImageButton) findViewById(R.id.imageviewer_navigation_close_button);
        mPrevBtn = (ImageButton) findViewById(R.id.imageviewer_navigation_pre_button);
        mNextBtn = (ImageButton) findViewById(R.id.imageviewer_navigation_next_button);
        mImageSrcView = (TextView) findViewById(R.id.image_attachment_reference_text_view);
        mImagePositionView = (TextView) findViewById(R.id.imageviewer_position_text_view);


        mAdapter = new PagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(this);
        mViewPager.setOffscreenPageLimit(5);
        CustomPageTransformer pageTransformer = new CustomPageTransformer(mViewPager);
        pageTransformer.setPageOffset(getResources().getDimensionPixelSize(R.dimen.image_viewer_offset_size));
        mViewPager.setPageTransformer(true, pageTransformer);
        mViewPager.setCurrentItem(mCurrentPosition);

        mCloseBtn.setOnClickListener(this);
        mPrevBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Glide.get(this).clearMemory();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageviewer_navigation_close_button:
                finishActivity();
                break;
            case R.id.imageviewer_navigation_pre_button:
                int i = mViewPager.getCurrentItem() - 1;
                if (i < 0) {
                    finish();
                    return;
                }
                mViewPager.setCurrentItem(i);
                break;
            case R.id.imageviewer_navigation_next_button:
                i = mViewPager.getCurrentItem() + 1;
                if (i >= mArrayList.size()) {
                    finish();
                    return;
                }
                mViewPager.setCurrentItem(i);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if ((position == mAdapter.getCount() - 1) && (mLast) && (positionOffset == 0.0F) && (positionOffsetPixels == 0)) {
            finishActivity();
        }
    }

    @Override
    public void onPageSelected(int position) {
        mCurrentPosition = position;
        if (position != mAdapter.getCount() - 1) {
            mLast = false;
        }
        setImageSrcText(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if ((state == 0) && (mCurrentPosition == mAdapter.getCount() - 1)) {
            mLast = true;
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if ((hasFocus) && mCurrentPosition >= 0 && (mCurrentPosition < mArrayList.size())) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mViewPager.setScrollDurationFactor(2.0D);
                    mViewPager.setCurrentItem(mCurrentPosition, true);
                    mViewPager.setScrollDurationFactor(1.0D);
                    setImageSrcText(mCurrentPosition);
                }
            }, 250L);
        }
    }
    public void downloadImageFile(){
        String imgURl = mArrayList.get(mCurrentPosition).getImg();
        String imgName = UrlRouter.getNameFromUrl(mArrayList.get(mCurrentPosition).getImg());
        //path to save file after downloaded
        Uri uri = Uri.parse(imgURl);
        DownloadManager.Request req = new DownloadManager.Request(uri);
        req.setTitle(imgName);
        req.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, imgName);

        //set notification can be seen
        req.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

        //show file in android gallery after downloaded
        req.allowScanningByMediaScanner();
        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);

        //start to download
        downloadManager.enqueue(req);
    }
    public void setImageSrcText(int position){
        mImageSrcView.setText(mArrayList.get(position).getImg());
        mImageSrcView.setVisibility(View.VISIBLE);
        mImagePositionView.setText(position + 1 + " / " + mAdapter.getCount());
    }

    public void finishActivity(){
        finish();
    }
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.aviary_slide_out_left);
    }

    @Override
    public void onLongClicked() {
        ArrayList arraylist = new ArrayList();
        arraylist.add("저장");
        CharSequence acharsequence[] = (CharSequence[])arraylist.toArray(new CharSequence[arraylist.size()]);
        (new CustomAlertDialog(this)).setItems(acharsequence , onClickListener).show();
    }

    @Override
    public void onSingleTouch() {
        if (mNavigationLayout.getVisibility() == View.VISIBLE) {
            AlphaAnimation localAlphaAnimation = new AlphaAnimation(1.0F, 0.0F);
            localAlphaAnimation.setDuration(250L);
            localAlphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    mNavigationLayout.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationEnd(Animation animation) {

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            mNavigationLayout.startAnimation(localAlphaAnimation);
            return;
        }
        mNavigationLayout.setVisibility(View.VISIBLE);
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermission() {
        LOGE(TAG, "CheckPermission : " + checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE));
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Explain to the user why we need to write the permission.
                Toast.makeText(this, "Read/Write external storage", Toast.LENGTH_SHORT).show();
            }

            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSION_REQUEST_STORAGE);

            // MY_PERMISSION_REQUEST_STORAGE is an
            // app-defined int constant

        } else {
            LOGE(TAG, "permission deny");
            downloadImageFile();
        }
    }

    private final DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                checkPermission();
            }else{
                downloadImageFile();
            }

        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_REQUEST_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                    downloadImageFile();

                    // permission was granted, yay! do the
                    // calendar task you need to do.

                } else {

                    LOGE(TAG, "Permission always deny");

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                break;
        }
    }

    class PagerAdapter extends FragmentPagerAdapter {
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }
        public int getCount() {
            return mArrayList.size();
        }
        public Fragment getItem(int position) {
            //ZoomableFragment fragment = ZoomableFragment.newInstance(mArrayList.get(position), position);
            //.setDropShadow(0, 0, getResources().getDimensionPixelSize(R.dimen.image_viewer_shadow_size), 0);
            return SubScaleFragment.newInstance(mArrayList.get(position), position);
        }
    }
}
