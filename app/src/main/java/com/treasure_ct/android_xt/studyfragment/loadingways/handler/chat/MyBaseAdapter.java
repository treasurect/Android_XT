package com.treasure_ct.android_xt.studyfragment.loadingways.handler.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.treasure_ct.android_xt.R;

import java.util.List;

/**
 * Created by treasure on 2016.09.18.
 */
public class MyBaseAdapter extends BaseAdapter{
    private Context context;
    private List<Entry> list;

    public MyBaseAdapter(Context context, List<Entry> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        int ret = 0;
        Entry entry = list.get(position);
        if (entry.isReceived()) {
            ret = 0;
        }else {
            ret = 1;
        }
        return ret;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View ret = null;
        int type = getItemViewType(position);
        switch (type) {
            case 0:
                ret = getLeftView(position,convertView,parent);
                break;
            case 1:
                ret = getRightView(position,convertView,parent);
                break;
        }
        return ret;
    }
    private View getLeftView(int position, View convertView, ViewGroup parent){
        View ret = null;
        if (convertView != null){
            ret = convertView;
        }else {
            ret = LayoutInflater.from(context).inflate(R.layout.chat_user_left_item,parent,false);
        }
        ViewHolder holder = (ViewHolder) ret.getTag();
        if (holder == null){
            holder = new ViewHolder(ret);
            ret.setTag(holder);
        }
        holder.bindView(position,list.get(position));
        return ret;
    }
    private View getRightView(int position, View convertView, ViewGroup parent){
        View ret = null;
        if (convertView != null){
            ret = convertView;
        }else {
            ret = LayoutInflater.from(context).inflate(R.layout.chat_user_right_item,parent,false);
        }
        ViewHolder holder = (ViewHolder) ret.getTag();
        if (holder == null){
            holder = new ViewHolder(ret);
            ret.setTag(holder);
        }
        holder.bindView(position,list.get(position));
        return ret;
    }
    private static class ViewHolder{
        private TextView name,text;
        private ImageView icon;
        public ViewHolder(View view) {
             name = (TextView) view.findViewById(R.id.userName);
             text = (TextView) view.findViewById(R.id.userText);
             icon = (ImageView) view.findViewById(R.id.userIcon);
        }
        public void bindView(int position,Entry entry){
            name.setText(entry.getUserName());
            text.setText(entry.getText());
        }
    }
}
