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
import com.treasure_ct.android_xt.studyfragment.corecontrols.AnimationActivity;
import com.treasure_ct.android_xt.studyfragment.corecontrols.Fragment_UsedActivity;
import com.treasure_ct.android_xt.studyfragment.corecontrols.ListViewActivity;
import com.treasure_ct.android_xt.studyfragment.corecontrols.PopUpWindowActivity;
import com.treasure_ct.android_xt.studyfragment.corecontrols.RecyclerViewActivity;
import com.treasure_ct.android_xt.studyfragment.corecontrols.SlidingViewActivtiy;
import com.treasure_ct.android_xt.studyfragment.corecontrols.ViewPagerActivity;
import com.treasure_ct.android_xt.studyfragment.corecontrols.WebViewActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class CoreControlsFragment extends Fragment implements AdapterView.OnItemClickListener {
    private GridView gridView;
    private SimpleAdapter adapter;
    private List<Map<String,Object>> dataList;
    private int[] icon = {R.mipmap.icon_main6, R.mipmap.icon_main1, R.mipmap.icon_main11,
            R.mipmap.icon_main7,R.mipmap.icon_main5,R.mipmap.icon_main12,R.mipmap.icon_main6,R.mipmap.icon_main8};
    private String[] iconName ={"动画","Fragment","ViewPager","ListView","WebView","RecyclerView","侧滑","PopupWindow"} ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_core_controls, container, false);
        gridView = (GridView)view.findViewById(R.id.core_components_girdView);
        dataList = new ArrayList<>();
        adapter = new SimpleAdapter(getContext(),getData(), R.layout.item_gridview_bj,new String[]{"image","text"},new int[]{R.id.image, R.id.text});
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);
        return view;
    }
    private List<Map<String,Object>> getData() {
        for (int i = 0; i < icon.length; i++) {
            Map<String,Object>map = new HashMap<>();
            map.put("image",icon[i]);
            map.put("text",iconName[i]);
            dataList.add(map);
        }
        return dataList;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (iconName[i]){
            case "动画":
                startActivity(new Intent().setClass(getContext(),AnimationActivity.class));
                break;
            case "Fragment":
                startActivity(new Intent().setClass(getContext(),Fragment_UsedActivity.class));
                break;
            case "ViewPager":
                startActivity(new Intent().setClass(getContext(),ViewPagerActivity.class));
                break;
            case "ListView":
                startActivity(new Intent().setClass(getContext(),ListViewActivity.class));
                break;
            case "WebView":
                startActivity(new Intent().setClass(getContext(),WebViewActivity.class));
                break;
            case "RecyclerView":
                startActivity(new Intent().setClass(getContext(),RecyclerViewActivity.class));
                break;
            case "侧滑":
                startActivity(new Intent().setClass(getContext(),SlidingViewActivtiy.class));
                break;
            case "PopupWindow":
                startActivity(new Intent().setClass(getContext(),PopUpWindowActivity.class));
                break;
        }
    }

}
