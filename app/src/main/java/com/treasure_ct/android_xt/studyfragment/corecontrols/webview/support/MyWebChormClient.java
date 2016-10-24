package com.treasure_ct.android_xt.studyfragment.corecontrols.webview.support;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by treasure on 2016.09.24.
 */

/**
 *这个类，可以给WebView 设置上，，能够监听WebView 加载进度/标题显示/JS对话框调用
 */
public class MyWebChormClient extends WebChromeClient {
    private BrowerSupport mSupport;

    public MyWebChormClient(BrowerSupport support) {
        mSupport = support;
    }

    /**
     * 进度变化，显示进度，范围【0 - 100】
     * @param view
     * @param newProgress
     */


    /**
     * 收到标题。显示标题
     * @param view
     * @param title
     */
    @Override
    public void onReceivedTitle(WebView view, String title) {
        if (mSupport != null) {
            mSupport.onReceivedTitle(view,title);
        }
    }
}
