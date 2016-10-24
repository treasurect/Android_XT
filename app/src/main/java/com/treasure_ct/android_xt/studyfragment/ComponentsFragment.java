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
import com.treasure_ct.android_xt.studyfragment.components.ActivityActivity;
import com.treasure_ct.android_xt.studyfragment.components.BroadcastRecriverActivity;
import com.treasure_ct.android_xt.studyfragment.components.ContentProviderActivity;
import com.treasure_ct.android_xt.studyfragment.components.ServiceActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComponentsFragment extends Fragment implements AdapterView.OnItemClickListener {
    private GridView gridView;
    private SimpleAdapter adapter;
    private List<Map<String,Object>> dataList;
    private int[] icon = {R.mipmap.icon_main6, R.mipmap.icon_main1, R.mipmap.icon_main11, R.mipmap.icon_main7};
    private String[] iconName ={"Activity","Service","广播接收者","内容提供者"} ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_components, container, false);
        gridView = (GridView)view.findViewById(R.id.components_girdView);
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
            case "Activity":
                startActivity(new Intent().setClass(getContext(),ActivityActivity.class));
                break;
            case "Service":
                startActivity(new Intent().setClass(getContext(),ServiceActivity.class));
                break;
            case "广播接收者":
                startActivity(new Intent().setClass(getContext(),BroadcastRecriverActivity.class));
                break;
            case "内容提供者":
                startActivity(new Intent().setClass(getContext(),ContentProviderActivity.class));
                break;
        }
    }
}
