package com.treasure_ct.android_xt.studyfragment.seniorcontrols.crypto;

import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.seniorcontrols.crypto.utils.CryptoUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

public class RsaCrypto_Ceshi extends AppCompatActivity {

    private EditText edit;
    private boolean isOpen = false;
    private PublicKey mPublic;
    private PrivateKey mPrivate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seneiorcontrols_rsa_crypto__ceshi);
        edit = (EditText) findViewById(R.id.rsacrypto_ceshi);
        Button btn = (Button) findViewById(R.id.btn_rsacrypto_ceshi);
        SharedPreferences sharedPreferences = getSharedPreferences("treasure", MODE_PRIVATE);
         isOpen = sharedPreferences.getBoolean("isOpen", false);

        if (!isOpen){
            btn.setText("保存");
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putBoolean("isOpen",true);
            edit.apply();
        }else {
            btn.setText("加载");
        }
    }
    public void btnRsacryptoCeshi(View view) {
        if (!isOpen){
            KeyPair keyPair = CryptoUtil.generateRsaKey(1024);
            mPublic = keyPair.getPublic();
            mPrivate = keyPair.getPrivate();
            String str = edit.getText().toString().trim();
            byte[] enPublic = mPublic.getEncoded();
            byte[] enPrivate = mPrivate.getEncoded();
            byte[] enContent = CryptoUtil.rsaEncrypt(str.getBytes(), mPrivate);
            String sContent = Base64.encodeToString(enContent, Base64.NO_WRAP);
            String sPublic = Base64.encodeToString(enPublic, Base64.NO_WRAP);
            JSONObject object = new JSONObject();
            try{
                object.put("sContent",sContent);
                object.put("sPublic",sPublic);
                String string = object.toString();
                String state = Environment.getExternalStorageState();
                if (Environment.MEDIA_MOUNTED.equals(state)){
                    File file = Environment.getExternalStorageDirectory();
                    String path = file.getAbsolutePath();
                    File file1 = new File(path+"/trea.json");
                    if (!file1.exists()){
                        file1.createNewFile();
                    }
                    FileOutputStream fos = new FileOutputStream(file1);
                    fos.write(string.getBytes());
                    fos.flush();
                    fos.close();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)){
                String path = Environment.getExternalStorageDirectory().getAbsolutePath();
                File file = new File(path + "/trea.json");
                try {
                    FileInputStream fis = new FileInputStream(file);
                    byte[] bytes = new byte[102400];
                    int length = fis.read(bytes);
                    String string = new String(bytes, 0, length);
                    JSONObject object = new JSONObject(string);
                    String sContent = object.optString("sContent");
                    String sPublic = object.optString("sPublic");
                    byte[] deContent = Base64.decode(sContent.getBytes(), Base64.NO_WRAP);
                    byte[] dePublic = Base64.decode(sPublic.getBytes(), Base64.NO_WRAP);
                    X509EncodedKeySpec keySpec = new X509EncodedKeySpec(dePublic);
                    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                    PublicKey publicKey = keyFactory.generatePublic(keySpec);
                    byte[] bytes1 = CryptoUtil.rsaDecrypt(deContent, publicKey);
                    String s = new String(bytes1);
                    edit.setText(s);
                    Toast.makeText(this, "shuju"+s, Toast.LENGTH_SHORT).show();
                    fis.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (InvalidKeySpecException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
