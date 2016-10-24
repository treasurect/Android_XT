package com.treasure_ct.android_xt.studyfragment.loadingways.asynctask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import com.treasure_ct.android_xt.R;

/**
 * Created by treasure on 2016.08.31.
 */
public class AsyncTask_NetWork extends AppCompatActivity implements AsyncTask_NetWorkTask.CallBack<AsyncTask_NetWork_Entry> {
    private WebView webView;
    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_network);
        webView = (WebView) findViewById(R.id.async_webView);
//        image = (ImageView) findViewById(R.id.async_image);
        new AsyncTask_NetWorkTask<>("http://www.tngou.net/api/top/show?id=12892",AsyncTask_NetWork_Entry.class).execute(this);
    }

    @Override
    public void onSuccess(AsyncTask_NetWork_Entry entry) {
                    setTitle(entry.getTitle());
            webView.loadDataWithBaseURL("http://www.tngou.net",entry.getMessage(),"text/html;charset=utf-8","UTF-8",null);


//        try {
//            JSONObject object = new JSONObject(s);
//            String title = object.optString("title");
//            String message = object.optString("message");
//            setTitle(title);
//            webView.loadDataWithBaseURL("http://www.tngou.net",message,"text/html;charset=utf-8","UTF-8",null);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void onFail(Exception e) {
        e.printStackTrace();
        Toast.makeText(AsyncTask_NetWork.this, "网络错误", Toast.LENGTH_SHORT).show();
    }
}
