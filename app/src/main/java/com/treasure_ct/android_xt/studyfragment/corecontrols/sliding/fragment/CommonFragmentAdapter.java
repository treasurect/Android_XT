package com.treasure_ct.android_xt.studyfragment.corecontrols.sliding.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by treasure on 2016.10.09.
 */

public class CommonFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment>mList;

    public CommonFragmentAdapter(FragmentManager fm, List<Fragment>list) {
        super(fm);
        mList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (mList != null) {
            ret = mList.size();
        }
        return ret;
    }
    //    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//        View ret = null;
//        container.addView(ret);
//        return ret;
//    }
//
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView((View) object);
//    }
}
