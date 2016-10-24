package com.treasure_ct.android_xt.studyfragment.seniorcontrols.eventdistribution.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by treasure on 2016.09.27.
 */
public class Eventlayout extends LinearLayout{
    private static final String TAG = "Event -----layout";
    public Eventlayout(Context context) {
        super(context);
    }

    public Eventlayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "dispatchTouchEvent: ");
        return super.dispatchTouchEvent(ev);
    }
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        Log.d(TAG, "onInterceptTouchEvent: ");
//        return super.onInterceptTouchEvent(ev);
//    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent: ");
        return super.onTouchEvent(event);
    }
}
