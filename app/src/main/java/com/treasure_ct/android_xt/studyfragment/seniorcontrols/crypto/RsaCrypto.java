package com.treasure_ct.android_xt.studyfragment.seniorcontrols.crypto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.seniorcontrols.crypto.utils.CryptoUtil;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class RsaCrypto extends AppCompatActivity {

    private EditText edit;
    private TextView text1,text2;
    private String str;
    private String s;
    private PrivateKey aPrivate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seniorcontrols_rea_crypto);
        edit = (EditText) findViewById(R.id.rea_edit);
        text1 = (TextView) findViewById(R.id.rea_text);
        text2 = (TextView) findViewById(R.id.rea_textre);

    }

    public void btnReaCrypyo(View view) {
        KeyPair keyPair = CryptoUtil.generateRsaKey(1024);
        //私钥
         aPrivate = keyPair.getPrivate();
        //公钥
        PublicKey aPublic = keyPair.getPublic();
        //TODO:把公钥和私钥使用Base64编码，保存在文件中
        byte[] aPrivateEncoded = aPrivate.getEncoded();
        byte[] aPublicEncoded = aPublic.getEncoded();
        str = edit.getText().toString().trim();
        byte[] encrypt = CryptoUtil.rsaEncrypt(str.getBytes(), aPublic);
         s = Base64.encodeToString(encrypt, Base64.NO_WRAP);
        text1.setText(s);
    }

    public void btnReaCrypyoRe(View view) {
        byte[] decode = Base64.decode(s, Base64.NO_WRAP);
        byte[] bytes = CryptoUtil.rsaDecrypt(decode, aPrivate);
        String s = new String(bytes);
        text2.setText(s);
    }
}
