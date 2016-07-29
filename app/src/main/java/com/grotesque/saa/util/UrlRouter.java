package com.grotesque.saa.util;

import android.net.Uri;
import android.text.TextUtils;

import java.util.Locale;

/**
 * Created by 경환 on 2016-04-15.
 */
public class UrlRouter {
    public static String getIdFromThumbnail(String s)
    {
        String s1 = s;
        if(ThumbnailSize.fromId(s) != null)
        {
            s1 = s;
            if(!TextUtils.isEmpty(s))
                s1 = s.substring(0, s.length() - 1);
        }
        return s1;
    }

    public static String getIdFromUrl(Uri uri)
    {
        String str = "";
        if(uri.getPathSegments().size() >= 3)
        {
            if("topic".equalsIgnoreCase(uri.getPathSegments().get(0)))
                str = uri.getPathSegments().get(2);
            else
            if("r".equalsIgnoreCase(uri.getPathSegments().get(0)))
                str = uri.getPathSegments().get(2);
            else
                str = uri.getPathSegments().get(1);
        } else
        if(uri.getPathSegments().size() == 2)
            str = uri.getPathSegments().get(1);
        else
        if(uri.getPathSegments().size() == 1)
            str = uri.getPathSegments().get(0);
        else
            uri = null;
        if(uri != null)
            return getIdFromThumbnail(str.replaceAll("\\..*", ""));
        else
            return null;
    }

    public static String getIdFromUrl(String s)
    {
        String s1;
        try
        {
            s1 = getIdFromUrl(Uri.parse(s));
        }
        catch(Exception exception) {
            return null;
        }
        return s1;
    }
    public static String getNameFromUrl(Uri uri){
        if(uri.getPathSegments().size() > 0){
            return uri.getLastPathSegment();
        }
        return null;
    }
    public static String getNameFromUrl(String s){
        String s1;
        try
        {
            s1 = getNameFromUrl(Uri.parse(s));
        }
        catch(Exception exception) {
            return null;
        }
        return s1;
    }
    public static boolean isDirectImageLink(Uri uri)
    {
        String s;
        if(uri != null)
            if(!TextUtils.isEmpty(s = uri.getLastPathSegment()) && (isImageHost(uri) || isLinkToImage(s)))
                return true;
        return false;
    }

    private static boolean isImageHost(Uri uri)
    {
        return "i.imgur.com".equals(uri.getAuthority()) && !(uri.getPathSegments() == null) || (uri.getPathSegments().isEmpty());
    }

    public static boolean isInternalLink(Uri uri)
    {
        if(uri == null)
            return false;
        else
            return "imgur".equalsIgnoreCase(uri.getScheme());
    }
    private static boolean isLinkToImage(String s)
    {
        s = s.toLowerCase(Locale.ENGLISH);
        return s.endsWith(".jpg") || s.endsWith(".jpeg") || s.endsWith(".png") || s.endsWith(".gif") || s.endsWith(".gifv") || s.endsWith(".mp4") || s.endsWith(".webm");
    }

}
