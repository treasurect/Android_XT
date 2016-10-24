package com.treasure_ct.android_xt.studyfragment.loadingways.asynctask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import com.treasure_ct.android_xt.R;

/**
 * Created by treasure on 2016.09.01.
 */
public class AsyncTask_DownLoad_Image extends AppCompatActivity implements AsyncTask_NetWorkTask.CallBack<AsyncTask_NetWork_Entry>{
    private WebView webView;
    private ImageView image1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_ceshi);
        webView = (WebView) findViewById(R.id.async_webView);
        image1 = (ImageView) findViewById(R.id.async_image);
        new AsyncTask_NetWorkTask<>("http://www.tngou.net/api/top/show?id=13122",AsyncTask_NetWork_Entry.class).execute(this);
    }

    @Override
    public void onSuccess(AsyncTask_NetWork_Entry entry) {
        setTitle(entry.getTitle());
        new AsyncTask_DownLoad_ImageTask(image1).execute("http://tnfs.tngou.net/img"+entry.getImage());
        webView.loadDataWithBaseURL("http://www.tngou.net",entry.getMessage(),"text/html;charset=utf-8","UTF-8",null);
    }

    @Override
    public void onFail(Exception e) {
    e.printStackTrace();
        Toast.makeText(AsyncTask_DownLoad_Image.this, "网络错误", Toast.LENGTH_SHORT).show();
    }
}
