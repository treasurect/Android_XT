package com.treasure_ct.android_xt.studyfragment.corecontrols.animation.view;

import android.view.animation.Interpolator;

/**
 * Created by treasure on 2016.09.12.
 */
public class SimpleInterpolator implements Interpolator{
    @Override
    public float getInterpolation(float input) {
        float ret = input;
        if (input > 0.5){
            ret = input - 0.1f;
        }
        return ret;
    }
}
