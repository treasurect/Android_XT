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
import com.treasure_ct.android_xt.studyfragment.simpledemo.calculator_xt.CalculatorActivity;
import com.treasure_ct.android_xt.studyfragment.simpledemo.contact_backup.BackupActivity;
import com.treasure_ct.android_xt.studyfragment.simpledemo.explorer_xt.ExplorerActivity;
import com.treasure_ct.android_xt.studyfragment.simpledemo.file_explorer_xt.LoadingActivity;
import com.treasure_ct.android_xt.studyfragment.simpledemo.keymanager_xt.KeyManagerActivity;
import com.treasure_ct.android_xt.studyfragment.simpledemo.movielist_xt.MovieListActivity;
import com.treasure_ct.android_xt.studyfragment.simpledemo.netease_xt.NeteaseActivity;
import com.treasure_ct.android_xt.studyfragment.simpledemo.newstoday_xt.NewsTodayActivity;
import com.treasure_ct.android_xt.studyfragment.simpledemo.shopcar_xt.ShopCarActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class SimpleDemoFragment extends Fragment implements AdapterView.OnItemClickListener {
    private GridView gridView;
    private SimpleAdapter adapter;
    private List<Map<String,Object>> dataList;
    private int[] icon = {R.mipmap.icon_calculator, R.mipmap.icon_explorer, R.mipmap.icon_netease, R.mipmap.icon_movielist, R.mipmap.icon_shopcar, R.mipmap.icon_fileexplorer, R.mipmap.icon_keymanager, R.mipmap.icon_main12, R.mipmap.icon_main1};
    private String[] iconName ={"计算器","浏览器","网易新闻","电影列表","购物车","文件管理","密钥管理","今日头条","联系人备份"} ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_simple_demo, container, false);
        gridView = (GridView)view.findViewById(R.id.sdemo_gridview);
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
            case "计算器":
                startActivity(new Intent().setClass(getContext(),CalculatorActivity.class));
                break;
            case "浏览器":
                startActivity(new Intent().setClass(getContext(),ExplorerActivity.class));
                break;
            case "网易新闻":
                startActivity(new Intent().setClass(getContext(),NeteaseActivity.class));
                break;
            case "电影列表":
                startActivity(new Intent().setClass(getContext(),MovieListActivity.class));
                break;
            case "购物车":
                startActivity(new Intent().setClass(getContext(),ShopCarActivity.class));
                break;
            case "文件管理":
                startActivity(new Intent().setClass(getContext(),LoadingActivity.class));
                break;
            case "密钥管理":
                startActivity(new Intent().setClass(getContext(),KeyManagerActivity.class));
                break;
            case "今日头条":
                startActivity(new Intent().setClass(getContext(),NewsTodayActivity.class));
                break;
            case "联系人备份":
                startActivity(new Intent().setClass(getContext(),BackupActivity.class));
                break;
        }
    }
}
