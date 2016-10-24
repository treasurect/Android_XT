package com.treasure_ct.android_xt.studyfragment.corecontrols;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.corecontrols.viewpager.tabhost.TabHostActivity;
import com.treasure_ct.android_xt.studyfragment.corecontrols.viewpager.tablayout.TabLayoutActivity;
import com.treasure_ct.android_xt.studyfragment.corecontrols.viewpager.loading.ViewPager_LoadingPage;
import com.treasure_ct.android_xt.studyfragment.corecontrols.viewpager.mainpager.ViewPager_MainPager;
import com.treasure_ct.android_xt.studyfragment.corecontrols.viewpager.tablayout.TabLayout2Activity;

public class ViewPagerActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corecontrols_viewpager);
        Button btn1 = (Button) findViewById(R.id.btn_viewpager1);
        Button btn2 = (Button) findViewById(R.id.btn_viewpager2);
        Button btn3 = (Button) findViewById(R.id.btn_viewpager3);
        Button btn4 = (Button) findViewById(R.id.btn_viewpager4);
        Button btn5 = (Button) findViewById(R.id.btn_tabhost);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_viewpager1:
                startActivity(new Intent(this, ViewPager_LoadingPage.class));
                break;
            case R.id.btn_viewpager2:
                startActivity(new Intent(this, ViewPager_MainPager.class));
                break;
            case R.id.btn_viewpager3:
                startActivity(new Intent(this, TabLayoutActivity.class));
                break;
            case R.id.btn_viewpager4:
                startActivity(new Intent(this, TabLayout2Activity.class));
                break;
            case R.id.btn_tabhost:
                startActivity(new Intent(this, TabHostActivity.class));
                break;
        }
    }
}
