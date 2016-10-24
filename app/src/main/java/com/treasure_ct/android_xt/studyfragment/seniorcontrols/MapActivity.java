package com.treasure_ct.android_xt.studyfragment.seniorcontrols;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.seniorcontrols.map.BaiduMap;
import com.treasure_ct.android_xt.studyfragment.seniorcontrols.map.GaodeMap;

public class MapActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seniorcontrols_map);
        Button baidu = (Button) findViewById(R.id.baidu_map);
        Button gaode = (Button) findViewById(R.id.gaode_map);
        baidu.setOnClickListener(this);
        gaode.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.baidu_map:
                startActivity(new Intent(this,BaiduMap.class));
                break;
            case R.id.gaode_map:
                startActivity(new Intent(this, GaodeMap.class));
                break;
        }
    }
}
