package com.treasure_ct.android_xt.studyfragment.seniorcontrols.eventdistribution.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by treasure on 2016.09.27.
 */

public class MyListView2 extends ListView{
    private static final String TAG = "MyListView";

    public MyListView2(Context context) {
        super(context);
    }

    public MyListView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int spec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
//        super.onMeasure(widthMeasureSpec, spec);
//    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                setParentScrollAble(false);//手指触到listview的时候，让父ScrollView交出ontouch权限，也就是让父scrollview停住不能滚动
                Log.d(TAG, "onInterceptTouchEvent: down");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "onInterceptTouchEvent: move");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onInterceptTouchEvent: up");
                break;
            case MotionEvent.ACTION_CANCEL:
                setParentScrollAble(true);//当手指松开时，让父ScrollView重新拿到onTouch权限
                Log.d(TAG, "onInterceptTouchEvent: cancel");
                break;
            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
    /**
     * 是否把滚动事件交给父ScrollView
     */
    private void setParentScrollAble(boolean flag){
        getParent().requestDisallowInterceptTouchEvent(!flag);
    }
}
