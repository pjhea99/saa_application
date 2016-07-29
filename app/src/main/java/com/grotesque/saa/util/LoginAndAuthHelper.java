package com.grotesque.saa.util;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.grotesque.saa.auth.AuthTokenManager;
import com.grotesque.saa.common.api.RetrofitApi;
import com.grotesque.saa.common.data.ResponseData;

import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.grotesque.saa.util.LogUtils.LOGE;
import static com.grotesque.saa.util.LogUtils.LOGW;
import static com.grotesque.saa.util.LogUtils.makeLogTag;


public class LoginAndAuthHelper {

    private static final String TAG = makeLogTag(LoginAndAuthHelper.class);

    Context mAppContext;

    // The Activity this object is bound to (we use a weak ref to avoid context leaks)
    WeakReference<Activity> mActivityRef;

    // Callbacks interface we invoke to notify the user of this class of useful events
    WeakReference<Callbacks> mCallbacksRef;

    // Name of the account to log in as (e.g. "foo@example.com")
    String mAccountName;

    String mAccountPassword;

    // Are we in the started state? Started state is between onStart and onStop.
    boolean mStarted = false;

    // True if we are currently showing UIs to resolve a connection error.
    boolean mResolving = false;


    public interface Callbacks {
        void onAuthSuccess(String accountName, boolean newlyAuthenticated);
        void onAuthFailure(int error);
    }

    public LoginAndAuthHelper(Activity activity, Callbacks callbacks, String accountName, String accountPassword) {
        LOGE(TAG, "Helper created. Account: " + mAccountName);
        mActivityRef = new WeakReference<Activity>(activity);
        mCallbacksRef = new WeakReference<Callbacks>(callbacks);
        mAppContext = activity.getApplicationContext();
        mAccountName = accountName;
        mAccountPassword = accountPassword;
    }

    public boolean isStarted() {
        return mStarted;
    }

    public String getAccountName() {
        return mAccountName;
    }
    public String getAccountPassword() { return mAccountPassword;}

    private Activity getActivity(String methodName) {
        Activity activity = mActivityRef.get();
        if (activity == null) {
            LOGE(TAG, "Helper lost Activity reference, ignoring (" + methodName + ")");
        }
        return activity;
    }

    public void retryAuthByUserRequest() {
        LOGE(TAG, "Retrying sign-in/auth (user-initiated).");

        PrefUtils.markUserRefusedSignIn(mAppContext, false);

    }

    /** Starts the helper. Call this from your Activity's onStart(). */
    public void start() {
        Activity activity = getActivity("start()");
        if (activity == null) {
            LOGE(TAG, "Activity is null");
            return;
        }

        if (mStarted) {
            LOGE(TAG, "Helper already started. Ignoring redundant call.");
            return;
        }

        mStarted = true;
        if (mResolving) {
            // if resolving, don't reconnect the plus client
            LOGE(TAG, "Helper ignoring signal to start because we're resolving a failure.");
            return;
        }

        LOGE(TAG, "Helper connected, account " + mAccountName);




        Call<ResponseData> call = RetrofitApi.getInstance().procMemberLogin(mAccountName, mAccountPassword, "N");
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if(response.body().getError() == -1){
                    Callbacks callbacks;
                    switch (response.body().getMessage()){
                        case "잘못된 비밀번호입니다.":
                            if (null != (callbacks = mCallbacksRef.get())) {
                                callbacks.onAuthFailure(1);
                            }
                            break;
                        case "존재하지 않는 회원 아이디입니다.":
                            if (null != (callbacks = mCallbacksRef.get())) {
                                callbacks.onAuthFailure(0);
                            }
                            break;
                    }
                }else
                    reportAuthSuccess(true);
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                LOGE(TAG, String.valueOf(t));
                if(t.toString().contains("SocketTimeout")){
                    Toast.makeText(mAppContext, "서버 연결이 지연되고 있습니다.", Toast.LENGTH_LONG).show();
                }
                reportAuthFailure();
            }
        });



    }


    /** Stop the helper. Call this from your Activity's onStop(). */
    public void stop() {
        if (!mStarted) {
            LOGW(TAG, "Helper already stopped. Ignoring redundant call.");
            return;
        }

        LOGE(TAG, "Helper stopping.");
        mStarted = false;

        mResolving = false;
    }

    private void reportAuthSuccess(boolean newlyAuthenticated) {
        LOGE(TAG, "Auth success for account " + mAccountName + ", newlyAuthenticated=" + newlyAuthenticated);
        AccountUtils.setLoginState(mAppContext, true);

        AccountUtils.setActiveAccount(mAppContext, mAccountName);
        AuthTokenManager.getInstance().setAuthToken(mAppContext, mAccountPassword);

        Callbacks callbacks;
        if (null != (callbacks = mCallbacksRef.get())) {
            callbacks.onAuthSuccess(mAccountName, newlyAuthenticated);
        }
    }

    private void reportAuthFailure() {
        LOGE(TAG, "Auth FAILURE for account " + mAccountName);
        Callbacks callbacks;
        if (null != (callbacks = mCallbacksRef.get())) {
            callbacks.onAuthFailure(2);
        }
    }
}