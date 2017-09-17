package com.treasure_ct.android_xt.relaxfragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.treasure_ct.android_xt.R;

public class RelaxFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_relax2, container, false);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.relax_tabLayout);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.relax_viewPager);

        return view;
    }

}
