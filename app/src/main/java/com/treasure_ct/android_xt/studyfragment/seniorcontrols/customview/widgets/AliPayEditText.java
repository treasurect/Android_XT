package com.treasure_ct.android_xt.studyfragment.seniorcontrols.customview.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

/**
 * Created by treasure on 2016.09.28.
 */

public class AliPayEditText extends EditText{
    /**
     *
     */
    public AliPayEditText(Context context) {
        super(context);
    }

    /**
     * 在布局中使用控件，自动调用这个方法
     * 布局里的属性,由AttributeSet来获取
     * 自定属性就是从这个参数
     */
    public AliPayEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 当文本变化的时候回调
     * text:当前文本
     * start：
     * lengthBefore：
     * lengthAfter:
     */
    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        if (text.length() == 1){
            //TODO:跳到下一个焦点
            View focusSearch = focusSearch(FOCUS_RIGHT);
            if (focusSearch != null) {
                clearFocus();
                focusSearch.requestFocus();
            }
        }else {
            View view = focusSearch(FOCUS_LEFT);
            if (view != null) {
                clearFocus();
                view.requestFocus();
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean ret = false;
        //当按键 是退格键 ，删除时处理
        if (keyCode == KeyEvent.KEYCODE_DEL) {
            if (getText().length() == 0) {//没有输入的情况，删除键就找焦点
                View view = focusSearch(FOCUS_LEFT);
                if (view != null) {
                    view.requestFocus();
                }
                ret = true;
            }
        }
        if (!ret) {
            //只有在退格的时候，检查，否则 让输入框自己处理
            ret = super.onKeyDown(keyCode,event);
        }
        return ret;
    }
}
