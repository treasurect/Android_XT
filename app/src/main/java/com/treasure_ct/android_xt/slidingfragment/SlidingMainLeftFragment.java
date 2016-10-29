package com.treasure_ct.android_xt.slidingfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.treasure_ct.android_xt.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SlidingMainLeftFragment extends Fragment implements View.OnClickListener {
   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_sliding_left, container, false);
       ImageView weather = (ImageView) view.findViewById(R.id.left_weather_img);
       ImageView video_recording = (ImageView) view.findViewById(R.id.left_video_recording_img);
       weather.setOnClickListener(this);
       video_recording.setOnClickListener(this);
       return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_weather_img:
                MainDialogFragment dialogFragment = new MainDialogFragment();
                dialogFragment.show(getChildFragmentManager(),"WeatherDialog");
                break;
            case R.id.left_video_recording_img:

                break;
        }
    }
}
