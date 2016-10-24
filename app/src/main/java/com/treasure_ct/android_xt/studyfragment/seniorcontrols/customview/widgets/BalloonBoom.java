package com.treasure_ct.android_xt.studyfragment.seniorcontrols.customview.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.treasure_ct.android_xt.R;

import java.util.Random;

/**
 * Created by treasure on 2016.09.29.
 */

public class BalloonBoom extends View{
    private Rect mRect;
    private Rect mImgRect;
    private Rect mShowRect;
    private Bitmap bitmap,bitmap2;
    private int index = 0,num_id = 0;
    public int score;
    private int screen_width,screen_height,width_move = 100;
    private Random mRandom;
    public BalloonBoom(Context context) {
        this(context,null);
    }

    public BalloonBoom(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }
    public void init(Context context,AttributeSet attrs){
         bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.icon_main4);
        bitmap2 = BitmapFactory.decodeResource(context.getResources(),R.mipmap.boom);
        mRect = new Rect();
        mImgRect = new Rect();
        mShowRect = new Rect();
        mRandom = new Random();
    }

    @Override
    protected void onDraw(Canvas canvas) {
         screen_width = getWidth();
         screen_height = getHeight();
        mRect.left = width_move;
        mRect.top = screen_height - index * 10;
        mRect.right = mRect.left + 150;
        mRect.bottom = mRect.top + 200;
        if (bitmap != null){
            canvas.drawBitmap(bitmap,null,mRect,null);
        }
        if (bitmap2 != null){
            canvas.drawBitmap(bitmap2,mImgRect,mShowRect,null);
        }

    }
    public  void shownext(){
        index ++;
        if (index >= (screen_height + 200)/10){
            index = 0;
            width_move = mRandom.nextInt(screen_width - 150);
        }
        postInvalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int img_width = bitmap2.getWidth();
        int img_height = bitmap2.getHeight();
        int numOfRows = 2;
        int numOfCol = 2;
        int boom_width = img_width / numOfCol;
        int boom_height = img_height / numOfRows;
        int colomu = num_id / numOfCol;
        int row = num_id /numOfRows;
        if ((event.getX() > mRect.left && event.getX() < mRect.right) && (event.getY() > mRect.top && event.getY() < mRect.bottom)){
            Log.d("helloooooooooooo", "onTouchEvent: ");
            score ++;
            index = 0;
            width_move = mRandom.nextInt(screen_width - 150);
            mImgRect.left = (int) event.getX();
            mImgRect.top = (int)event.getY();
            mImgRect.right = mImgRect.left + boom_width * colomu;
            mImgRect.bottom = mImgRect.top +boom_height * row;
            mShowRect.left = (int) event.getX();
            mShowRect.top = (int)event.getY();
            mShowRect.right = mShowRect.left + boom_width;
            mShowRect.bottom = mShowRect.top +boom_height;

        }
        return super.onTouchEvent(event);
    }
//
//    public int change_num() {
//        score++;
//        return score;
//    }
}
