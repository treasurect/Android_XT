package com.treasure_ct.android_xt.studyfragment.seniorcontrols.camera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.treasure_ct.android_xt.R;

import java.io.File;

public class CameraPhoto extends AppCompatActivity implements View.OnClickListener {

    private File file;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seniorcontrols_camera_main);
        Button camare1 = (Button) findViewById(R.id.camare_btn);
        Button camare2 = (Button) findViewById(R.id.camare_btn2);
         imageView = (ImageView) findViewById(R.id.camare_image);
        camare1.setOnClickListener(this);
        camare2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.camare_btn:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,666);
                break;
            case R.id.camare_btn2:
                Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //使用 EXTRA_OUTPUT 指定Uri 位置，可以把原尺寸大图保存到Uri 指定位置
                String state = Environment.getExternalStorageState();
                File dir = getFilesDir();
                if (state.equals(Environment.MEDIA_MOUNTED)){
                    dir = Environment.getExternalStorageDirectory();
                }
                if (!dir.exists()){
                    dir.mkdir();
                }
                file = new File(dir,"img-"+ System.currentTimeMillis()+".jpg");
                Uri uri = Uri.fromFile(file);
                intent1.putExtra(MediaStore.EXTRA_OUTPUT,uri);
                startActivityForResult(intent1,999);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 666) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    Bitmap data1 = data.getParcelableExtra("data");
                    imageView.setImageBitmap(data1);
                }
            }
        } else if (requestCode == 999){
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "照片保存成功", Toast.LENGTH_SHORT).show();
                if (file.exists()) {
//                    //案例一：使用 options参数，直接设置inSampleSize
//                    BitmapFactory.Options options = new BitmapFactory.Options();
//                    //inSampleSize
//                    options.inSampleSize = 2;
////                    options.inPurgeable
//                    options.inPreferredConfig = Bitmap.Config.RGB_565;
//                    //加载推图片，显示（！！！）
//                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(),options);
                    //使用图片的内存亚索来进行图片的加载
                    Bitmap bitmap = BitmapUtil.loadBitmapWithScale(file, 100, 100);
                    imageView.setImageBitmap(bitmap);
                }
            }
        }
    }
}
