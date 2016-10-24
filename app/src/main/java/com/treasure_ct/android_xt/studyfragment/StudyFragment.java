package com.treasure_ct.android_xt.studyfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.treasure_ct.android_xt.R;

import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudyFragment extends Fragment implements AdapterView.OnItemClickListener {
    private ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_study, container, false);
         listView = (ListView) view.findViewById(R.id.study_listView);
        List<String> list = Arrays.asList("导读","基础控件","简单控件","核心控件","高级控件",
                                        "四大组件","基础功能","加载方式","存储方面","简单应用");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                                                            android.R.layout.simple_list_item_1,
                                                            list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.study_frame,new DirectionFragment());
        transaction.commit();
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        switch (position){
            case 0:
                transaction.replace(R.id.study_frame,new DirectionFragment());
                break;
            case 1:
                transaction.replace(R.id.study_frame,new BasedControlsFragment());
                break;
            case 2:
                transaction.replace(R.id.study_frame,new SimpleControlsFragment());
                break;
            case 3:
                transaction.replace(R.id.study_frame,new CoreControlsFragment());
                break;
            case 4:
                transaction.replace(R.id.study_frame,new SeniorControlsFragment());
                break;
            case 5:
                transaction.replace(R.id.study_frame,new ComponentsFragment());
                break;
            case 6:
                transaction.replace(R.id.study_frame,new BasedFunctionFragment());
                break;
            case 7:
                transaction.replace(R.id.study_frame,new LoadingWaysFragment());
                break;
            case 8:
                transaction.replace(R.id.study_frame,new StorageFragment());
                break;
            case 9:
                transaction.replace(R.id.study_frame,new SimpleDemoFragment());
                break;
        }
        transaction.commit();
    }
}
