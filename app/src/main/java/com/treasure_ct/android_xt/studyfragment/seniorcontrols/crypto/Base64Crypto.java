package com.treasure_ct.android_xt.studyfragment.seniorcontrols.crypto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.treasure_ct.android_xt.R;

import java.io.UnsupportedEncodingException;

public class Base64Crypto extends AppCompatActivity {
    private EditText edit;
    private TextView result;
    private TextView result_re;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seniorcontrols_base64_crypto);
        edit = (EditText) findViewById(R.id.crypto_edit);
        result = (TextView) findViewById(R.id.crypto_result);
        result_re = (TextView) findViewById(R.id.crypto_result_re);
    }

    public void btnBase64Encode(View view) {
        String str = edit.getText().toString().trim();
        ///Base64编码，NO_WRAP代表编码结果没有任何换行
        //加密
        String encode = Base64.encodeToString(str.getBytes(), Base64.NO_WRAP);
        result.setText(encode);
    }

    public void btnBase64EncodeRe(View view) {
        if (result != null){
            String str = result.getText().toString();
            byte[] decode = Base64.decode(str.getBytes(), Base64.NO_WRAP);
            try {
                String s = new String(decode, "UTF-8");
                result_re.setText(s);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }
}
