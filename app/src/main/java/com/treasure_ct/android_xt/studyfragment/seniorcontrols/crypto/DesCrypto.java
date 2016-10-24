package com.treasure_ct.android_xt.studyfragment.seniorcontrols.crypto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.seniorcontrols.crypto.utils.CryptoUtil;

public class DesCrypto extends AppCompatActivity {

    private EditText user;
    private EditText pass;
    private TextView text;
    private TextView retext;
    private String s,str,pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_des_crypto);
        user = (EditText) findViewById(R.id.des_user);
        pass = (EditText) findViewById(R.id.des_pass);
        text = (TextView) findViewById(R.id.des_text);
        retext = (TextView) findViewById(R.id.des_text_re);
    }

    public void btnDesCrypto(View view) {
        str = user.getText().toString().trim();
        pwd = pass.getText().toString().trim();
        if (pwd.length() == 8 && str != null && str.equals("")) {
            byte[] desEncrypt = CryptoUtil.desEncrypt(str.getBytes(), pwd.getBytes());
            s = Base64.encodeToString(desEncrypt, Base64.NO_WRAP);
            text.setText(s);
        }else {
            Toast.makeText(this, "密码必须为8位", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnDesCryptoRe(View view) {
        str = user.getText().toString().trim();
        pwd = pass.getText().toString().trim();
        if (pwd.length() == 8 && str != null && str.equals("")) {
            //解密,把Base64字符串还原并且解密
            byte[] decode = Base64.decode(s, Base64.NO_WRAP);
            byte[] bytes = CryptoUtil.desDecrypt(decode, pass.getText().toString().trim().getBytes());
            String s1 = new String(bytes);
            retext.setText(s1);
        }else {
            Toast.makeText(this, "密码的长度必须为8位", Toast.LENGTH_SHORT).show();
        }
    }
}
