package com.treasure_ct.android_xt.studyfragment.corecontrols.webview.support;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by treasure on 2016.09.24.
 */

public class MyWebviewClient extends WebViewClient{
    private BrowerSupport Support;

    public MyWebviewClient(BrowerSupport support) {
        Support = support;
    }

    /**
     * 这个方法用于实现网址的拦截和特定动作的调用
     * 当这个方法返回false的时候，WebView会自己加载网址
     * 返回true ，那么webView就不会进行网址的加载
     * @param view
     * @param url
     * @return
     */
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        boolean ret = false;
        if (url.startsWith("tel://")) {
            //TODO:使用隐式意图 ACTION_VIEW,ACTION_CALL
            //调用回调接口，传给外部程序
            if (Support != null) {
                Support.loadAppurl(view,url);
            }
            return true;
        }
        return ret;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        if (Support != null) {
            Support.onPageStarted(view,url,favicon);
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        Support.onPageFinished(view, url);
    }

    @Override
    public void onPageCommitVisible(WebView view, String url) {
        Support.onPageCommitVisible(view, url);
    }
}
