package com.treasure_ct.android_xt.studyfragment.storage;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.treasure_ct.android_xt.R;

import java.io.File;

public class File_SDCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_file__sdcard);

        testSdcard();
    }

    private void testSdcard() {
        //1.检测当前手机是否包含存储卡，
        //  所有外部存储的操作都需要在清单文件中申明权限
        String state = Environment.getExternalStorageState();
        // 根据状态判断是否有外部存储
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            //外部存储已经挂载，可以访问和使用
            //2.获取外部存储的根目录
            File directory = Environment.getExternalStorageDirectory();
            Log.d("ExternalStorage",directory.getAbsolutePath());

            //3.获取外部存储，公共目录
            File dcimDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
            if (dcimDir.exists()) {
                //TODO:遍历所有的文件，打印出来
                File[] files = dcimDir.listFiles();
                if (files != null) {
                    for (File file : files) {
                        Log.d("ExternalActivity", "file" + file);
                    }
                }
            }
            //4.外部存储可以获取 应用程序特定的一些目录，类似于内部存储的路径、
            //    /外部根目录/android/data/包名
            //使用上下文来获取
            Log.d("====================", "testSdcard: "+getExternalCacheDir().getAbsolutePath());
            //获取外部存储区中的，应用程序自身 files 目录内部的文件夹
            //如果传参为null，直接返回files目录
            //否则  filesxx 目录
            Log.d("---------------", "testSdcard: "+getExternalFilesDir("images").getAbsolutePath());
        }
    }
}
