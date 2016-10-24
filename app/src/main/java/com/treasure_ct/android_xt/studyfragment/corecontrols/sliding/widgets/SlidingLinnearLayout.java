package com.treasure_ct.android_xt.studyfragment.corecontrols.sliding.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by treasure on 2016.10.06.
 */

public class SlidingLinnearLayout extends LinearLayout{
    private float downX,downY;
    public SlidingLinnearLayout(Context context) {
        super(context);
    }

    public SlidingLinnearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean ret = false;
        float rawX = ev.getRawX();
        float rawY = ev.getRawY();
        if (ev.getAction() == MotionEvent.ACTION_DOWN){
            downX = rawX;
            downY = rawY;
        }else if (ev.getAction() == MotionEvent.ACTION_MOVE){
            float moveX = rawX - downX;
            float moveY = rawY - downY;
            if (Math.abs(moveX) > Math.abs(moveY) && Math.abs(moveX) > 10){
                ret = true;
            }
        }
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                downX = rawX;
//                downY = rawY;
//                ret =true;
//                break;
//            case MotionEvent.ACTION_MOVE:
//                float moveX = rawX - downY;
//                float moveY = rawY - downY;
//                if (Math.abs(moveX) > Math.abs(moveY) && Math.abs(moveX) > 10){
//                    ret = true;
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//
//                break;
//        }
        return ret;
    }
}
