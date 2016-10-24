package com.treasure_ct.android_xt.studyfragment.corecontrols.viewpager.tabhost;

import android.app.TabActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;
import android.widget.Toast;

import com.treasure_ct.android_xt.R;

public class TabHostActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TabHost host = getTabHost();
        LayoutInflater.from(this).inflate(R.layout.activity_corecontrols_tabhost,host.getTabContentView(),true);
        host.addTab(host.newTabSpec("one").setIndicator("tab1").setContent(R.id.tab1));
        host.addTab(host.newTabSpec("two").setIndicator("tab2").setContent(R.id.tab1));
        host.addTab(host.newTabSpec("three").setIndicator("tab3").setContent(R.id.tab1));
        host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                Toast.makeText(TabHostActivity.this, tabId, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
