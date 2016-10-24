package com.treasure_ct.android_xt.studyfragment.corecontrols.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.treasure_ct.android_xt.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by treasure on 2016.09.02.
 */

public class ListView_Simple1 extends AppCompatActivity {
    private ListView listView;
    private SimpleAdapter adapter;
    private List<Map<String,Object>> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primarydcontrols_listview_sim1);
        listView = (ListView) findViewById(R.id.sim1listview);
        list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 100; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("image", R.mipmap.lufei);
            map.put("text","这又一条数据"+i);
            list.add(map);
        }
        adapter = new SimpleAdapter(this,list, R.layout.activity_primarycontrols_listview_item,new String[]{"image","text"},new int[]{R.id.listview_item_image, R.id.listview_item_text});
        listView.setAdapter(adapter);
    }
}

