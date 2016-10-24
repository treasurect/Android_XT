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
import com.treasure_ct.android_xt.studyfragment.seniorcontrols.CameraActivity;
import com.treasure_ct.android_xt.studyfragment.seniorcontrols.CryptoActivity;
import com.treasure_ct.android_xt.studyfragment.seniorcontrols.CustomViewActivity;
import com.treasure_ct.android_xt.studyfragment.seniorcontrols.EventDestributionActivity;
import com.treasure_ct.android_xt.studyfragment.seniorcontrols.MapActivity;
import com.treasure_ct.android_xt.studyfragment.seniorcontrols.ModelAdaptationActivity;
import com.treasure_ct.android_xt.studyfragment.seniorcontrols.SensorActivty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class SeniorControlsFragment extends Fragment implements AdapterView.OnItemClickListener {
    private GridView gridView;
    private SimpleAdapter adapter;
    private List<Map<String,Object>> dataList;
    private int[] icon = {R.mipmap.icon_main6, R.mipmap.icon_main1, R.mipmap.icon_main4,R.mipmap.icon_main5,R.mipmap.icon_main3,R.mipmap.icon_main12,R.mipmap.icon_main15};
    private String[] iconName ={"地图","事件分发","自定义View","机型适配","Camera","传感器","编码加密"} ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_senior_controls, container, false);
        gridView = (GridView)view.findViewById(R.id.senior_girdView);
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
            case "地图":
                startActivity(new Intent().setClass(getContext(),MapActivity.class));
                break;
            case "事件分发":
                startActivity(new Intent().setClass(getContext(),EventDestributionActivity.class));
                break;
            case "自定义View":
                startActivity(new Intent().setClass(getContext(),CustomViewActivity.class));
                break;
            case "机型适配":
                startActivity(new Intent().setClass(getContext(),ModelAdaptationActivity.class));
                break;
            case "Camera":
                startActivity(new Intent().setClass(getContext(),CameraActivity.class));
                break;
            case "传感器":
                startActivity(new Intent().setClass(getContext(),SensorActivty.class));
                break;
            case "编码加密":
                startActivity(new Intent().setClass(getContext(),CryptoActivity.class));
                break;
        }
    }
}
