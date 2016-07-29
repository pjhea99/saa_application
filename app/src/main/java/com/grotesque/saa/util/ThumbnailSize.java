// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.grotesque.saa.util;

import android.net.Uri;


public final class ThumbnailSize {

    private ThumbnailSize(String s, int i, String s1, String s2, int j, int k) {

        suffix = s1;
        queryParam = s2;
        width = j;
        height = k;
    }

    public static ThumbnailSize fromId(String s)
    {
        int i = s.length();
        if (i == 6 || i == 8) {
            switch (s.charAt(i-1)){
                case 's':
                    return SMALL_SQUARE;
                case 'b':
                    return BIG_SQUARE;
                case 't':
                    return SMALL_THUMBNAIL;
                case 'm':
                    return MEDIUM_THUMBNAIL;
                case 'l':
                    return LARGE_THUMBNAIL;
                case 'h':
                    return HUGE_THUMBNAIL;
            }
        }
        return null;
    }

    public static ThumbnailSize fromUri(Uri uri) {
        if(UrlRouter.isDirectImageLink(uri)) {
            String str = uri.getLastPathSegment();
            return fromId(str.substring(0, str.indexOf('.')));
        } else {
            return null;
        }
    }

    public static ThumbnailSize[] values()
    {
        return VALUES.clone();
    }

    public int getHeight()
    {
        return height;
    }

    public int getPixelCount()
    {
        return getWidth() * getHeight();
    }

    public String getQueryParam()
    {
        return queryParam;
    }

    public String getSuffix()
    {
        return suffix;
    }

    public int getWidth()
    {
        return width;
    }

    private int height;
    private String queryParam;
    private String suffix;
    private int width;



    private static final ThumbnailSize SMALL_SQUARE = new ThumbnailSize("SMALL_SQUARE", 0, "s", null, 90, 90);
    private static final ThumbnailSize BIG_SQUARE = new ThumbnailSize("BIG_SQUARE", 1, "b", null, 160, 160);
    private static final ThumbnailSize SMALL_THUMBNAIL = new ThumbnailSize("SMALL_THUMBNAIL", 2, "t", null, 160, 160);
    private static final ThumbnailSize MEDIUM_THUMBNAIL = new ThumbnailSize("MEDIUM_THUMBNAIL", 3, "m", null, 320, 320);
    private static final ThumbnailSize LARGE_THUMBNAIL = new ThumbnailSize("LARGE_THUMBNAIL", 4, "l", null, 640, 640);
    private static final ThumbnailSize HUGE_THUMBNAIL = new ThumbnailSize("HUGE_THUMBNAIL", 5, "h", null, 1024, 1024);
    private static final ThumbnailSize TALL_MEDIUM_THUMBNAIL = new ThumbnailSize("TALL_MEDIUM_THUMBNAIL", 6, "", "t320", 320, 720);
    private static final ThumbnailSize TALL_LARGE_THUMBNAIL = new ThumbnailSize("TALL_LARGE_THUMBNAIL", 7, "", "t512", 512, 1152);
    private static final ThumbnailSize[] VALUES = (new ThumbnailSize[] {
            SMALL_SQUARE, BIG_SQUARE, SMALL_THUMBNAIL, MEDIUM_THUMBNAIL, LARGE_THUMBNAIL, HUGE_THUMBNAIL, TALL_MEDIUM_THUMBNAIL, TALL_LARGE_THUMBNAIL
        });

}
