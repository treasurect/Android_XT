package com.treasure_ct.android_xt.studyfragment.seniorcontrols.customview.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

/**
 * Created by treasure on 2016.09.29.
 */

public class Full_DrawPadView extends View {
    public int CLEAR_COLOR = 0xFFb5d7b5;
    private Paint mLinePaint;
    private Paint mRectPaint;
    private TextPaint mTextPaint;//与文本相关的Paint
    private Paint mCirclePaint;

    private Random mRandom;
    private Number mNumber;
    private RectF mArcRectF;
    private Paint mArcPaint;

    public Full_DrawPadView(Context context) {
        this(context,null);
    }

    public Full_DrawPadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }
    private void init(Context context,AttributeSet attrs){
        //初始化对象，避免在onDraw创建

        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setColor(Color.BLUE);
        mLinePaint.setStrokeWidth(5);
        mRectPaint = new Paint();
        mRectPaint.setColor(Color.GRAY);
        mTextPaint = new TextPaint();
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(300);
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRandom = new Random();
        mArcRectF = new RectF();
        //不是矩形的   都得加 Paint.ANTI_ALIAS_FLAG
        mArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }
    /**
     * 绘制控件本身
     * ！！onDraw  方法，不允许  创建对象
     * ！！super。onDraw代表空间原有的显示内容
     * ！！onDraw方法是在排版/测量尺寸之后执行的；可以直接获取getWidth（）getHeight（）
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        //关于绘制
        //canvas 画布，相当于屏幕上的一块，深入理解：android系统内部有一个和屏幕一样大的图像区域
        //系统根据控件的位置尺寸，切成一块，形成画布
        //1.          画线
//        int startColor = 0xFF000000;//ARGB
//        int endColor = 0xFFFFFFFF;  //ARGB
        //矩形渐变
        int r = 0,g = 0,b = 0;
        int step = (255 - r) / 150;
        for (int i = 0; i < 150; i++) {
            int lineY = 50 + i;
            mLinePaint.setColor(Color.rgb(r + step * i,g + step * i,b + step * i));
            canvas.drawLine(20, lineY, 500, lineY, mLinePaint);
        }
        //2.    清屏
        //以特定颜色绘制这个canvas
        canvas.drawColor(CLEAR_COLOR);

        //3.2制定Clip区域，实现绘制的裁剪，控制之后绘制的部分范围
        //在设置区域之前需要使用canvas save（）方法，来保存
        canvas.save();
        canvas.clipRect(200,100,400,130);

//        3.xiexian=斜线渐变
        for (int i = 0; i < 150; i++) {
            mLinePaint.setColor(Color.rgb(r + step * i,g + step * i,b + step * i));
            canvas.drawLine(200 - i,50 + i,600 - i,150 + i,mLinePaint);
        }
        //3.3回复canvas的状态，清除clip
        canvas.restore();

        mLinePaint.setColor(Color.BLACK);
        canvas.drawLine(50,200,50,400,mLinePaint);

        //清屏
        canvas.drawColor(CLEAR_COLOR);

        //4.画矩形
//        Paint可以控制Style属性，来控制是否填充
        mRectPaint.setStyle(Paint.Style.STROKE);
        for (int i = 0;; i++) {
            int span = i * 5;//两个矩形的间距   5
            int left = 50 + span;
            int top = 50 + span;
            int right = 200 - span;
            int bottom = 200 - span;
            if (left >= right || top >= bottom){
                break;
            }
            canvas.drawRect(left, top, right, bottom,mRectPaint);
        }

        canvas.drawColor(CLEAR_COLOR);

        //矩形移动

        int startTop =  300;
        int rectH = 150;
        for (int i = 0; i < 50; i++) {
            int left = 50 + i * 5;
            int right = 250 + i * 5;
            int top = startTop - i * 5;
            int bottom = startTop +rectH + i * 5;

            canvas.drawRect(left,top,right,bottom,mRectPaint);
        }
        //清屏
        canvas.drawColor(Color.BLACK);

        int width = getWidth();
        int height = getHeight();
        //drawText需要制定对齐方式，默认为文本的左下角

        mNumber = mRandom.nextInt(90) + 10;
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(String.valueOf(mNumber),width >> 1,height >>1,mTextPaint);

        for (int i = 0; i < 300; i++) {
            r = mRandom.nextInt(256);
            g = mRandom.nextInt(256);
            b = mRandom.nextInt(256);
            int cx = mRandom.nextInt(getWidth());
            int cy = mRandom.nextInt(getHeight());
            //画圆
            mCirclePaint.setColor(Color.argb(0x99,r,g,b));
            canvas.drawCircle(cx,cy,50,mCirclePaint);

            //5.清屏 画饼图
            canvas.drawColor(CLEAR_COLOR);
            //参数1：RectF 包含浮点数的Rect
            mArcRectF.left = 50;
            mArcRectF.top = 50;
            mArcRectF.right = 150;
            mArcRectF.bottom = 150;
            //参数2：起始角度开始画,顺时针3点钟方向为0度
            //参数3：弧线经历的角度  正数：顺时针  负数：逆时针
            //参数4：弧线两个端点是否与中心点链接
           float Mark_Android = (float) (360 * 0.64);
            float Mark_IOS = (float) (360 * 0.32);
            float Mark_WinP = 360 - Mark_IOS - Mark_Android;
            mArcPaint.setColor(Color.BLUE);
            canvas.drawArc(mArcRectF,0, Mark_Android,true,mArcPaint);
            mArcPaint.setColor(Color.RED);
            canvas.drawArc(mArcRectF,Mark_Android, Mark_IOS,true,mArcPaint);
            mArcPaint.setColor(Color.YELLOW);
            canvas.drawArc(mArcRectF,Mark_Android+Mark_IOS,Mark_WinP,true,mArcPaint);
            mRectPaint.setColor(CLEAR_COLOR);
            canvas.drawCircle(mArcRectF.centerX(),mArcRectF.centerY(),25,mArcPaint);

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            //强制控件重新绘制，invalidate 方法 ，必须在主线程完成
            invalidate();
        }
        return super.onTouchEvent(event);
    }
}
