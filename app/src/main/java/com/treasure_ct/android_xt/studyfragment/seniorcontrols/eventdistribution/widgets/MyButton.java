package com.treasure_ct.android_xt.studyfragment.seniorcontrols.eventdistribution.widgets;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by treasure on 2016.09.27.
 */

public class MyButton extends Button{
    private float lastX,lastY;
    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    /**
     * 当控件自身的  dispatchTouchEvent 方法被调用，并且控件无子控件或子控件未被点击到
     * 就会调用View内部的dispatchTouchEvent相当于自己处理事件
     * 调用到onTouchEvent，处理触摸事件的实际操作
     * @param event
     * @return boolean 返回true代表处理，返回false代表未处理，需要父容器处理
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean ret = false;
        //MotionEvent 内部包含action属性，代表 触摸事件状态：按下/滚动/抬起
        int action = event.getAction();
        //如果实现  手机拖拽Button的功能
        float eX = event.getRawX();
        float eY = event.getRawY();
        String name ="";
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                name = "按下";
                lastX = eX;
                lastY = eY;
                break;
            case MotionEvent.ACTION_MOVE:
                name = "移动动";
                float cX = eX - lastX;
                float cY = eY - lastY;
                float myX = ViewCompat.getX(this);
                float myY = ViewCompat.getY(this);
                ViewCompat.setX(this,myX + cX);
                ViewCompat.setY(this,myY + cY);
                lastX = eX;
                lastY = eY;
                ret = true;
                break;
            case MotionEvent.ACTION_UP:
                name = "抬起";
                ret = super.onTouchEvent(event);
                break;
        }
        Log.d("1111111111111", "onTouchEvent: "+name);
        return ret;
    }
}
