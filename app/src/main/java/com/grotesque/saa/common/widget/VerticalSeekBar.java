package com.grotesque.saa.common.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.grotesque.saa.R;


public class VerticalSeekBar extends View
{

    public VerticalSeekBar(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        max = 100;
        min = 0;
        progress = 50;
        init(attributeset);
    }

    private void updateGradientBitmap(int i, int j)
    {
        mPaintFG.setShader(new LinearGradient(0.0F, j, 0.0F, 0.0F, getResources().getColor(R.color.seek_bar_fg_start), getResources().getColor(R.color.seek_bar_fg_end), Shader.TileMode.CLAMP));
        mGradient = Bitmap.createBitmap(i, j, Bitmap.Config.ARGB_8888);
        (new Canvas(mGradient)).drawRect(0.0F, 0.0F, i, j, mPaintFG);
    }

    public int getMax()
    {
        return max;
    }

    public int getMin()
    {
        return min;
    }

    public int getProgress()
    {
        return progress;
    }

    public void init(AttributeSet attributeset)
    {
        max = attributeset.getAttributeIntValue("android", "max", 100);
        progress = attributeset.getAttributeIntValue("android", "progress", 0);
        mPaintBG = new Paint();
        mPaintBG.setStyle(Paint.Style.FILL);
        mPaintBG.setColor(getResources().getColor(R.color.seek_bar_bg));
        mPaintFG = new Paint();
        mPaintFG.setStyle(Paint.Style.FILL);
        mPaintFG.setColor(getResources().getColor(R.color.seek_bar_fg_start));
        mPaintThumb = new Paint();
        mPaintThumb.setStyle(Paint.Style.FILL);
        mPaintThumb.setColor(getResources().getColor(R.color.seek_bar_thumb));
    }

    protected void onDraw(Canvas canvas)
    {
        float f = getHeight();
        int i = getWidth();
        canvas.drawRect(new Rect(0, 0, i, (int)f), mPaintBG);
        canvas.drawRect(new Rect(0, (int)(((float)(max - progress) * f) / (float)max), i, (int)f), mPaintFG);
        super.onDraw(canvas);
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
        super.onSizeChanged(i, j, k, l);
    }

    public void setColor(int i, int j)
    {
        mPaintBG.setColor(i);
        mPaintFG.setColor(j);
    }

    public void setMax(int i)
    {
        max = i;
    }

    public void setMin(int i)
    {
        min = i;
    }

    public void setProgress(int i)
    {
        if(progress != i)
        {
            progress = i;
            postInvalidate();
        }
    }

    private Bitmap mGradient;
    private Paint mPaintBG;
    private Paint mPaintFG;
    private Paint mPaintThumb;
    private int max;
    private int min;
    private int progress;
}
