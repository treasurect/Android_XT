package com.treasure_ct.android_xt.studyfragment.simpledemo.newstoday_xt;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.treasure_ct.android_xt.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsListFragment extends Fragment implements AdapterView.OnItemClickListener {
    /**
     * Fragment 定义的接口，用于给Activity传递一个ListView点击事件调用
     */
    public interface onNewsSelectedListener{
        void onNewsSelected(Bundle bundle);
    }
    private onNewsSelectedListener mListener;
    public NewsListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //内部设置，接口回调
        if (context instanceof onNewsSelectedListener) {
            mListener = (onNewsSelectedListener)context;
        }else {
            throw new IllegalArgumentException("Activity must implements onNewsSelectListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.activity_simpledemo_fragment_newslist, container, false);
        ListView listView = (ListView) view.findViewById(R.id.newslist_list);
        if (listView != null){
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                list.add("News"+i);
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,list);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(this);
        }
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mListener != null){
            Bundle bundle = new Bundle();
            bundle.putInt("position",position);
            bundle.putLong("id",id);
            mListener.onNewsSelected(bundle);
        }
    }
}
