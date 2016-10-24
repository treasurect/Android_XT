package com.treasure_ct.android_xt.studyfragment.storage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.network_xt.ImageTask;

import java.io.File;

public class FileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_file);
        ImageView imageView = (ImageView) findViewById(R.id.in_file_image);
        ImageTask imageTask = new ImageTask(imageView);
        imageTask.execute("https://www.baidu.com/img/bd_logo1.png");
        File dir = getDir("images", MODE_PRIVATE);
        Toast.makeText(this, dir.getAbsolutePath(), Toast.LENGTH_SHORT).show();
    }
}
