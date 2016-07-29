package com.grotesque.saa.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import static com.grotesque.saa.util.LogUtils.LOGD;
import static com.grotesque.saa.util.LogUtils.LOGE;
import static com.grotesque.saa.util.LogUtils.LOGI;
import static com.grotesque.saa.util.LogUtils.LOGV;
import static com.grotesque.saa.util.LogUtils.makeLogTag;

public class AccountUtils {


    private static final String TAG = makeLogTag(AccountUtils.class);



    private static final String PREF_ACTIVE_ACCOUNT = "chosen_account";

    // these names are are prefixes; the account is appended to them
    private static final String PREFIX_PREF_AUTH_TOKEN = "auth_token_";
    private static final String PREFIX_PREF_AUTO_LOGIN = "auto_login_";
    private static final String PREFIX_PREF_LOG_IN_STATE = "login_state_";




    private static SharedPreferences getSharedPreferences(final Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static boolean hasActiveAccount(final Context context) {
        return !TextUtils.isEmpty(getActiveAccountName(context));
    }

    public static String getActiveAccountName(final Context context) {
        SharedPreferences sp = getSharedPreferences(context);
        return sp.getString(PREF_ACTIVE_ACCOUNT, null);
    }



    //현재 사용 중인 계정
    public static void setActiveAccount(final Context context, final String accountName) {
        LOGD(TAG, "Set active account to: " + accountName);
        SharedPreferences sp = getSharedPreferences(context);
        sp.edit().putString(PREF_ACTIVE_ACCOUNT, accountName).apply();
    }

    public static boolean getAutoLogin(final Context context) {
        SharedPreferences sp = getSharedPreferences(context);
        return sp.getBoolean(PREFIX_PREF_AUTO_LOGIN, false);
    }

    public static void setAutoLogin(final Context context, final boolean autoLogin) {
        LOGD(TAG, "Set auto login: " + autoLogin);
        SharedPreferences sp = getSharedPreferences(context);
        sp.edit().putBoolean(PREFIX_PREF_AUTO_LOGIN, autoLogin).apply();
    }

    public static boolean getLoginState(final Context context) {
        SharedPreferences sp = getSharedPreferences(context);
        return sp.getBoolean(PREFIX_PREF_LOG_IN_STATE, false);
    }

    public static void setLoginState(final Context context, final boolean loginState){
        SharedPreferences sp = getSharedPreferences(context);
        sp.edit().putBoolean(PREFIX_PREF_LOG_IN_STATE, loginState).apply();
    }

    public static String getAuthToken(final Context context) {
        SharedPreferences sp = getSharedPreferences(context);
        return hasActiveAccount(context) ?
                sp.getString(makeAccountSpecificPrefKey(context, PREFIX_PREF_AUTH_TOKEN), null) : null;
    }

    public static void setAuthToken(final Context context, final String accountName, final String authToken) {
        LOGI(TAG, "Auth token of length "
                + (TextUtils.isEmpty(authToken) ? 0 : authToken.length()) + " for "
                + accountName);
        SharedPreferences sp = getSharedPreferences(context);
        sp.edit().putString(makeAccountSpecificPrefKey(accountName, PREFIX_PREF_AUTH_TOKEN),
                authToken).commit();
        LOGV(TAG, "Auth Token: " + authToken);
    }

    public static void setAuthToken(final Context context, final String authToken) {
        if (hasActiveAccount(context)) {
            setAuthToken(context, getActiveAccountName(context), authToken);
        } else {
            LOGE(TAG, "Can't set auth token because there is no chosen account!");
        }
    }
    private static String makeAccountSpecificPrefKey(Context ctx, String prefix) {
        return hasActiveAccount(ctx) ? makeAccountSpecificPrefKey(getActiveAccountName(ctx),
                prefix) : null;
    }

    private static String makeAccountSpecificPrefKey(String accountName, String prefix) {
        return prefix + accountName;
    }
}
