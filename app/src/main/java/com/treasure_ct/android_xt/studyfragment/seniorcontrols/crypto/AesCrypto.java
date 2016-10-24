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

public class AesCrypto extends AppCompatActivity {

    private EditText edit1, edit2;
    private TextView text1, text2;
    private String s1,s2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seniorcontrols_aes_crypto);
        edit1 = (EditText) findViewById(R.id.aes_user);
        edit2 = (EditText) findViewById(R.id.aes_pass);
        text1 = (TextView) findViewById(R.id.aes_user_text);
        text2 = (TextView) findViewById(R.id.aes_pass_text);
    }

    public void btnAddCrypto1(View view) {
        String user1 = edit1.getText().toString().trim();
        String pass1 = edit2.getText().toString().trim();
        if (pass1.length() == 16){
            byte[] bytes = CryptoUtil.aesEncryptSimple(user1.getBytes(), pass1.getBytes());
             s1 = Base64.encodeToString(bytes, Base64.NO_WRAP);
            text1.setText(s1);
        }else {
            Toast.makeText(this, "密码必须为16位", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnRemoveCrypto1(View view) {
        String user1 = edit1.getText().toString().trim();
        String pass1 = edit2.getText().toString().trim();
        if (pass1.length() == 16){
            byte[] decode = Base64.decode(s1, Base64.NO_WRAP);
            byte[] bytes = CryptoUtil.aesDecryptSimple(decode, pass1.getBytes());
            String s = new String(bytes);
            text2.setText(s);
        }else {
            Toast.makeText(this, "密码必须为16位", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnAddCrypto2(View view) {
        String user1 = edit1.getText().toString().trim();
        String pass1 = edit2.getText().toString().trim();
        if (pass1.length() == 16) {
            byte[] iv = {23, 33, 2, 3, 3, 4, 6, 7, 1, 3, 3, 4, 4, 3, 4, 4};
            byte[] bytes = CryptoUtil.aesEncryptWithIv(user1.getBytes(), pass1.getBytes(), iv);
            s2 = Base64.encodeToString(bytes, Base64.NO_WRAP);
            text1.setText(s2);
        }else {
            Toast.makeText(this, "密钥必须为16位", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnRemoveCrypto2(View view) {
        String user1 = edit1.getText().toString().trim();
        String pass1 = edit2.getText().toString().trim();
        if (pass1.length() == 16) {
            byte[] iv = {23, 33, 2, 3, 3, 4, 6, 7, 1, 3, 3, 4, 4, 3, 4, 4};
            byte[] decode = Base64.decode(s2, Base64.NO_WRAP);
            byte[] bytes = CryptoUtil.aesDecryptWithIv(decode, pass1.getBytes(), iv);
            String s = new String(bytes);
            text2.setText(s);
        }else {
            Toast.makeText(this, "密钥必须为16位", Toast.LENGTH_SHORT).show();
        }
    }
}
