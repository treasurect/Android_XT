package com.treasure_ct.android_xt.studyfragment.loadingways.asynctask;

import android.os.AsyncTask;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by treasure on 2016.08.31.
 */
public class AsyncTask_SimpleTask extends AsyncTask<TextView,Integer,String>{
    private TextView text;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(TextView... params) {
        text = params[0];
        for (int i = 0; i < 100; i++) {
            publishProgress(i);
            try {
                Thread.sleep(new Random().nextInt(10) * 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "下载完成";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        text.setText(String.valueOf(values[0]));
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        text.setText(s);
    }
}
