package com.grotesque.saa.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.grotesque.saa.util.FontManager;


/**
 * Created by KH on 2015-11-04.
 */
public class TypefacedTextView extends TextView{
    public TypefacedTextView(Context context) {
        super(context);
        setTypeFace(context);
    }

    public TypefacedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeFace(context);
    }

    public TypefacedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeFace(context);
    }

    public TypefacedTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setTypeFace(context);
    }

    private void setTypeFace(Context context){
        setTypeface(FontManager.getInstance(context).getTypeface());
    }
}
