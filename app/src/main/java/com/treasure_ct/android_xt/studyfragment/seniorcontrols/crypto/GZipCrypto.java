package com.treasure_ct.android_xt.studyfragment.seniorcontrols.crypto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.treasure_ct.android_xt.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZipCrypto extends AppCompatActivity {

    private EditText edit;
    private TextView text;
    private byte[] bytes = null;
    private TextView gzip_text_re;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seniorcontrols_gzip_crypto);
        edit = (EditText) findViewById(R.id.gzip_edit);
        text = (TextView) findViewById(R.id.gzip_text);
        gzip_text_re = (TextView) findViewById(R.id.gzip_text_re);
    }

    public void btnGZip(View view) {
        String trim = edit.getText().toString().trim();
        //压缩GZIP，GZIPOutputStream
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        try {
            GZIPOutputStream gzout = new GZIPOutputStream(bout);
            gzout.write(trim.getBytes());//利用GZIPOutputStream压缩，并输出结果
            gzout.finish();//必须调用，在结束时，生成实际的压缩数据,并flush
            gzout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
         bytes = bout.toByteArray();
        String s = Arrays.toString(bytes);
        text.setText("原始内容长度:"+trim.length()+"\n压缩后内容长度： "+bytes.length
        +"\n\n压缩后的内容:\n"+s);
    }

    public void btnGZipRe(View view) {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        try {
            GZIPInputStream gzis = new GZIPInputStream(bis);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] bytes = new byte[128];
            int length;
            while (true){
                length = gzis.read(bytes);
                if (length == -1){
                    break;
                }
                bos.write(bytes,0,length);
            }
            byte[] bytes1 = bos.toByteArray();
            String s = new String(bytes1);
            gzip_text_re.setText(s);
            bis.close();
            gzis.close();
            bos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
