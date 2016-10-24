package com.treasure_ct.android_xt.studyfragment.simpledemo.movielist_xt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.simpledemo.movielist_xt.entries.ML_Entry;
import com.treasure_ct.network_xt.ImageUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

/**
 * Created by treasure on 2016.09.04.
 */
public class ML_BaseAdapter extends BaseAdapter{
    private Context context;
    private List<ML_Entry> list;
    private LayoutInflater inflater;

    public ML_BaseAdapter(Context context, List<ML_Entry> list) {
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
        ML_Entry entry = list.get(position);
        Class<? extends ML_Entry> aClass = entry.getClass();
        Field id = null;
        try {
            id = aClass.getField("id");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        if (id == null){
            try {
                id = aClass.getDeclaredField("id");
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        if (id != null){
            try {
                return (long)id.get(entry);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        Method getId = null;
        try {
            getId = aClass.getMethod("getId");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        if (getId == null){
            try {
                getId = aClass.getDeclaredMethod("getId");
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        if (getId != null){
            try {
                return (long)getId.invoke(entry);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = inflater.inflate(R.layout.activity_simpledemo_movielist_item, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        ML_Entry entry = list.get(position);
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.title.setText(entry.getTitle());
        holder.score.setText(String.valueOf(entry.getScore()));
        holder.time.setText(entry.getRetime());
//        new ImageLoader(holder.image).execute(entry.getCover());
        ImageUtil.loadImage(holder.image,entry.getCover());
        return convertView;
    }
    public  void addAll(Collection<? extends ML_Entry>collection){
        list.addAll(collection);
        notifyDataSetChanged();
    }
    public static class ViewHolder{
        private TextView title,score,time;
        private ImageView image;
        public ViewHolder(View view) {
            title = (TextView) view.findViewById(R.id.ml_title);
            score = (TextView) view.findViewById(R.id.ml_score);
            time = (TextView) view.findViewById(R.id.ml_time);
            image = (ImageView) view.findViewById(R.id.ml_image);
        }
    }
}
