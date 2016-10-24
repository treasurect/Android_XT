package com.treasure_ct.android_xt.studyfragment.corecontrols.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.treasure_ct.android_xt.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by treasure on 2016.09.02.
 */

public class ListView_Array extends AppCompatActivity {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primarycontrols_listview_array);
        listView = (ListView) findViewById(R.id.arraylistview);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(String.format(Locale.CHINA,"Item %03d",i + 1));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
    }
}

