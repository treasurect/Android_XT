package com.treasure_ct.android_xt.studyfragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.storage.FileActivity;
import com.treasure_ct.android_xt.studyfragment.storage.File_SDCardActivity;
import com.treasure_ct.android_xt.studyfragment.storage.SQLiteActivity;
import com.treasure_ct.android_xt.studyfragment.storage.SharedPreferencesActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class StorageFragment extends Fragment implements AdapterView.OnItemClickListener {
    private GridView gridView;
    private SimpleAdapter adapter;
    private List<Map<String,Object>> dataList;
    private int[] icon = {R.mipmap.icon_storage1, R.mipmap.icon_storage2, R.mipmap.icon_storage3, R.mipmap.icon_storage4};
    private String[] iconName ={"SharedPreferences","文件","文件外部存储","SQLite"} ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_storage, container, false);
        gridView = (GridView)view.findViewById(R.id.storage_gridView_func);
        dataList = new ArrayList<Map<String, Object>>();
        adapter = new SimpleAdapter(getContext(),getData(), R.layout.item_gridview_bj,new String[]{"image","text"},new int[]{R.id.image, R.id.text});
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);
        return view;
    }
    private List<Map<String,Object>> getData() {
        for (int i = 0; i < icon.length; i++) {
            Map<String,Object>map = new HashMap<String, Object>();
            map.put("image",icon[i]);
            map.put("text",iconName[i]);
            dataList.add(map);
        }
        return dataList;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (iconName[i]){
            case "SharedPreferences":
                startActivity(new Intent().setClass(getContext(),SharedPreferencesActivity.class));
                break;
            case "文件":
                startActivity(new Intent().setClass(getContext(),FileActivity.class));
                break;
            case "文件外部存储":
                startActivity(new Intent().setClass(getContext(),File_SDCardActivity.class));
                break;
            case "SQLite":
                startActivity(new Intent().setClass(getContext(),SQLiteActivity.class));
                break;
        }
    }
}
