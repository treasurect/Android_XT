package com.treasure_ct.android_xt.studyfragment.seniorcontrols.eventdistribution;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.treasure_ct.android_xt.R;

import java.util.ArrayList;

public class Scroll_ListView_Finish1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seniorcontrols_eventdestribution_scroll_listview_finish1);
        ListView listView = (ListView) findViewById(R.id.sl2_listview);
        ArrayList<String>list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("制定"+i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
//        setListViewHeight(listView);
    }
    public static void setListViewHeight(ListView listView){
        if (listView == null){
            return;
        }
        ListAdapter adapter = listView.getAdapter();
        if (adapter == null) {
            return;
        }
        int Height = 0;
        /**
         * 内容的长度
         */
        for (int i = 0; i < adapter.getCount(); i++) {
            View adapterView = adapter.getView(i, null, listView);
            adapterView.measure(0,0);
            Height += adapterView.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        /**
         * (listView.getDividerHeight()*(adapter.getCount() - 1)
         * ListView各个item的间隙长度
         */
        params.height = Height + (listView.getDividerHeight()*(adapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}
