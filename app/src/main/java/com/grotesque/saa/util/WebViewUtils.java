package com.grotesque.saa.util;

import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static com.grotesque.saa.util.LogUtils.LOGE;

/**
 * Created by 경환 on 2016-04-07.
 */
public class WebViewUtils{
        public static WebView setWebview(WebView wv) {

            //웹뷰 글씨 크기 지정
            wv.getSettings().setTextZoom(85);

            //웹뷰 캐시 관련
            wv.clearCache(true);
            wv.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
            wv.getSettings().setJavaScriptEnabled(true);
            wv.getSettings().setLoadWithOverviewMode(true);
            wv.getSettings().getPluginState();
            wv.getSettings().setPluginState(WebSettings.PluginState.ON);
            wv.getSettings().setSupportZoom(true);
            wv.getSettings().setDefaultFontSize(24);

            wv.getSettings().setSupportMultipleWindows(true);

            wv.setFocusable(false);
            wv.setHorizontalScrollBarEnabled(false);
            wv.setVerticalScrollBarEnabled(false);

            wv.setWebChromeClient(new WebChromeClient());
            wv.setWebViewClient(new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    LOGE("url = ", url);
                    return super.shouldOverrideUrlLoading(view, url);
                }
            });
            return wv;
        }
}
