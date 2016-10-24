package com.treasure_ct.android_xt.studyfragment.simpledemo.explorer_xt;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.treasure_ct.android_xt.R;

public class ExplorerActivity extends Activity {
    private WebView webview;
    private String url="http://www.baidu.com/";
    private ProgressDialog p_dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_simpledemo_explorer);
        webview=(WebView)findViewById(R.id.webview_xt);
        webview.loadUrl(url);
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return  true;
            }
        });
        //启用JS
        webview.getSettings().setJavaScriptEnabled(true);
        //设置页面适应屏幕显示
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setUseWideViewPort(true);
        //优先使用缓存
        webview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webview.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if(newProgress==100){
                    closeDialog();
                }else{
                    openDialog(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
    }

    private void openDialog(int newProgress) {
        if(p_dialog==null){
            p_dialog=new ProgressDialog(ExplorerActivity.this);
            p_dialog.setTitle("正在加载，请稍后");
            p_dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            p_dialog.setProgress(newProgress);
            p_dialog.show();
        }else{
            p_dialog.setProgress(newProgress);
        }
    }

    private void closeDialog() {
        if(p_dialog!=null&&p_dialog.isShowing()){
            p_dialog.dismiss();
            p_dialog=null;
        }
    }
    //退出键设置

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            if(webview.canGoBack()){
                webview.goBack();//当前不是主页面，退回上一页面
                return true;
            }else{
                exitDialog();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exitDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("确认框");
        builder.setMessage("你真的要退出吗？");
        builder.setPositiveButton("退出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(ExplorerActivity.this,"感谢您的使用",Toast.LENGTH_SHORT).show();
                System.exit(0);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(ExplorerActivity.this,"请继续使用",Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog a_dialog=builder.create();
        a_dialog.show();
    }
}
