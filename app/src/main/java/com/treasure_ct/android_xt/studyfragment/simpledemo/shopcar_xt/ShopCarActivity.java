package com.treasure_ct.android_xt.studyfragment.simpledemo.shopcar_xt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.treasure_ct.android_xt.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShopCarActivity extends AppCompatActivity {
    private ListView listView;
    private SimpleAdapter adapter;
    private ArrayList<Map<String,Object>> list;
    private Map<String,Object> map;
    private boolean list_clear = true;
    private boolean list_edit = true;
    private int list_size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simpledemo_shopcar);
        listView = (ListView) findViewById(R.id.sc_listview);
        list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 5; i++) {
            map = new HashMap<>();
            map.put("check","");
            map.put("image", R.mipmap.lufei);
            map.put("text","请叫我蒙奇·D·路飞，我要成为海贼王");
            list.add(map);
        }
        adapter = new SimpleAdapter(this,list, R.layout.activity_simpledemo_shopcar_listview_item_gone,new String[]{"check","image","text"},new int[]{R.id.sc_checkbox, R.id.sc_image, R.id.sc_text});
        listView.setAdapter(adapter);
        listView.setOnCreateContextMenuListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.simpledemo_shopcar_menu_setting,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                if (!list_clear){
                    list.clear();
                }
                if (list_edit) {
                    map.put("check", "");
                    map.put("image", R.mipmap.lufei);
                    map.put("text", "请叫我蒙奇·D·路飞，我要成为海贼王");
                    list.add(map);
                    adapter = new SimpleAdapter(this, list, R.layout.activity_simpledemo_shopcar_listview_item_gone, new String[]{"check", "image", "text"}, new int[]{R.id.sc_checkbox, R.id.sc_image, R.id.sc_text});
                    listView.setAdapter(adapter);
                    listView.setOnCreateContextMenuListener(this);
                    list_clear = true;
                }else {
                    map.put("check", "");
                    map.put("image", R.mipmap.lufei);
                    map.put("text", "请叫我蒙奇·D·路飞，我要成为海贼王");
                    list.add(map);
                    adapter = new SimpleAdapter(this, list, R.layout.activity_simpledemo_shopcar_listview_item, new String[]{"check", "image", "text"}, new int[]{R.id.sc_checkbox, R.id.sc_image, R.id.sc_text});
                    listView.setAdapter(adapter);
                    listView.setOnCreateContextMenuListener(this);
                    list_clear = true;
                }
                break;
            case R.id.action_edit:
                if (list_edit){
                    list_size = list.size();
                    if (list_size == 0){
                        list_edit = true;
                    }else {
                        list_edit = false;
                    }
                    list.clear();
                    edit_now();
                }else {
                    list_size = list.size();
                    list.clear();
                    edit_pre();
                    list_edit = true;
                }
                break;
            case R.id.action_clear:
                listView.setAdapter(null);
                list.clear();
                list_clear = false;
                list_edit = true;
                break;
        }
        return true;
    }
    public void edit_pre(){
        for (int i = 0; i < list_size; i++) {
            map = new HashMap<>();
            map.put("check","");
            map.put("image", R.mipmap.lufei);
            map.put("text","请叫我蒙奇·D·路飞，我要成为海贼王");
            list.add(map);
        }
        adapter = new SimpleAdapter(this,list, R.layout.activity_simpledemo_shopcar_listview_item_gone,new String[]{"check","image","text"},new int[]{R.id.sc_checkbox, R.id.sc_image, R.id.sc_text});
        listView.setAdapter(adapter);
        listView.setOnCreateContextMenuListener(this);
    }
    public void edit_now(){
        for (int i = 0; i < list_size; i++) {
            map = new HashMap<>();
            map.put("check","");
            map.put("image", R.mipmap.lufei);
            map.put("text","请叫我蒙奇·D·路飞，我要成为海贼王");
            list.add(map);
        }
        adapter = new SimpleAdapter(this,list, R.layout.activity_simpledemo_shopcar_listview_item,new String[]{"check","image","text"},new int[]{R.id.sc_checkbox, R.id.sc_image, R.id.sc_text});
        listView.setAdapter(adapter);
        listView.setOnCreateContextMenuListener(this);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.simpledemo_shopcar_context_item,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.context_delete:
                list.remove(list.get(item.getItemId()));
                list_size = list.size();
                list.clear();
                if (list_edit) {
                    edit_pre();
                }else {
                    edit_now();
                }
                break;
        }
        return true;
    }
}
