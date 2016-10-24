package com.treasure_ct.android_xt.studyfragment.corecontrols.viewpager.mainpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.treasure_ct.android_xt.studyfragment.corecontrols.viewpager.tablayout.BaseFragment;

import java.util.List;

/**
 * Created by treasure on 2016.09.14.
 */
public class CommonFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> list;
    public CommonFragmentPagerAdapter(FragmentManager fm, List<BaseFragment>list) {
        super(fm);
        this.list = list;
    }

    /**
     *返回指定页码的Fragment
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    /**
     * 返回viewpager的fragment的个数
     * @return
     */
    @Override
    public int getCount() {
        int ret = 0;
        if (list.size() != 0){
            ret = list.size();
        }
        return ret;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).getPageTitle();
    }
}
