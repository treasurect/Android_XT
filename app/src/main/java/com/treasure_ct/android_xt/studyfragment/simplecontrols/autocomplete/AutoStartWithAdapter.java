package com.treasure_ct.android_xt.studyfragment.simplecontrols.autocomplete;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by treasure on 2016.08.28.
 */
public class AutoStartWithAdapter extends BaseAdapter implements Filterable{
    private Context context;
    private List<String> list;
    private List<String> backup;
    private Filter filter;

    public AutoStartWithAdapter(Context context, List<String> list) {
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
        ((TextView) view).setText(list.get(i));
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
                    if (!TextUtils.isEmpty(charSequence)){
                        List<String> strings = new ArrayList<>();
                        for (String str : backup) {
                            try {
                                String string = PinyinHelper.convertToPinyinString(str, "", PinyinFormat.WITHOUT_TONE);
                                if (string.contains(charSequence)){
                                    strings.add(str);
                                }else {
                                    String shortPinyin = PinyinHelper.getShortPinyin(str);
                                    if (shortPinyin.startsWith(charSequence.toString())) {
                                        strings.add(str);
                                    }
                                }
                            } catch (PinyinException e) {
                                e.printStackTrace();
                            }
                            results.count = strings.size();
                            results.values = strings;
                        }
                    }else {
                        results.count = 0;
                        results.values = new ArrayList<>();
                    }
                    return results;
                }

                @Override
                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    list.clear();
                    list.addAll((Collection<?extends String>)filterResults.values);
                    notifyDataSetChanged();
                }
            };
        }
        return filter;
    }
}
