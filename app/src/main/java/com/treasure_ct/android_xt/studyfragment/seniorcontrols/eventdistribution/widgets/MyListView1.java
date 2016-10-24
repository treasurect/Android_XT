package com.treasure_ct.android_xt.studyfragment.seniorcontrols.eventdistribution.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by treasure on 2016.10.05.
 */

public class MyListView1 extends ListView {
    private static final String TAG = "MyListView";

    public MyListView1(Context context) {
        super(context);
    }

    public MyListView1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int spec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, spec);
    }
}
