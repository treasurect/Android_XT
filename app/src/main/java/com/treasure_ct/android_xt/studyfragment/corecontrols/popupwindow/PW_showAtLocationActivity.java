package com.treasure_ct.android_xt.studyfragment.corecontrols.popupwindow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.treasure_ct.android_xt.R;

public class PW_showAtLocationActivity extends AppCompatActivity implements View.OnClickListener {
    private PopupWindow popupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corecontrols_popupwindow_showatlocation);
    }

    public void ShowPopupWindow(View view) {
        View contentView = LayoutInflater.from(this).inflate(R.layout.popwindow_showatlocation_item, null);
         popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setContentView(contentView);
        TextView tv1 = (TextView) contentView.findViewById(R.id.popupWindow_showAtLocation_computer);
        TextView tv2 = (TextView) contentView.findViewById(R.id.popupWindow_showAtLocation_food);
        TextView tv3 = (TextView) contentView.findViewById(R.id.popupWindow_showAtLocation_money);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);

        //显示popWindow
        View rootView = LayoutInflater.from(this).inflate(R.layout.activity_corecontrols_popupwindow_showatlocation, null);
        popupWindow.showAtLocation(rootView, Gravity.BOTTOM,0,0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.popupWindow_showAtLocation_computer:
                Toast.makeText(this, "点击了 computer", Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
                break;
            case R.id.popupWindow_showAtLocation_food:
                Toast.makeText(this, "点击了 食品营养", Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
                break;
            case R.id.popupWindow_showAtLocation_money:
                Toast.makeText(this, "点击了 会计学", Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
                break;
        }
    }
}
