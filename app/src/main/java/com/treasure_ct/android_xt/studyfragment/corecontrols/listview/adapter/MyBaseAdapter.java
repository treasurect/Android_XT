package com.treasure_ct.android_xt.studyfragment.corecontrols.listview.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.loadingways.asynctask.AsyncTask_DownLoad_ImageTask;
import com.treasure_ct.android_xt.studyfragment.corecontrols.listview.model.Base_Entry;

import java.util.Collection;
import java.util.List;

/**
 * Created by treasure on 2016.09.02.
 */
public class MyBaseAdapter extends BaseAdapter{
    private static final String TAG = MyBaseAdapter.class.getSimpleName();

    private Context context;
    private List<Base_Entry> list;
    private LayoutInflater inflater;
    public MyBaseAdapter(Context context, List<Base_Entry> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
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
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(TAG, "getView: "+convertView);
        if (convertView == null){
            convertView = inflater.inflate(R.layout.activity_primarycontrols_listview_item,parent,false);
            convertView.setTag(new ViewHolder(convertView));
        }
        Base_Entry entry = list.get(position);
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.text.setText(entry.getText());
        new AsyncTask_DownLoad_ImageTask(holder.image).execute(entry.getImgUrl());
        return convertView;
    }
    public void addAll(Collection<? extends Base_Entry> collection){
        list.addAll(collection);
        notifyDataSetChanged();
    }
    public void clear(){
        list.clear();
        notifyDataSetChanged();
    }
    public static class ViewHolder{
        private ImageView image;
        private TextView text;
        public ViewHolder(View itemView) {
            text = (TextView) itemView.findViewById(R.id.listview_item_text);
            image = ((ImageView) itemView.findViewById(R.id.listview_item_image));
        }
    }
}
