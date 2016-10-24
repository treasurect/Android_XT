package com.treasure_ct.android_xt.studyfragment.seniorcontrols.customview.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by treasure on 2016.09.28.
 */

/**
 * 流式布局，从左到右，从上到下 排版控件
 * 通常就是标签列表和个人印象
 */
public class FlowLayout extends ViewGroup{
    public FlowLayout(Context context){
        super(context);
    }
    public FlowLayout(Context context, AttributeSet attrs){
        super(context,attrs);
    }

    /**
     * 当控件没有参数,自动生成一个
     * @return
     */
    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
    }
    /**
     * addView 时 默认参数
     * @param p
     * @return
     */
    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }
    /**
     * 布局使用的LayoutParams
     * @param attrs
     * @return
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }

    /**
     * onMeasure方法，需要测量自身的尺寸，如果是View Group先测量子控件
     * 然后再测量自己 为了支持match_parent
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //所有子空间按照最大容器空间来计算尺寸
        //！！！这个方法会在哦那Layout之前调用，可以通过子控件的
        //getMeasureWidth()/getMeasure()来控制控件宽高

//        measureChildren(widthMeasureSpec,heightMeasureSpec);
        //measureChildren（）不支持margin测量
        //TODO：支持margin排版支持
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View childAt = getChildAt(i);
            measureChildWithMargins(childAt,widthMeasureSpec,0,heightMeasureSpec,0);
        }
        //onMeasure 需要调用自身的计算，可以使用super...\
        //或者setMeasureDimenion(width,height);
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
    }

    /**
     * 所有ViewGroup 子类都需要实现这个方法，用来排版控件
     * @param changed  布局被修改
     * @param l  左  当前容器在父容器的left位置
     * @param t 上   当前容器在父容器的top位置
     * @param r you 当前容器在父容器的right位置
     * @param b 下   当前容器在父容器的bottom位置
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //遍历所有控件，进行空间位置的摆放，如果不排，控件无法显示
        int count = getChildCount();
        int lastBottom = 20;
        int lastLeft = 20;
        //每行最高尺寸
        int maxHeight = 0;
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            //垂直布局算法
//            child.layout(20,lastBottom,100,lastBottom +120);
//            lastBottom +=100;
            //Flow布局算法
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();

            //使用MarginLayoutParams
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) child.getLayoutParams();
            int leftMargin = marginLayoutParams.leftMargin;
            int rightMargin = marginLayoutParams.rightMargin;

            //如果当前控件left+width大于容器的宽度,那么换行
            int right = lastLeft + width +leftMargin + rightMargin;
            if (right > r - l){
                lastLeft = 20;
                lastBottom += maxHeight;
                right = lastLeft + width + leftMargin + rightMargin;
                maxHeight = height;
            }else {
                maxHeight = Math.max(maxHeight,height);
            }

            child.layout(lastLeft,lastBottom,right,lastBottom + height);
            lastLeft = right;
        }
    }
}
