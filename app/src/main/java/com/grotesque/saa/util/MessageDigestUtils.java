package com.grotesque.saa.util;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.grotesque.saa.util.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageDigestUtils {

    private MessageDigestUtils(){
    }

    private static String a()
    {
        return String.format("%s-%s", Build.MODEL, Build.SERIAL);
    }

    public static String a(Context context)
    {
        String s = a(b(context));
        if(TextUtils.isEmpty(s))
            s = a(a());
        return b(String.format("SDK-%s", new Object[] {
                s
        }));
    }

    private static String a(String s)
    {
        if(s == null)
            return null;
        else
            return s.replaceAll("[0\\s]", "");
    }

    private static String b(Context context)
    {
        TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);

        return telephonyManager.getDeviceId();
    }

    private static String b(String s)
    {

        MessageDigest messagedigest = null;
        try {
            messagedigest = MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert messagedigest != null;
        messagedigest.reset();
        messagedigest.update(s.getBytes());
        s = StringUtils.toHexString(messagedigest.digest());

        return s;
    }

}
