package com.grotesque.saa.auth;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;

import com.grotesque.saa.util.AccountUtils;
import com.grotesque.saa.util.MessageDigestUtils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class AuthTokenManager {

    private static String mAuthToken = null;
    protected static AuthTokenManager mAuthTokenManager = null;
    final String a = "AES/CBC/PKCS5Padding";
    final String b = "UTF8";
    final String c = "SHA1PRNG";
    final String d = "PBKDF2WithHmacSHA1";
    final String e = "SHA1";
    final String f = "userId";

    public AuthTokenManager()
    {
    }

    private Cipher a(int k, Context context) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException {
        SecureRandom securerandom = SecureRandom.getInstance("SHA1PRNG");
        IvParameterSpec ivparameterspec = new IvParameterSpec(new byte[] {
            29, 88, -79, -101, -108, -38, -126, 90, 52, 101, 
            -35, 114, 12, -48, -66, -30
        });
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(k, a(context), ivparameterspec, securerandom);
        return cipher;
    }

    private SecretKey a(Context context) throws NoSuchAlgorithmException {
        String s = MessageDigestUtils.a(context);
        MessageDigest messagedigest = MessageDigest.getInstance("SHA1");
        try {
            messagedigest.update(s.getBytes("UTF8"));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        char[] base64 = Base64.encodeToString(messagedigest.digest(), 1).toCharArray();
        try {
            return new SecretKeySpec(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(new PBEKeySpec(base64, "userId".getBytes("UTF8"), 100, 128)).getEncoded(), "SHA1");
        } catch (InvalidKeySpecException | UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    public static AuthTokenManager getInstance(){
        if(mAuthTokenManager == null)
            mAuthTokenManager = new AuthTokenManager();
        return mAuthTokenManager;
    }

    public void clearAuthToken(Context context){
        mAuthToken = null;
        AccountUtils.setAuthToken(context, mAuthToken);
    }

    public String doDecrypt(String s, Context context) {
        try {
            Cipher cypher = a(2, context);
            byte[] s1 = Base64.decode(s.getBytes("UTF8"), 2);
            cypher.getIV();
            s = new String(cypher.doFinal(s1, 0, s1.length), "UTF8");
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | InvalidAlgorithmParameterException | BadPaddingException | UnsupportedEncodingException | IllegalBlockSizeException e1) {
            e1.printStackTrace();
        }
        return s;
    }

    public String doEncryption(String s, Context context)
    {
        try {
            Cipher c = a(1, context);
            byte[] bytes = c.doFinal(s.getBytes("UTF8"));
            c.getIV();
            s = Base64.encodeToString(bytes, 2);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidAlgorithmParameterException | InvalidKeyException | BadPaddingException | UnsupportedEncodingException | IllegalBlockSizeException e1) {
            e1.printStackTrace();
        }
        return s;
    }

    public void finalize()
    {
        mAuthToken = null;
    }

    public String getAuthToken(Context context)
    {
        if(TextUtils.isEmpty(mAuthToken))
        {
            String authToken = AccountUtils.getAuthToken(context);
            if(authToken!=null)
                mAuthToken = doDecrypt(authToken, context);

        }
        return mAuthToken;
    }

    public void setAuthToken(Context context, String s){
        if(s == null)
            return;
        mAuthToken = s;
        AccountUtils.setAuthToken(context, doEncryption(s, context));
    }

}
