package com.treasure_ct.android_xt.studyfragment.seniorcontrols;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.seniorcontrols.crypto.Base64Crypto;
import com.treasure_ct.android_xt.studyfragment.seniorcontrols.crypto.RsaCrypto_Ceshi;
import com.treasure_ct.android_xt.studyfragment.seniorcontrols.crypto.DesCrypto;
import com.treasure_ct.android_xt.studyfragment.seniorcontrols.crypto.AesCrypto;
import com.treasure_ct.android_xt.studyfragment.seniorcontrols.crypto.GZipCrypto;
import com.treasure_ct.android_xt.studyfragment.seniorcontrols.crypto.RsaCrypto;
import com.treasure_ct.android_xt.studyfragment.seniorcontrols.crypto.URLEncodeing;

public class CryptoActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seniorcontrols_crypto);
        Button crypto_base64 = (Button) findViewById(R.id.crypto_base64);
        Button crypto_urlencoding = (Button) findViewById(R.id.crypto_urlencoding);
        Button crypto_gzip = (Button) findViewById(R.id.crypto_gzip);
        Button crypto_des = (Button) findViewById(R.id.crypto_des);
        Button crypto_aes = (Button) findViewById(R.id.crypto_aes);
        Button crypto_rsa = (Button) findViewById(R.id.crypto_rsa);
        Button crypto_rsa_ceshi = (Button) findViewById(R.id.crypto_rsa_ceshi);
        crypto_base64.setOnClickListener(this);
        crypto_urlencoding.setOnClickListener(this);
        crypto_gzip.setOnClickListener(this);
        crypto_des.setOnClickListener(this);
        crypto_aes.setOnClickListener(this);
        crypto_rsa.setOnClickListener(this);
        crypto_rsa_ceshi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.crypto_base64:
                startActivity(new Intent(this, Base64Crypto.class));
                break;
            case R.id.crypto_urlencoding:
                startActivity(new Intent(this, URLEncodeing.class));
                break;
            case R.id.crypto_gzip:
                startActivity(new Intent(this, GZipCrypto.class));
                break;
            case R.id.crypto_des:
                startActivity(new Intent(this, DesCrypto.class));
                break;
            case R.id.crypto_aes:
                startActivity(new Intent(this, AesCrypto.class));
                break;
            case R.id.crypto_rsa:
                startActivity(new Intent(this, RsaCrypto.class));
                break;
            case R.id.crypto_rsa_ceshi:
                startActivity(new Intent(this, RsaCrypto_Ceshi.class));
                break;
        }
    }
}
