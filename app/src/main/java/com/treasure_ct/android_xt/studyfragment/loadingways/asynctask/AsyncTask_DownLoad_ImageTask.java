package com.treasure_ct.android_xt.studyfragment.loadingways.asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.treasure_ct.android_xt.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by treasure on 2016.09.01.
 */
public class AsyncTask_DownLoad_ImageTask extends AsyncTask<String,Void,Bitmap>{
    private ImageView im;

    public AsyncTask_DownLoad_ImageTask(ImageView im) {
        this.im = im;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        AsyncTask_DownLoad_ImageTask tag = (AsyncTask_DownLoad_ImageTask) im.getTag();
        im.setTag(this);
        im.setImageResource(R.mipmap.lufei);
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        String url = params[0];
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            int code = connection.getResponseCode();
            if (code == 200) {
                InputStream is = connection.getInputStream();
                return  BitmapFactory.decodeStream(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (bitmap != null){
            im.setImageBitmap(bitmap);
        }else {
            im.setImageResource(R.mipmap.icon_main8);
        }
    }
}
