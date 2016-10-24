package com.treasure_ct.android_xt.studyfragment.corecontrols.viewpager.mainpager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.corecontrols.viewpager.tablayout.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends BaseFragment {


    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public String getPageTitle() {
        return "First";
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_corecontrols_mainpager_fragment_first, container, false);
    }

}
