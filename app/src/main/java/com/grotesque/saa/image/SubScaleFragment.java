package com.grotesque.saa.image;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grotesque.saa.R;
import com.grotesque.saa.common.widget.subscaleview.SubSamplingScaleImageView;
import com.grotesque.saa.content.data.ContentItem;
import com.grotesque.saa.util.StringUtils;

import static com.grotesque.saa.util.LogUtils.LOGE;

/**
 * Created by 경환 on 2016-05-11.
 */
public class SubScaleFragment extends Fragment {

    private static final String TAG = SubScaleFragment.class.getSimpleName();
    private SubSamplingScaleImageView mImageView;
    private ContentItem mItem;
    private int mPosition;

    public static SubScaleFragment newInstance(ContentItem item, int position){
        SubScaleFragment fragment = new SubScaleFragment();
        Bundle args = new Bundle();
        args.putParcelable("image", item);
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if(args != null){
            mItem = args.getParcelable("image");
            mPosition = args.getInt("position");
        }
        LOGE(TAG, String.valueOf(mPosition));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mImageView = (SubSamplingScaleImageView) view.findViewById(R.id.subscale_image);

        String imgUrl = StringUtils.convertImgUrl(mItem.getImg());
        LOGE(TAG, imgUrl);
        mImageView.setImageUri(imgUrl);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_subscale, container, false);
    }
}
