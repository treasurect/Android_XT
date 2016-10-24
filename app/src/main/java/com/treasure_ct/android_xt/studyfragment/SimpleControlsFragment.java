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
import com.treasure_ct.android_xt.studyfragment.simplecontrols.AlertDialogActivity;
import com.treasure_ct.android_xt.studyfragment.simplecontrols.AutoCompleteActivity;
import com.treasure_ct.android_xt.studyfragment.simplecontrols.CheckBoxActivity;
import com.treasure_ct.android_xt.studyfragment.simplecontrols.RadioButtonActivity;
import com.treasure_ct.android_xt.studyfragment.simplecontrols.ScrollViewActivity;
import com.treasure_ct.android_xt.studyfragment.simplecontrols.SpinnerActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class SimpleControlsFragment extends Fragment implements AdapterView.OnItemClickListener {
    private GridView gridView;
    private SimpleAdapter adapter;
    private List<Map<String,Object>> dataList;
    private int[] icon = {R.mipmap.icon_checkbox, R.mipmap.icon_radio, R.mipmap.icon_spinner, R.mipmap.icon_auto, R.mipmap.icon_scroll, R.mipmap.icon_main1};
    private String[] iconName ={"CheckBox","RadioButton","Spinner","AutoComplete","ScrollView","Dialog"} ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_simple_controls, container, false);
        gridView = (GridView)view.findViewById(R.id.sc_girdView);
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
            case "CheckBox":
                startActivity(new Intent().setClass(getContext(),CheckBoxActivity.class));
                break;
            case "RadioButton":
                startActivity(new Intent().setClass(getContext(),RadioButtonActivity.class));
                break;
            case "Spinner":
                startActivity(new Intent().setClass(getContext(),SpinnerActivity.class));
                break;
            case "AutoComplete":
                startActivity(new Intent().setClass(getContext(),AutoCompleteActivity.class));
                break;
            case "ScrollView":
                startActivity(new Intent().setClass(getContext(),ScrollViewActivity.class));
                break;
            case "Dialog":
                startActivity(new Intent().setClass(getContext(),AlertDialogActivity.class));
                break;
        }
    }
}
