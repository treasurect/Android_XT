package com.treasure_ct.android_xt.studyfragment.simpledemo.file_explorer_xt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.treasure_ct.android_xt.R;

import java.io.File;
import java.util.List;

/**
 * Created by treasure on 2016.09.06.
 */
public class FileAdapter extends BaseAdapter{
    private Context context;
    private List<File>list;

    public FileAdapter(Context context, List<File> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (list!=null){
            ret = list.size();
        }
        return ret;
    }

    /**
     * 获取指定条目的数据，只有getCount（）>0才可以执行
     * @param position
     * @return
     */
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
        View ret = null;
        if (convertView != null){
            ret = convertView;
        }else {
            ret = LayoutInflater.from(context).inflate(R.layout.activity_simpledemo_bg_item,parent,false);
        }
        ViewHoler holer = (ViewHoler) ret.getTag();
        if (holer == null){
            holer = new ViewHoler(ret);
            ret.setTag(holer);
        }
        File file = list.get(position);
        holer.bindView(position,file);
        return ret;
    }
    private static class ViewHoler{
        private TextView textView;
        private ImageView imageView;

        public ViewHoler(View view) {
            textView = (TextView) view.findViewById(R.id.item_text);
            imageView = (ImageView) view.findViewById(R.id.item_image);
        }
        public void  bindView(int position,File file){
            if (file.isDirectory()){
                imageView.setImageResource(R.mipmap.icon_file_explore);
            }else {
                imageView.setImageResource(R.mipmap.icon_file);
            }
            textView.setText(file.getName());
        }
    }
}
