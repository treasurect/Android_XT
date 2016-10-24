package com.treasure_ct.android_xt.studyfragment.loadingways.handler.pager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by treasure on 2016.09.01.
 */
public class Handler_NetWork_Pager_Runnable implements Runnable,Handler.Callback {
    private String url;
    private MyCall myCall;
    private Handler handler;

    public Handler_NetWork_Pager_Runnable(String url, MyCall myCall) {
        this.url = url;
        this.myCall = myCall;
        handler = new Handler(Looper.getMainLooper(),this);
    }

    @Override
    public void run() {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            int code = connection.getResponseCode();
            if (code == 200) {
                InputStream is = connection.getInputStream();
                int length;
                byte[] bytes = new byte[102400];
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                while ((length = is.read(bytes))!=-1){
                    bos.write(bytes,0,length);
                }
                String s = bos.toString("UTF-8");
                handler.obtainMessage(0,s).sendToTarget();
            }else {
                RuntimeException exception = new RuntimeException("Code:" + code);
                String s = "服务器错误";
                Bundle bundle = new Bundle();
                bundle.putString("msg",s);
                bundle.putSerializable("exception",exception);
                Message message = handler.obtainMessage(1);
                message.setData(bundle);
                handler.sendMessage(message);

            }
        } catch (IOException e) {
            String s = "网络错误";
            Bundle bundle = new Bundle();
            bundle.putString("msg",s);
            bundle.putSerializable("exception",e);
            Message message = handler.obtainMessage(1);
            message.setData(bundle);
            handler.sendMessage(message);
        }
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 0:
                myCall.onSuccess((String) msg.obj);
                break;
            case 1:
                Bundle bundle = msg.getData();
                Exception exception = (Exception) bundle.getSerializable("exception");
                String msg1 = bundle.getString("msg");
                myCall.onFail(exception,msg1);
                break;
        }
        return false;
    }
    public interface MyCall{
        void onSuccess(String s);
        void onFail(Exception e, String s);
    }
}
