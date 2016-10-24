package com.treasure_ct.android_xt.studyfragment.seniorcontrols.crypto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.treasure_ct.android_xt.R;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class URLEncodeing extends AppCompatActivity {

    private TextView result;
    private TextView data;
    private TextView result_re;
    private TextView data_re;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seniorcontrols_urlencodeing);
        result = (TextView) findViewById(R.id.urlencoding_result);
        data = (TextView) findViewById(R.id.urlencoding_data);
        result_re = (TextView) findViewById(R.id.urlencoding_result_re);
        data_re = (TextView) findViewById(R.id.urlencoding_data_re);
    }

    public void btnURLEncoding(View view) {
        String str = "%E5%8F%98%E5%BD%A2%E9%87%91%E5%88%9A";
        try {
            String decode = URLDecoder.decode(str, "UTF-8");
            Log.d("GGGGGGGGGGGZIP", "btnURLEncoding: "+decode);
            data.setText(str);
            result.setText(decode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void btnURLEncodingRe(View view) {
        String str = null;
        try {
            str = URLEncoder.encode("天边最美的彩虹 66666666","UTF-8");
            Log.d("GGGGGGGGGGGZIP", "btnURLEncoding: "+str);
            data_re.setText("天边最美的彩虹 66666666");
            result_re.setText(str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
