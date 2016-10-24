package com.treasure_ct.android_xt.studyfragment.seniorcontrols.eventdistribution.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by treasure on 2016.09.27.
 */

public class SignPadView extends View{
    /**
     * 记录手指的轨迹信息
     * @param context
     */
    private Path mPath;

    /**
     * 绘制详式
     * @param context
     */
    private Paint mPaint;

    /**
     * 用于存储Canvas中的图像
     * @param context
     */
//    private
    public SignPadView(Context context) {
        super(context,null);
    }

    public SignPadView(Context context, AttributeSet attrs) {
        super(context,attrs);
        Init(context,attrs);
    }
    public void Init(Context context, AttributeSet attrs){
        mPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    /**
     * 控件自身通过Canvas参数，显示在屏幕上
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(mPath,mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        //凡是在空间内部的事件操作，直接使用相对坐标
        float eX = event.getX();
        float eY = event.getY();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(eX,eY);//指定当前新的线段的起始位置
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(eX,eY);//当前线段的点 与制定的点连接一条很小的片段
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        //刷新当前控件，让Android自动i显示内容，调用onDraw方法
        invalidate();
        return true;
    }
}
