package com.grotesque.saa.util;


import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

public class FontManager
{
    private static Typeface mTypeFaceSAA = null;
    private static Typeface mTypeFaceSAAItalic = null;
    private static Context c;
    protected static FontManager manager = null;

    protected FontManager(){}

    public static FontManager getInstance(Context context){
        if(manager == null) {
            manager = new FontManager();
            c = context.getApplicationContext();
        }

        if (mTypeFaceSAA == null)
            mTypeFaceSAA = Typeface.createFromAsset(c.getAssets(), "NanumBarunGothicUltraLight.otf");
        if (mTypeFaceSAAItalic == null)
            mTypeFaceSAAItalic = Typeface.createFromAsset(c.getAssets(), "Georgia_Italic.ttf");

        return manager;
    }

    public Typeface getTypeface(){
        return mTypeFaceSAA;
    }

    public Typeface getTypefaceItalic(){
        return mTypeFaceSAAItalic;
    }

    public void purgeInstance(){
        manager = null;
        mTypeFaceSAA = null;
        mTypeFaceSAAItalic = null;
    }

    public void setTypeface(TextView textview){
        if(mTypeFaceSAA != null)
            textview.setTypeface(mTypeFaceSAA);
    }

    public void setTypefaceItalic(TextView textview){
        if(mTypeFaceSAAItalic != null)
            textview.setTypeface(mTypeFaceSAAItalic);
    }
}
