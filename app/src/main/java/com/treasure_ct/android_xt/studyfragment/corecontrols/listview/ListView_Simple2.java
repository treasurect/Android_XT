package com.treasure_ct.android_xt.studyfragment.corecontrols.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.loadingways.asynctask.AsyncTask_DownLoad_ImageTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by treasure on 2016.09.02.
 */

public class ListView_Simple2 extends AppCompatActivity {
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
            map.put("image","https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=293264459,1662135948&fm=111&gp=0.jpg");
            map.put("text","这又一条数据"+i);
            list.add(map);
        }
        adapter = new SimpleAdapter(this,list, R.layout.activity_primarycontrols_listview_item,new String[]{"image","text"},new int[]{R.id.listview_item_image, R.id.listview_item_text});
        adapter.setViewBinder(new SimpleAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object data, String textRepresentation) {
                switch (view.getId()) {
                    case R.id.listview_item_image:
                        ImageView imageView = (ImageView) view;
                        String url = (String) data;
                        new AsyncTask_DownLoad_ImageTask(imageView).execute(url);
                        return true;
                }
                return false;
            }
        });
        listView.setAdapter(adapter);
    }
}

