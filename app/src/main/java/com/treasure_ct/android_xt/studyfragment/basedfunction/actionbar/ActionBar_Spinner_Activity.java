package com.treasure_ct.android_xt.studyfragment.basedfunction.actionbar;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.treasure_ct.android_xt.R;

import java.util.ArrayList;

public class ActionBar_Spinner_Activity extends AppCompatActivity implements ActionBar.OnNavigationListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basedfunction_actionbar_spinner);

        // 1. getSupportActionBar()

        // 2. 设置导航模式为列表

        // 3. 设置下拉列表导航的 Adapter

        // 4. 列表选中接口回调

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
            actionBar.setDisplayShowTitleEnabled(false);//去掉标题栏的文字
            ArrayList<String> list = new ArrayList<>();
            list.add("按天");
            list.add("按周");
            list.add("按月");
            list.add("按年");
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,list);
            actionBar.setListNavigationCallbacks(adapter,this);
        }
    }

    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        boolean ret = true;
        Toast.makeText(ActionBar_Spinner_Activity.this, "选中"+itemPosition, Toast.LENGTH_SHORT).show();
        return ret;
    }
}
