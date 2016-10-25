package com.treasure_ct.android_xt.welcomfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.welcomfragment.chat.ChatOnlineActivity;

import java.util.ArrayList;
import java.util.List;

public class WelcomeFragment extends Fragment implements View.OnClickListener {

    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);
        ImageView imgChat = (ImageView) view.findViewById(R.id.welcome_chat);
        viewPager = ((ViewPager) view.findViewById(R.id.welcome_viewPager));
        List<Integer>list = new ArrayList<>();

        imgChat.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.welcome_chat:
                startActivity(new Intent(getContext(),ChatOnlineActivity.class));
                break;
        }
    }
}
