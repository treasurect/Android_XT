package com.treasure_ct.android_xt.studyfragment.seniorcontrols.customview.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by treasure on 2016.09.28.
 */

public class AlphaIndicator extends LinearLayout{
    /**
     * 发送方：声明接口 ，类内部有这个接口类型的成员变量。发状态时，调用接口对象的方法
     * 接收方：实现接口，设置给当前对象
     */
    public interface OnIndexSelectedListener{
        void onIndexSelected(int index);
        void onFingerUp();
    }
    private OnIndexSelectedListener mOnIndexSelectedListener;
    private int lastIndex;
    public AlphaIndicator(Context context) {
        this(context,null);
    }

    public AlphaIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
        lastIndex = -1;
    }

    public void setOnIndexSelectedListener(OnIndexSelectedListener onIndexSelectedListener) {
        mOnIndexSelectedListener = onIndexSelectedListener;
    }

    public void init(Context context, AttributeSet attrs){
        //代码方式，添加控件，到自身
        lastIndex = -1;

        // 组合控件:
        // 1. addView
        // 2. 通用的样式设置
        for (int i = 0; i < 26; i++) {
            TextView textView = new TextView(context);
            //text
            textView.setText(Character.toString(((char) ('A' + i))));
            //gravity
            textView.setGravity(Gravity.CENTER);
            //textSize
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
            addView(textView);
        }
    }

    /**
     * 处理手指拖拽，形成不同字母的选择
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean ret = true;
        float eX = event.getX();
        float eY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:
                //TODO:根据事件的x，y，来查找在哪一个控件上
                //ViewGroup getChildCount（）获取控件总数
                //ViewGroup getChildAt(int index)获取指定控件
                int childCount = getChildCount();
                int index = -1;
                for (int i = childCount - 1; i >= 0 ; i--) {
                    View child = getChildAt(i);
                    int left = child.getLeft();
                    int top = child.getTop();
                    int right = child.getRight();
                    int bottom = child.getBottom();
                    if (eX >= left && eX <= right){
                        if (eY >= top && eY <= bottom){
                            //在当前控件上
                            index = i;
                            break;
                        }
                    }
                }
                if (index != lastIndex){
                    Log.d("aaaaaaaaaaaa", "onTouchEvent: "+index);
                    lastIndex = index;
                    if (mOnIndexSelectedListener != null) {
                        mOnIndexSelectedListener.onIndexSelected(index);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                lastIndex = -1;
                mOnIndexSelectedListener.onFingerUp();
                break;
        }
        return ret;
    }
}
