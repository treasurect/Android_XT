package com.treasure_ct.android_xt.studyfragment.seniorcontrols.customview.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by treasure on 2016.09.29.
 */

public class MeasureDemoView extends View{
    private static final String TAG = "MeasureDemoView";
    private String textView;
    private TextPaint textPaint;
    public MeasureDemoView(Context context) {
        this(context,null);
    }

    public MeasureDemoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }


    public void init(Context context,AttributeSet attrs){
        textView = "hello 世界";
        textPaint = new TextPaint();
        textPaint.setTextSize(50);
        textPaint.setColor(Color.BLUE);
    }
    /**
     * 1.默认View类的onMeasure 方法，会设置父容器最大值，不管是否为wrap_content
     * 2.参数使用Measure类来定义，包含mode和size
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width_mode = MeasureSpec.getMode(widthMeasureSpec);
        int width_size = MeasureSpec.getSize(widthMeasureSpec);
        int height_mode = MeasureSpec.getMode(heightMeasureSpec);
        int height_size = MeasureSpec.getSize(heightMeasureSpec);
        //模式有三种：AT_MOST,EXACTLY,UNSPECIFIED
        switch (width_mode){
            case MeasureSpec.AT_MOST:
                width_size = (int) textPaint.measureText(textView);
                Log.d(TAG, "width_mode: AT_MOST");
                break;
            case MeasureSpec.EXACTLY:
                Log.d(TAG, "width_mode: EXACTLY");
                break;
            case MeasureSpec.UNSPECIFIED:
                Log.d(TAG, "width_mode: UNSPECIFIED");
                break;
        }
        Log.d(TAG, "onMeasure: "+width_size);
        switch (height_mode){
            case MeasureSpec.AT_MOST:
                Log.d(TAG, "height_mode: AT_MOST");
                height_size = (int) textPaint.getTextSize();
                break;
            case MeasureSpec.EXACTLY:
                Log.d(TAG, "height_mode: EXACTLY");
                break;
            case MeasureSpec.UNSPECIFIED:
                Log.d(TAG, "height_mode: UNSPECIFIED");
                break;
        }
        Log.d(TAG, "onMeasure: "+height_size);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //onMeasure必须调用super或者setMeasuredDimension(w,h);
        setMeasuredDimension(width_size,height_size);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawText(textView,0,0 - textPaint.ascent(),textPaint);
    }
}
