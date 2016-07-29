package com.grotesque.saa.post.helper;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Listener for manual initiation of a drag.
 */
public interface OnPostAdapterListener {

    void onFocusChanged(View v, boolean hasFocus);
    /**
     * Called when a view is requesting a start of a drag.
     *
     * @param viewHolder The holder of the view to drag.
     */
    void onStartDrag(RecyclerView.ViewHolder viewHolder);

}