package com.grotesque.saa.board;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.grotesque.saa.util.DensityScaleUtil;
import com.grotesque.saa.util.LogUtils;

/**
 * Created by 경환 on 2016-04-19.
 */
public class StaggeredItemDecoration extends RecyclerView.ItemDecoration {
    private static final String TAG = LogUtils.makeLogTag(StaggeredItemDecoration.class);
    private Context mContext;
    private int b;
    private int c;

    public StaggeredItemDecoration(Context context) {
        super();
        mContext = context;
        b = DensityScaleUtil.dipToPixel(mContext, 20F);
        c = DensityScaleUtil.dipToPixel(mContext, 5F);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int j = outRect.bottom;

        outRect.set(c, c, c, j);

    }
}
