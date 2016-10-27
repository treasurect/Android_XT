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
import com.treasure_ct.android_xt.studyfragment.studyadvance.retrofit.RetrofitActivity;
import com.treasure_ct.android_xt.studyfragment.studyadvance.rxjava.RXJavaActivity;
import com.treasure_ct.android_xt.studyfragment.studyadvance.rxjava.RXJavaSimpleUseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudyAdvanceFragment extends Fragment implements AdapterView.OnItemClickListener {
    private GridView gridView;
    private SimpleAdapter adapter;
    private List<Map<String,Object>> dataList;
    private int[] icon = {R.mipmap.icon_main1, R.mipmap.icon_main2};
    private String[] iconName ={"Retrofit","RxJava"} ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_study_advance, container, false);
        gridView = (GridView)view.findViewById(R.id.sadvance_girdView);
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
            case "Retrofit":
                startActivity(new Intent().setClass(getContext(),RetrofitActivity.class));
                break;
            case "RxJava":
                startActivity(new Intent().setClass(getContext(),RXJavaActivity.class));
                break;
        }
    }
}
