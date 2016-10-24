package com.treasure_ct.android_xt.studyfragment.simplecontrols;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.treasure_ct.android_xt.R;

/**
 * Created by treasure on 2016.08.26.
 */
public class CheckBoxActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private CheckBox chekbox;
    private int count = 888;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simplecontrols_checkbox);
        chekbox = (CheckBox) findViewById(R.id.simple_checkbox);
        Drawable[] drawables = chekbox.getCompoundDrawables();
        //获取 左上右下的图片 赋给数组
        Drawable top = drawables[1];
        //都去上访的图片
        ColorStateList list = getResources().getColorStateList(R.color.checkbox_sel);
        DrawableCompat.setTintList(top,list);
        //图片着色
        DrawableCompat.setTintMode(top, PorterDuff.Mode.SRC_ATOP);
        chekbox.setOnCheckedChangeListener(this);
        chekbox.setText("+"+count);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        compoundButton.setText("+"+(count + (b?1:0)));
    }
}
