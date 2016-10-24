package com.treasure_ct.android_xt.studyfragment.corecontrols.webview.support;

/**
 * Created by treasure on 2016.09.24.
 */

import android.graphics.Bitmap;
import android.webkit.WebView;

/**
 * 用于接收 在WebChromeClient   UI状态变化
 */
public interface BrowerSupport {
    void onReceivedTitle(WebView webView,String title);
    void onProgressChanged(WebView view, int newProgress);
    void onPageStarted(WebView view, String url, Bitmap favicon);
    void onPageFinished(WebView view, String url);
    void onPageCommitVisible(WebView view, String url);
    //实现网址的拦截
    void loadAppurl(WebView view, String url);

}
