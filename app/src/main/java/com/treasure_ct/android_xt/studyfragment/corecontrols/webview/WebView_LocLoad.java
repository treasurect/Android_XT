package com.treasure_ct.android_xt.studyfragment.corecontrols.webview;

import android.graphics.Bitmap;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.corecontrols.webview.support.BrowerSupport;
import com.treasure_ct.android_xt.studyfragment.corecontrols.webview.support.MyWebChormClient;
import com.treasure_ct.android_xt.studyfragment.corecontrols.webview.support.MyWebviewClient;

/**
 * WebView的使用：
 * 1.WebView 的基本使用
 * 2.WebView控制，UI状态显示
 * back,forward
 * getUrl()获取网址
 *
 * 3.WebView 加载内容的方式
 * 4.WebView网址的拦截
 * 5.**********  WebView Java与JS的交互  ********
 */
public class WebView_LocLoad extends AppCompatActivity implements View.OnClickListener, BrowerSupport {

    private WebView webView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corecontrols_webview_locload);
        Button gomove = (Button) findViewById(R.id.gomove1);
        Button gostop = (Button) findViewById(R.id.gostop1);
        Button goreload = (Button) findViewById(R.id.goreload1);
        webView = (WebView) findViewById(R.id.webView_loc);
        mProgressBar = (ProgressBar) findViewById(R.id.webView_progress_bar1);
        //TODO:解决点击url调到自带浏览器的问题
        //设置一个WebViewClient对象，可以解决跳转到系统自带浏览器的问题
        //如果不希望拦截网址，使用系统的对象即可
        webView.setWebViewClient(new MyWebviewClient(this));

        //TODO:实现网页记载进度的显示/标题显示
        webView.setWebChromeClient(new MyWebChormClient(this));

        //TODO:部分网址需浏览器支持 / 打开JS
        WebSettings settings = webView.getSettings();
        //开启JS支持
        if (Build.VERSION.SDK_INT < 11) {
            settings.setJavaScriptEnabled(true);
        } else {
            settings.setDisplayZoomControls(true);
        }

//        在网页中显示放大缩小
        settings.setBuiltInZoomControls(true);
        //允许加载本地网页
        settings.setAllowFileAccess(true);
        //设置WebView排版算法，实现单列模式，不允许横向滑动
//        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.loadUrl("file:///android_asset/index.html");


        gomove.setOnClickListener(this);
        gostop.setOnClickListener(this);
        goreload.setOnClickListener(this);
    }

    //实现后退网页
    @Override
    public void onBackPressed() {
        //1.判断可以后退的权限，可以则后退
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gomove1:
                if (webView.canGoForward()) {
                    webView.goForward();
                }
                break;
            case R.id.gostop1:
                //停止当前的页面加载
                webView.stopLoading();
                break;
            case R.id.goreload1:
                //刷新，重新加载当前网页
                webView.reload();
//                webView.loadUrl(webView.getUrl());
                break;
        }
    }

    @Override
    public void onReceivedTitle(WebView webView, String title) {
        setTitle(title);
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        mProgressBar.setProgress(newProgress);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onPageCommitVisible(WebView view, String url) {

    }

    @Override
    public void loadAppurl(WebView view, String url) {

    }
}
