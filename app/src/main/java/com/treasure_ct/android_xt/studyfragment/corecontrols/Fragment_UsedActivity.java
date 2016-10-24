package com.treasure_ct.android_xt.studyfragment.corecontrols;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.corecontrols.fragment.timedemo.FragmentDemo1;
import com.treasure_ct.android_xt.studyfragment.corecontrols.fragment.weibo_demo.Weibo_Fang_Demo;

public class Fragment_UsedActivity extends FragmentActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corecontrols_fragment_used);
        Button btn1 = (Button) findViewById(R.id.fragmentdemo01);
        Button btn2 = (Button) findViewById(R.id.fragmentdemo02);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragmentdemo01:
                startActivity(new Intent(this, FragmentDemo1.class));
                break;
            case R.id.fragmentdemo02:
                startActivity(new Intent(this, Weibo_Fang_Demo.class));
                break;
        }
    }
}
