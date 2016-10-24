package com.treasure_ct.android_xt.studyfragment.corecontrols;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.corecontrols.webview.WebView_LocLoad;
import com.treasure_ct.android_xt.studyfragment.corecontrols.webview.WebView_NetLoad;
import com.treasure_ct.android_xt.studyfragment.corecontrols.webview.WebView_StrLoad;
import com.treasure_ct.android_xt.studyfragment.corecontrols.webview.WebView_JS;

public class WebViewActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corecontrols_webview);
        Button netload = (Button) findViewById(R.id.webView_netload);
        Button locload = (Button) findViewById(R.id.webView_locload);
        Button jsload = (Button) findViewById(R.id.webView_jsload);
        Button strload = (Button) findViewById(R.id.webView_strload);
        netload.setOnClickListener(this);
        locload.setOnClickListener(this);
        jsload.setOnClickListener(this);
        strload.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.webView_netload:
                startActivity(new Intent(this, WebView_NetLoad.class));
                break;
            case R.id.webView_locload:
                startActivity(new Intent(this, WebView_LocLoad.class));
                break;
            case R.id.webView_strload:
                startActivity(new Intent(this, WebView_StrLoad.class));
                break;
            case R.id.webView_jsload:
                startActivity(new Intent(this, WebView_JS.class));
                break;

        }
    }
}
