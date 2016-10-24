package com.treasure_ct.android_xt.studyfragment.components.service.downloadfile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.treasure_ct.android_xt.R;

public class DownloadFileActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_components_service_downloadfile);
        Button download = (Button) findViewById(R.id.btn_download);
        download.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_download:
                EditText input = (EditText) findViewById(R.id.input_url);
                Intent intent = new Intent(this, DownloadfileIntentService.class);
                intent.putExtra("url",input.getText().toString());
                startService(intent);
                break;
        }
    }
}
