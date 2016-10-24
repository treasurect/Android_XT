package com.treasure_ct.android_xt.studyfragment.corecontrols.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.treasure_ct.android_xt.R;

import java.util.List;

/**
 * Created by treasure on 2016.10.05.
 */

public class MyCyclerViewGridAdapter extends RecyclerView.Adapter<MyCyclerViewGridAdapter.MyViewHolder>{
    private Context mContext;
    private List<String> mList;
    private LayoutInflater mInflater;

    public MyCyclerViewGridAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
        mInflater = LayoutInflater.from(mContext);
    }
    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyCyclerViewGridAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.activity_corecontrols_recyclerview_grid_item, parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }
    //填充onCreateViewHolder方法返回的holder中的控件
    @Override
    public void onBindViewHolder(MyCyclerViewGridAdapter.MyViewHolder holder, int position) {
        holder.textView.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.recyclerview_item_title2);
        }
    }
}
