package com.treasure_ct.android_xt.studyfragment.simpledemo.netease_xt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.simpledemo.netease_xt.entries.Netease_Entry;
import com.treasure_ct.network_xt.ImageUtil;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by treasure on 2016.09.03.
 */
public class Netease_Adapter extends BaseAdapter{
    private Context context;
    private List<Netease_Entry>list;
    private LayoutInflater inflater;

    public Netease_Adapter(Context context, List<Netease_Entry> list) {
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
        if (convertView == null){
            if (getItemViewType(position) == 0) {
                convertView = inflater.inflate(R.layout.activity_simpledemo_netease_sim_item, parent, false);
            }else {
                convertView = inflater.inflate(R.layout.activity_simpledemo_netease_multi_item,parent,false);
            }
            convertView.setTag(new ViewHolder(convertView));
        }
        Netease_Entry entry = list.get(position);
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.title.setText(entry.getTitle());
        holder.time.setText(entry.getTime());
        holder.source.setText(entry.getSource());
        ImageUtil.loadImage(holder.imageView,entry.getImage());
        if (getItemViewType(position) == 1) {
            List<Map<String, String>> list = entry.getList();
            ImageUtil.loadImage(holder.imageView2,list.get(0).get("imgsrc"));
            ImageUtil.loadImage(holder.imageView3,list.get(1).get("imgsrc"));
        }
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        Netease_Entry entry = list.get(position);
        if (entry.getList() == null) {
            return 0;
        }else {
            return 1;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    public void addAll(Collection<? extends Netease_Entry>collection){
        list.addAll(collection);
        notifyDataSetChanged();
    }
    public static class ViewHolder{
        private TextView title;
        private TextView source;
        private TextView time;
        private ImageView imageView;
        private ImageView imageView2;
        private ImageView imageView3;
        public ViewHolder(View itemview) {
            title = (TextView) itemview.findViewById(R.id.netease_title);
            source = (TextView) itemview.findViewById(R.id.netease_source);
            time = (TextView) itemview.findViewById(R.id.netease_time);
            imageView = (ImageView) itemview.findViewById(R.id.netease_image);
            imageView2 = (ImageView) itemview.findViewById(R.id.netease_image_2);
            imageView3 = (ImageView) itemview.findViewById(R.id.netease_image_3);
        }
    }
}
