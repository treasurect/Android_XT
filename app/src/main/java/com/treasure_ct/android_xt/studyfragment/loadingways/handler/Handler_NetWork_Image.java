package com.treasure_ct.android_xt.studyfragment.loadingways.handler;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.loadingways.handler.image.Handler_NetWork_Image_Runnable;

import java.util.Locale;

/**
 * Created by treasure on 2016.09.01.
 */
public class Handler_NetWork_Image extends AppCompatActivity implements Handler_NetWork_Image_Runnable.MyCall{
    private ImageView textView;
    private TextView text1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_network_image);
        textView = (ImageView) findViewById(R.id.text_handler);
        text1 = (TextView) findViewById(R.id.text_percent);
        Handler_NetWork_Image_Runnable runable = new Handler_NetWork_Image_Runnable("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1472712632&di=cb63eb9b4ffd5fe38804e1dd8d9df2ba&src=http://img3.duitang.com/uploads/item/201608/02/20160802212751_CLtXz.thumb.700_0.jpeg", this);
        new Thread(runable).start();
    }

    @Override
    public void onSuccess(Bitmap s) {
        textView.setImageBitmap(s);
    }

    @Override
    public void onFail(Exception e, String s) {
        e.printStackTrace();
        Toast.makeText(Handler_NetWork_Image.this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPercent(float percent) {
        text1.setText(String.format(Locale.CHINA,"%.2f%%",percent));
    }
}
