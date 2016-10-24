package com.treasure_ct.android_xt.studyfragment.seniorcontrols.customview.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.treasure_ct.android_xt.R;

/**
 * Created by treasure on 2016.09.29.
 */

public class PictrueShowView extends View{
    private Bitmap mBitmap;
    private Rect mShowRect;
    private Rect mSrcRect;
    private int index = 0;
    /**
     * 使用图片渲染  对图片的
     */
    private Paint mShapePaint;

    public PictrueShowView(Context context) {
        this(context,null);
    }

    public PictrueShowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }
    public void init(Context context,AttributeSet attrs){
        //加载图片资源，完整加载原图
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.sprit);
        mShowRect = new Rect();
        mSrcRect = new Rect();
        //设置渲染，实现圆型图片
        mShapePaint = new Paint();
        BitmapShader shader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mShapePaint.setShader( shader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //1.原图绘制，不进行缩放，显示在指定坐标
//        canvas.drawBitmap(mBitmap,0,0,null);
        //2.缩放图片，能显示完整图片
        //参数2：从原图裁剪出一个小区域，显示在Canvas，null表示整张图
        //参数3：裁剪出的图片显示在canvas的那个区域由left和right决定
//        mShowRect.left = 0;
//        mShowRect.top = 0;
//        mShowRect.right = mShowRect.left + 400;
//        mShowRect.bottom = mShowRect.top + 600;
//        if (mBitmap != null) {
//            canvas.drawBitmap(mBitmap, null, mShowRect, null);
//        }
        //3.裁剪绘制，绘制图片的一小部分
        int height = mBitmap.getHeight();
        int width = mBitmap.getWidth() - 80;
        int numOfRow = 7;
        int numOfCol = 12;
        int perW = width / numOfCol;
        int perH = height /numOfRow;
        int cloumn = index % 12;
        int row  = index /12;

        mSrcRect.left = perW * cloumn;
        mSrcRect.top = perH * row;
        mSrcRect.right = mSrcRect.left +perW;
        mSrcRect.bottom = mSrcRect.top + perH;
        mShowRect.left = 0;
        mShowRect.top = 0;
        mShowRect.right = mShowRect.left + perW;
        mShowRect.bottom = mShowRect.top + perH;
        canvas.drawBitmap(mBitmap,mSrcRect,mShowRect,null);
        //4.绘制向指定形状的图片；
        //Paint中可以指定图片渲染效果
        canvas.drawCircle(200,200,50,mShapePaint);
    }
    public  void showNext(){
        index ++;
        if (index >= 64){
            index = 0;
        }
//        invalidate();
        //n能够在子线程，发起Handler消息  更新UI
        postInvalidate();
    }
}
