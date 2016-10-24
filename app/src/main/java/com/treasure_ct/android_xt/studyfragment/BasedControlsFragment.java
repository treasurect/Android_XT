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
import com.treasure_ct.android_xt.studyfragment.basedcontrols.ButtonActivity;
import com.treasure_ct.android_xt.studyfragment.basedcontrols.EditTextActivity;
import com.treasure_ct.android_xt.studyfragment.basedcontrols.ImageViewActivity;
import com.treasure_ct.android_xt.studyfragment.basedcontrols.LayoutActivity;
import com.treasure_ct.android_xt.studyfragment.basedcontrols.TextViewActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class BasedControlsFragment extends Fragment implements AdapterView.OnItemClickListener {
    private GridView gridView;
    private SimpleAdapter adapter;
    private List<Map<String, Object>> dataList;
    private int[] icon = {R.mipmap.icon_text, R.mipmap.icon_button, R.mipmap.icon_edittext, R.mipmap.icon_image,
            R.mipmap.icon_layout};
    private String[] iconName = {"TextView", "Button", "EditText", "ImageView",
            "常用布局"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_based_controls, container, false);
        gridView = (GridView) view.findViewById(R.id.base_gridView);
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
            case "TextView":
                startActivity(new Intent().setClass(getContext(), TextViewActivity.class));
                break;
            case "Button":
                startActivity(new Intent().setClass(getContext(), ButtonActivity.class));
                break;
            case "EditText":
                startActivity(new Intent().setClass(getContext(), EditTextActivity.class));
                break;
            case "ImageView":
                startActivity(new Intent().setClass(getContext(), ImageViewActivity.class));
                break;
            case "常用布局":
                startActivity(new Intent().setClass(getContext(), LayoutActivity.class));
                break;
        }
    }
}
