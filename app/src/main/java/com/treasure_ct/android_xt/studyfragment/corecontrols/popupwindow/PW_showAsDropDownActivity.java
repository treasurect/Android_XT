package com.treasure_ct.android_xt.studyfragment.corecontrols.popupwindow;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.treasure_ct.android_xt.R;

public class PW_showAsDropDownActivity extends AppCompatActivity implements View.OnClickListener {

    private PopupWindow popWindow;
    private PopupWindow popWindow1;
    private PopupWindow popWindow2;
    private PopupWindow popWindow3;
    private TextView clickMenu,click10ge,click_Return;
    private float screen_height_dp,screen_height_px;
    private boolean isPlay = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corecontrols_popupwindow_showasdropdown);
        clickMenu = (TextView) findViewById(R.id.popupwindow_showasdropdown_menu);
         click10ge = (TextView) findViewById(R.id.popupwindow_showasdropdown_10app);
        click_Return = (TextView) findViewById(R.id.popupwindow_showasdropdown_return);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
         screen_height_px = metrics.heightPixels;
        screen_height_dp = screen_height_px /metrics.density;
        Toast.makeText(this, ""+screen_height_dp, Toast.LENGTH_SHORT).show();
    }

    public void btnReturn(View view) {
        finish();
    }

    public void btn10App(View view) {
        if (popWindow1 == null && popWindow2 == null && popWindow3 == null){
            showPopupWindow2();
        }else {
            if (popWindow2.isShowing() || popWindow1.isShowing() || popWindow3.isShowing()){
                popWindow1.dismiss();
                popWindow2.dismiss();
                popWindow3.dismiss();
            }else {
                showPopupWindow2();
            }
        }
    }

    public void btnMenu(View view) {
        if (popWindow == null) {
            showPopupWindow();
        } else {
            if (popWindow.isShowing()) {
                popWindow.dismiss();
            } else {
                showPopupWindow();
            }
        }
    }

    /**
     * 为什么非要设置width和height的才胡显示，xml文件中已经设置为什么代码还需要设置一遍
     * <p>
     * 原因：通篇代码中，总共只有三个函数能设置mWidth，分别如下:除了setWidth（）函数本身，就只有PopupWindow（）的两个构造函数了；
     *
     * @1 public void setWidth(int width) {
     * mWidth = width;
     * }
     * @2 public PopupWindow(View contentView, int width, int height) {
     * this(contentView, width, height, false);
     * }
     * @3 public PopupWindow(View contentView, int width, int height, boolean focusable) {
     * }
     * 子控件的大小实在父控件大小确立的基础上决定
     * View contentView = LayoutInflater.from(this).inflate(R.layout.popwindow_showatlocation_item, null);
     * contentView是直接inflate出来的，没有父控件，只有我们自己去设定，不然默认为0
     */
    private void showPopupWindow() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.popwindow_showatlocation_item, null);
        popWindow = new PopupWindow(contentView);
        popWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popWindow.setAnimationStyle(R.style.popupWindowAnim);
        TextView tv1 = (TextView) contentView.findViewById(R.id.popupWindow_showAtLocation_computer);
        TextView tv2 = (TextView) contentView.findViewById(R.id.popupWindow_showAtLocation_food);
        TextView tv3 = (TextView) contentView.findViewById(R.id.popupWindow_showAtLocation_money);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        //@1  设置为false ，popupWindow的点击事件不可用，popUPwindow 的所有touch事件不可点击
//        popWindow.setTouchable(false);
        //@2  如果是popupwindow中含有editText，则editText获取不到焦点，编辑不了，对于普通控件没什么影响
//        popWindow.setFocusable(false);
//@3  设置为true代表点击   popupwindow可以退出popupwindow  必须要提前设置popWindow.setBackgroundDrawable(new BitmapDrawable());
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        popWindow.setOutsideTouchable(true);
        //@4  setBackgroundDrawable
        //**如果我们给PopupWindow添加了mBackground，那它将会：**
//        setOutsideTouchable(true)将生效，具有外部点击隐藏窗体的功能
//                手机上的返回键将可以使窗体消失
//        对于PopupWindow上层没有捕捉的点击事件，点击之后，仍然能使窗体消失。

        /**
         * public void showAsDropDown(View anchor, int xoff, int yoff) {
         * …………
         * //准备窗口
         * WindowManager.LayoutParams p = createPopupLayout(anchor.getWindowToken());
         * preparePopup(p);
         * …………
         * //显示窗口
         * invokePopup(p);
         * }
         */
        popWindow.showAsDropDown(clickMenu);
    }

    private void showPopupWindow2() {
        View content_View = LayoutInflater.from(this).inflate(R.layout.popwindow_show10ge_item1, null);
        popWindow1 = new PopupWindow(content_View);
        popWindow1.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popWindow1.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popWindow1.setAnimationStyle(R.style.popupWindowAnim1);


         content_View = LayoutInflater.from(this).inflate(R.layout.popwindow_show10ge_item2, null);
        popWindow2 = new PopupWindow(content_View);
        popWindow2.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popWindow2.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popWindow2.setAnimationStyle(R.style.popupWindowAnim2);

         content_View = LayoutInflater.from(this).inflate(R.layout.popwindow_show10ge_item3, null);
        popWindow3 = new PopupWindow(content_View);
        popWindow3.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popWindow3.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popWindow3.setAnimationStyle(R.style.popupWindowAnim3);
        popWindow1.setOutsideTouchable(true);
        popWindow2.setOutsideTouchable(true);
        popWindow3 .setOutsideTouchable(true);
        popWindow1.showAsDropDown(click_Return,150, (int) (screen_height_dp));
        popWindow2.showAsDropDown(click10ge,100,(int) (screen_height_dp));
        popWindow3.showAsDropDown(clickMenu,50 ,(int) (screen_height_dp));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.popupWindow_showAtLocation_computer:
                Toast.makeText(this, "计算机 被点击了", Toast.LENGTH_SHORT).show();
                popWindow.dismiss();
                break;
            case R.id.popupWindow_showAtLocation_food:
                Toast.makeText(this, "食品营养 被点击了", Toast.LENGTH_SHORT).show();
                popWindow.dismiss();
                break;
            case R.id.popupWindow_showAtLocation_money:
                Toast.makeText(this, "会计学 被点击了", Toast.LENGTH_SHORT).show();
                popWindow.dismiss();
                break;
        }
    }
}
