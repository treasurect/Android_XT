package com.treasure_ct.android_xt.studyfragment.components;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.components.contentprovider.CallRecordsActivity;
import com.treasure_ct.android_xt.studyfragment.components.contentprovider.AddressListActivity;

/**
 * 使用：
 * 1） ContentResolver,类似Socket的客户端
 * 2） 需要提供Uri对象，可以看做网址，Android提供很多常量类
 * 3)访问Android内部的内容提供者，需要声明权限
 * 4)API23以上，所有内容提供者都需要动态权限检查，否者执行不了
 */
public class ContentProviderActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn1,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_components_contentprovider);
        btn1 = (Button) findViewById(R.id.call_records_btn);
        btn2 = (Button) findViewById(R.id.addresslist_btn);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.call_records_btn:
                startActivity(new Intent(this, CallRecordsActivity.class));
                break;
            case R.id.addresslist_btn:
                startActivity(new Intent(this, AddressListActivity.class));
                break;
        }
    }
}