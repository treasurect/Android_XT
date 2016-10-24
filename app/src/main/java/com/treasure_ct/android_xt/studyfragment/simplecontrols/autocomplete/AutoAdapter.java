package com.treasure_ct.android_xt.studyfragment.simplecontrols.autocomplete;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by treasure on 2016.08.28.
 */
public class AutoAdapter extends BaseAdapter implements Filterable{
    private Context context;
    private List<AutoEntry>list;
    private List<AutoEntry>backup;
    private Filter filter;

    public AutoAdapter(Context context, List<AutoEntry> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            view = new TextView(context);
        }
        ((TextView) view).setText(list.get(i).getText());
        return view;
    }

    @Override
    public Filter getFilter() {
        if (filter == null){
            filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence charSequence) {
                    if (backup == null){
                        backup = new ArrayList<>(list);
                    }
                    FilterResults results = new FilterResults();
                    if (!TextUtils.isEmpty(charSequence)) {
                        List<AutoEntry> entries = new ArrayList<>();
                        for (AutoEntry entry : backup) {
                            String pinyin = entry.getPinyin();
                            if (pinyin.contains(charSequence.toString())){
                                entries.add(entry);
                            }
                        }
                        results.count = entries.size();
                        results.values = entries;
                    }else {
                        results.count = 0;
                        results.values = new ArrayList<>();
                    }
                    return results;
                }

                @Override
                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    list.clear();
                    list.addAll((Collection<? extends AutoEntry>)filterResults.values);
                    notifyDataSetChanged();
                }
            };
        }
    return filter   ;
    }
}
