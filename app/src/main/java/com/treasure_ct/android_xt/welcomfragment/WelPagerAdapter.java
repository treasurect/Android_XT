package com.treasure_ct.android_xt.welcomfragment;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by treasure on 2016.10.25.
 */

public class WelPagerAdapter extends PagerAdapter {
    private Context context;
    private List<Integer>list;

    public WelPagerAdapter(Context context, List<Integer> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object ret = null;
        ImageView imageView = new ImageView(context);

        return ret;
    }
}
