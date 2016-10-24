package com.treasure_ct.android_xt;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by treasure on 2016.10.21.
 */

public class MainFragmentAdapter extends FragmentPagerAdapter{
    private List<Fragment>list;
    public MainFragmentAdapter(FragmentManager fm,List<Fragment>list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (list != null){
            ret = list.size();
        }
        return ret;
    }
}
