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
import com.treasure_ct.android_xt.studyfragment.basedfunction.ActionBarActivity;
import com.treasure_ct.android_xt.studyfragment.basedfunction.MenuActivity;
import com.treasure_ct.android_xt.studyfragment.basedfunction.NotificationActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class BasedFunctionFragment extends Fragment implements AdapterView.OnItemClickListener {
    private GridView gridView;
    private SimpleAdapter adapter;
    private List<Map<String, Object>> dataList;
    private int[] icon = {R.mipmap.icon_menu, R.mipmap.icon_notification, R.mipmap.icon_titlebar};
    private String[] iconName = {"Menu", "通知","标题栏"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_based_function, container, false);
        gridView = (GridView) view.findViewById(R.id.base_gridView_func);
        dataList = new ArrayList<Map<String, Object>>();
        adapter = new SimpleAdapter(getContext(), getData(), R.layout.item_gridview_bj, new String[]{"image", "text"}, new int[]{R.id.image, R.id.text});
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);
        return view;
    }
    private List<Map<String, Object>> getData() {
        for (int i = 0; i < icon.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            dataList.add(map);
        }
        return dataList;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (iconName[i]) {
            case "Menu":
                startActivity(new Intent().setClass(getContext(), MenuActivity.class));
                break;
            case "通知":
                startActivity(new Intent().setClass(getContext(), NotificationActivity.class));
                break;
            case "标题栏":
                startActivity(new Intent(getContext(), ActionBarActivity.class));
                break;
        }
    }
}
