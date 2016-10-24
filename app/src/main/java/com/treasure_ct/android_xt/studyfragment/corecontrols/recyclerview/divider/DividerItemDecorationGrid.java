package com.treasure_ct.android_xt.studyfragment.corecontrols.recyclerview.divider;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * Created by treasure on 2016.10.05.
 */

public class DividerItemDecorationGrid extends RecyclerView.ItemDecoration{
    public static final int[] ATTRS = new int[]{ android.R.attr.listDivider};
    private Drawable mDrawable;
    public DividerItemDecorationGrid(Context context) {
        final TypedArray array = context.obtainStyledAttributes(ATTRS);
        mDrawable = array.getDrawable(0);
        array.recycle();
    }
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        drawHorizontal(c,parent);
        drawVertical(c,parent);
    }
    private int getSpanCount(RecyclerView parent){
        //列数
     int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager){
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        }else if (layoutManager instanceof StaggeredGridLayoutManager){
            spanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        }
        return spanCount;
    }
    public void drawHorizontal(Canvas canvas,RecyclerView parent){
         int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View childAt = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) childAt.getLayoutParams();
            final int left = childAt.getLeft() - params.leftMargin;
            final int right = childAt.getRight() + params.rightMargin + mDrawable.getIntrinsicWidth();
            final int top = childAt.getBottom() +params.bottomMargin;
            final int bottom = top + mDrawable.getIntrinsicHeight();
            mDrawable.setBounds(left,top,right,bottom);
            mDrawable.draw(canvas);
        }
    }
    public void drawVertical(Canvas canvas,RecyclerView parent){
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View childAt = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) childAt.getLayoutParams();
            final int left = childAt.getRight() + params.rightMargin;
            final int right = left + mDrawable.getIntrinsicWidth();
            final int top = childAt.getTop() - params.topMargin;
            final int bottom = childAt.getBottom() + params.bottomMargin;
            mDrawable.setBounds(left,top,right,bottom);
            mDrawable.draw(canvas);
        }
    }



    private boolean isLastColum(RecyclerView parent,int pos,int spanCount,int childCount){
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager){
            if ((pos + 1) % spanCount == 0){//如果是最后一列，则不需要绘制右边
                return true;
            }
        }else if (layoutManager instanceof StaggeredGridLayoutManager){
            int orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL){
                if ((pos + 1) % spanCount == 0){//如果是最后一列，则不需要绘制右边
                    return true;
                }
            }else {
                childCount = childCount - childCount % spanCount;
                if (pos >= childCount){//如果是最后一列，则不需要绘制右边
                    return true;
                }
            }
        }
        return false;
    }



    private boolean isLastRaw(RecyclerView parent,int pos,int spanCount,int childCount){
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager){
            childCount = childCount - childCount % spanCount;
            if (pos >= childCount){//如果是最后一行，则不需要绘制底部
                return true;
            }

        }else if (layoutManager instanceof StaggeredGridLayoutManager){
            int orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
            //StaggeredGridLayoutManager 且纵向滚动
            if (orientation == StaggeredGridLayoutManager.VERTICAL){
                childCount = childCount - childCount % spanCount;
                if (pos >= childCount){//如果是最后一行，则不需要绘制底部
                    return true;
                }
            }else {
                //StaggeredGridLayoutManager 横向滑动
                if ((pos +1) % childCount == 0){//如果是最后一行，则不需要绘制底部
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        int spanCount = getSpanCount(parent);
        int childCount = parent.getAdapter().getItemCount();
        if (isLastRaw(parent,itemPosition,spanCount,childCount)){
            //如果是最后一行，则不足要绘制底部
            outRect.set(0,0,mDrawable.getIntrinsicWidth(),0);
        }else if (isLastColum(parent,itemPosition,spanCount,childCount)){
            //如果是最后一列，则不需要绘制右边
            outRect.set(0,0,0,mDrawable.getIntrinsicHeight());
        }else {
            outRect.set(0,0,mDrawable.getIntrinsicWidth(),mDrawable.getIntrinsicHeight());
        }
    }
}
