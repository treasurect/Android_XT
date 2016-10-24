package com.treasure_ct.android_xt.studyfragment.seniorcontrols;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.seniorcontrols.camera.CameraPhoto;
import com.treasure_ct.android_xt.studyfragment.seniorcontrols.camera.CameraSurfaceView;
import com.treasure_ct.zxing.decoding.Intents;

public class CameraActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seniorcontrols_camera_photo);
        Button photo = (Button) findViewById(R.id.btnCameraPhoto);
        Button surface = (Button) findViewById(R.id.btnCameraSurface);
        Button btnQRcode = (Button) findViewById(R.id.btnQRcode);
        text = (TextView) findViewById(R.id.text123);
        photo.setOnClickListener(this);
        surface.setOnClickListener(this);
        btnQRcode.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCameraPhoto:
                startActivity(new Intent(this, CameraPhoto.class));
                break;
            case R.id.btnCameraSurface:
                startActivity(new Intent(this, CameraSurfaceView.class ));
                break;
            case R.id.btnQRcode:
                Intent intent = new Intent(Intents.Scan.ACTION);
                startActivityForResult(intent,123);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode== 123){
            if (data != null){
                String result = data.getStringExtra("result");
                Toast.makeText(this, ""+result, Toast.LENGTH_SHORT).show();
                if (text != null){
                    text.setText(result+"");
                }
            }
        }
    }
}
