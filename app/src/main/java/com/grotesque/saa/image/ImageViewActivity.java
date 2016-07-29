/**
 * ****************************************************************************
 * Copyright 2011, 2012 Chris Banes.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * *****************************************************************************
 */
package com.grotesque.saa.image;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.grotesque.saa.R;
import com.grotesque.saa.common.widget.subscaleview.SubSamplingScaleImageView;
import com.grotesque.saa.common.widget.zoomable.ZoomableDraweeView;

import static com.grotesque.saa.util.LogUtils.LOGE;


/**
 * Lock/Unlock button is added to the ActionBar.
 * Use it to temporarily disable ViewPager navigation in order to correctly interact with ImageView by gestures.
 * Lock/Unlock state of ViewPager is saved and restored on configuration changes.
 *
 * Julia Zudikova
 */

public class ImageViewActivity extends AppCompatActivity {
    private static final String TAG = ImageViewActivity.class.getSimpleName();
    private String mUri;
    private int mImageType;
    private LinearLayout mImageContainer;
    private RelativeLayout mNavigation;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        if (getIntent() != null) {
            mUri = getIntent().getStringExtra("image");
            mImageType = getIntent().getIntExtra("image_type", 1);
        }
        LOGE(TAG, "image uri : " + mUri);
        LOGE(TAG, "image type : " + mImageType);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return;
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        mImageContainer = (LinearLayout) findViewById(R.id.imageContainer);
        mNavigation = (RelativeLayout) findViewById(R.id.navigationLayout);

        switch (mImageType){
            case 0:
                SubSamplingScaleImageView subSamplingScaleImageView = new SubSamplingScaleImageView(this);
                subSamplingScaleImageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                subSamplingScaleImageView.setImageUri(mUri);
                mImageContainer.addView(subSamplingScaleImageView);
                break;
            case 1:
                ZoomableDraweeView zoomableDraweeView = new ZoomableDraweeView(this);
                zoomableDraweeView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                DraweeController zoomableController = Fresco.newDraweeControllerBuilder()
                        .setUri(mUri)
                        .setOldController(zoomableDraweeView.getController())
                        .build();
                zoomableDraweeView.setController(zoomableController);
                mImageContainer.addView(zoomableDraweeView);
                break;
            case 2:
                SimpleDraweeView simpleDraweeView = new SimpleDraweeView(this);
                simpleDraweeView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                        .setUri(mUri)
                        .setOldController(simpleDraweeView.getController())
                        .setAutoPlayAnimations(true)
                        .build();
                simpleDraweeView.setController(draweeController);
                mImageContainer.addView(simpleDraweeView);
                break;
        }
        final GestureDetector gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                mNavigation.setVisibility(mNavigation.getVisibility() != View.VISIBLE ? View.VISIBLE :View.GONE);
                return true;
            }
        });
        mImageContainer.getChildAt(0).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) { return gestureDetector.onTouchEvent(event); }
        });

        TextView imageUri = (TextView) findViewById(R.id.imageUri);
        imageUri.setText(mUri);
    }

}
