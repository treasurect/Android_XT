package com.treasure_ct.android_xt.studyfragment.loadingways.asynctask;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by treasure on 2016.08.31.
 */
public class AsyncTask_NetWorkTask<T> extends AsyncTask<AsyncTask_NetWorkTask.CallBack<T>,Void,Object>{
    private CallBack<T> callBack;
    private String url;
    private Class<T>t;
    public AsyncTask_NetWorkTask(String url, Class<T>t) {
        this.url = url;
        this.t = t;
    }

    @Override
    protected Object doInBackground(CallBack<T>... params) {
        callBack = params[0];
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            int code = connection.getResponseCode();
            if (code == 200){
                InputStream is = connection.getInputStream();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int length;
                byte[] bytes = new byte[102400];
                while ((length = is.read(bytes)) != -1){
                    bos.write(bytes,0,length);
                }
                Gson gson = new Gson();
                return gson.fromJson(bos.toString("UTF-8"),AsyncTask_NetWork_Entry.class);
            }else{
                return new RuntimeException("Code:"+code);
            }
        } catch (IOException e) {
            return e;
        }
    }

    @Override
    protected void onPostExecute(Object s) {
        super.onPostExecute(s);
        if (t.isInstance(s)){
            callBack.onSuccess((T) s);
        }else if (s instanceof Exception){
            callBack.onFail((Exception) s);
        }
    }

    public interface CallBack<S>{
        void onSuccess(S s);
        void onFail(Exception e);
    }
}
