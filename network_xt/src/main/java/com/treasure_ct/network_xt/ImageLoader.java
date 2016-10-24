package com.treasure_ct.network_xt;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageLoader extends AsyncTask<String, Void, Bitmap> {
    private ImageView image;
    private String url;

    public ImageLoader(ImageView image) {
        this.image = image;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
//        ImageLoader loader = (ImageLoader) image.getTag();
//        if (loader != null) {
//            loader.cancel(false);
//        }
        image.setTag(this);
        image.setImageResource(R.mipmap.coming);
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        url = params[0];
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            int code = connection.getResponseCode();
            if (code == 200) {
                InputStream is = connection.getInputStream();
                return BitmapFactory.decodeStream(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (bitmap != null) {
            image.setImageBitmap(bitmap);
            ImageUtil.cache.put(url, bitmap);
        } else {
            image.setImageResource(R.mipmap.coming);
        }
        image.setTag(null);
    }

    @Override
    protected void onCancelled(Bitmap bitmap) {
        if (bitmap != null) {
            ImageUtil.cache.put(url, bitmap);
        }
    }

}
