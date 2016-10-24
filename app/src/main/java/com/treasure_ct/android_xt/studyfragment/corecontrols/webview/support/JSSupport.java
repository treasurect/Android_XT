package com.treasure_ct.android_xt.studyfragment.corecontrols.webview.support;

/**
 * Created by treasure on 2016.09.24.
 */

import android.content.Context;
import android.os.Build;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * 用于WebView中 JS调用Java的功能
 */

public class JSSupport {
    //这个地方有注解代表JS可以直接调用
    private Context mContext;

    public JSSupport(Context context) {
        mContext = context;
    }

    @JavascriptInterface
    public void showToast(String str){
        Toast.makeText(mContext, str, Toast.LENGTH_SHORT).show();
    }
    /**
     * JS 调用JAVA获取数据
     */
    @JavascriptInterface
    public String getDeviceModel(){
        return Build.DEVICE;
    }
    @JavascriptInterface
    public String getContactList(){
        String ret = null;

        return ret;
    }

}
