package com.treasure_ct.android_xt.studyfragment.corecontrols.sliding.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.treasure_ct.android_xt.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SlidingMenuLeftFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sliding_left, container, false);
        if (view != null) {
            ListView listView = (ListView) view.findViewById(R.id.slidingmenu_left_listview);
            List<String> list = Arrays.asList("聊天", "发现", "通讯录", "朋友圈", "订阅号");
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, list);
            listView.setAdapter(adapter);
        }
        return view;
    }


}
