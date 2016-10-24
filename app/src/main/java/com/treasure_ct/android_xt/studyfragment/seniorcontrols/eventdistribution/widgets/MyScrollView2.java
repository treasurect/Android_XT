package com.treasure_ct.android_xt.studyfragment.seniorcontrols.eventdistribution.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by treasure on 2016.09.27.
 */

public class MyScrollView2 extends ScrollView {
    private GestureDetector mGestureDetector;
    View.OnTouchListener mOnTouchListener;
    public MyScrollView2(Context context) {
        super(context);
    }

    public MyScrollView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        mGestureDetector = new GestureDetector(new YScrollDetector());
        setFadingEdgeLength(0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev) && mGestureDetector.onTouchEvent(ev);
    }
    //如果Y角移动的绝对值大于X轴移动的绝对值，即纵向滑动返回true
    class YScrollDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if (Math.abs(distanceY) > Math.abs(distanceX)){
                return true;
            }
            return false;
        }
    }
}

