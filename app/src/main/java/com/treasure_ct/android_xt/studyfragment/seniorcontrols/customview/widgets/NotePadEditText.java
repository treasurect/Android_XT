package com.treasure_ct.android_xt.studyfragment.seniorcontrols.customview.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

import com.treasure_ct.android_xt.R;

/**
 * Created by treasure on 2016.09.28.
 */

public class NotePadEditText extends EditText{
    private Paint mPaint;
    public NotePadEditText(Context context) {
        //构造方法的调用，调用NotePadEditText(Context context, AttributeSet attrs)
        this(context,null);
    }

    public NotePadEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }
    public void init(Context context, AttributeSet attrs){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //使用自定义属性来获取颜色，直接设置
//        mPaint.setColor(Color.BLUE);
        int blue = Color.BLUE;
        float strokeWidth = 5;
        if (attrs != null){
            //有属性
            //1获取属性集
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.NotePadEditText);
            //2从属性集获取属性
            blue = array.getColor(R.styleable.NotePadEditText_lineColor,blue);

            strokeWidth = array.getDimension(R.styleable.NotePadEditText_strokeWidth,strokeWidth);
            Log.d("0000000000", "init: "+strokeWidth);
            //3回收属性集合
            array.recycle();
        }
        mPaint.setColor(blue);
        //Paint  就是绘制的样式：指定颜色/制定填充/线宽
        mPaint.setStrokeWidth(6);//单位：像素
    }
    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);绘制的是 输入框默认的界面
        super.onDraw(canvas);
        Log.d("NotePad", "onDraw: ");
        //1.画一条线
//        canvas.drawLine(20,50,200,50,mPaint);
        //2.获取控件的属性：宽度
        int width = getWidth();
        //3.获取左右 padding 来实现 线宽
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        //获取行高
        int lineHeight = getLineHeight();
        int paddingTop = getPaddingTop();
        //获取行数
        int lineCount = getLineCount();

        //获取总高度，计算显示的行数
        int height = getHeight();
        int paddingBottom = getPaddingBottom();
        int lineCounts = (height - paddingTop - paddingBottom) / lineHeight;
        //计算最终的行数
        int maxlineCount = Math.max(lineCount, lineCounts);
        for (int i = 1; i <= maxlineCount ; i++) {
            canvas.drawLine(paddingLeft,lineHeight * i + paddingTop,width - paddingRight,lineHeight * i + paddingTop,mPaint);
        }
    }
}
