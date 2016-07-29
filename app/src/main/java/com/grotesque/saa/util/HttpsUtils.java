package com.grotesque.saa.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.OkHttpClient;

import static com.grotesque.saa.util.LogUtils.LOGE;


/**
 * Created by 0614_000 on 2015-06-05.
 */
public class HttpsUtils {
    private static final String TAG = LogUtils.makeLogTag(HttpsUtils.class);


    public static HttpURLConnection conn = null;
    public static URL url;
    public static int responseCode;
    public static String response;





    public static CookieHandler getCookieHandler(){
        return CookieHandler.getDefault();
    }
    public static CookieStore getCookieStore() { return ((CookieManager) CookieHandler.getDefault()).getCookieStore();}
    public static boolean getAuth(String id, String password){

        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));

        response = "";

        HashMap<String, String> postParams = new HashMap<>();
        postParams.put("user_id", id);
        postParams.put("password", password);


        try {
            url = new URL("http://www.serieamania.com/xe/index.php?act=procMemberLogin");
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestProperty("Accept-Encoding", "identity");
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));

            writer.flush();
            writer.close();
            os.close();
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {

                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line + "\n";
                }
                br.close();
            }
            else {
                response="";
                return false;
            }

        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, String.valueOf(e));
            return false;
        }finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return true;
    }
    public static String getHtml(String address){
        Writer response = new StringWriter();
        char[] buffer = new char[1024];
        try {
            url = new URL(address);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.81 Safari/537.36");
            conn.setDoInput(true);

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                int n;
                while ((n = br.read(buffer)) != -1) {
                    response.write(buffer, 0, n);
                }
                br.close();
            }
            else {
                response = null;
                return "";
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return response.toString();
    }


    public static Boolean getBoardListJSON(String mid, String page){
        response = "";
        try {
            url = new URL("http://www.serieamania.com/xe/index.php?act=dispBoardContentList&mid="+mid+"&page="+page);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept-Encoding", "identity");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Accept-Language", "ko-KR, ko");
            conn.setDoInput(true);

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                while ((line = br.readLine()) != null) {
                    response += line + "\n";
                }
                br.close();
                LogUtils.LOGE(TAG, "게시판 정보 " + response);
            }
            else {
                response = null;
                return false;
            }

        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, String.valueOf(e));
            return false;
        }finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return true;
    }

    public static boolean onActiveNetwork(Context context){
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork == null || !activeNetwork.isConnected()) {
            // if there's no network, don't try to change the selected account
            Toast.makeText(context, "연결이 불안정하니 다시 시도 해주시기 바랍니다.",
                    Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }
}
