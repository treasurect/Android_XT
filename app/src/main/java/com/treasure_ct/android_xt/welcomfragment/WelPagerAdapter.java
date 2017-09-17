package com.treasure_ct.android_xt.welcomfragment;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
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
        return 10000;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View ret = null;
        position = position % list.size();
            ImageView imageView = new ImageView(context);
            imageView.setBackgroundResource(list.get(position));
            ret = imageView;
        container.addView(ret);
        return ret;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
