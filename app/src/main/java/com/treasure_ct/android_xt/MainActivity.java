package com.treasure_ct.android_xt;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.mob.commons.SMSSDK;
import com.treasure_ct.android_xt.cartfragment.CartFragment;
import com.treasure_ct.android_xt.minefragment.MineFragment;
import com.treasure_ct.android_xt.relaxfragment.RelaxFragment;
import com.treasure_ct.android_xt.slidingfragment.SlidingMainLeftFragment;
import com.treasure_ct.android_xt.slidingfragment.SlidingMainRightFragment;
import com.treasure_ct.android_xt.studyfragment.StudyFragment;
import com.treasure_ct.android_xt.welcomfragment.WelcomeFragment;

public class MainActivity extends SlidingFragmentActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup radioGroup;
    private RadioButton rb01,rb02,rb03,rb04,rb05;
    private int bottom_id = -1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = (RadioGroup) findViewById(R.id.main_rG);
        rb01 = (RadioButton) findViewById(R.id.main_rB01);
        rb02 = (RadioButton) findViewById(R.id.main_rB02);
        rb03 = (RadioButton) findViewById(R.id.main_rB03);
        rb04 = (RadioButton) findViewById(R.id.main_rB04);
        rb05 = (RadioButton) findViewById(R.id.main_rB05);
        rb01.setTextColor(R.color.colorPrimaryDark);
        if (radioGroup != null){
            radioGroup.setOnCheckedChangeListener(this);
        }
        initSlidingMenu();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame,new StudyFragment()).commit();
        initRBtextColor();
        rb03.setTextColor(R.color.mylove);
        rb03.setChecked(true);
    }

    private void initRBtextColor() {
        rb01.setTextColor(R.color.black);
        rb02.setTextColor(R.color.black);
        rb03.setTextColor(R.color.black);
        rb04.setTextColor(R.color.black);
        rb05.setTextColor(R.color.black);
    }

    private void initSlidingMenu() {
        SlidingMainLeftFragment leftFragment = new SlidingMainLeftFragment();
        setBehindContentView(R.layout.fragment_slidingmenu_left_fragm);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.slidingmenu_left_fragm,leftFragment).commit();

        //设置侧滑菜单
        SlidingMenu menu = getSlidingMenu();
        menu.setMode(SlidingMenu.LEFT_RIGHT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.slidingmenu_shadow_item);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0.35f);

        //设置二级侧滑菜单
        menu.setSecondaryMenu(R.layout.fragment_slidingmenu_right_fragm);
        SlidingMainRightFragment rightFragment = new SlidingMainRightFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.slidingmenu_right_fragm,rightFragment).commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (checkedId) {
            case R.id.main_rB01:
                transaction.replace(R.id.main_frame,new WelcomeFragment());
                initRBtextColor();
                rb01.setTextColor(R.color.mylove);
                bottom_id = 0;
                break;
            case R.id.main_rB02:
                transaction.replace(R.id.main_frame,new RelaxFragment());
                initRBtextColor();
                rb02.setTextColor(R.color.mylove);
                bottom_id = 1;
                break;
            case R.id.main_rB03:
                transaction.replace(R.id.main_frame,new StudyFragment());
                initRBtextColor();
                rb03.setTextColor(R.color.mylove);
                bottom_id =2;
                break;
            case R.id.main_rB04:
                transaction.replace(R.id.main_frame,new CartFragment());
                initRBtextColor();
                rb04.setTextColor(R.color.mylove);
                bottom_id = 3;
                break;
            case R.id.main_rB05:
                transaction.replace(R.id.main_frame,new MineFragment());
                initRBtextColor();
                rb05.setTextColor(R.color.mylove);
                bottom_id = 4;
                break;
        }
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (bottom_id != -1){
            if (bottom_id != 2){
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_frame,new StudyFragment()).commit();
                initRBtextColor();
                rb03.setTextColor(R.color.mylove);
                bottom_id =2;
                rb03.setChecked(true);
            }else {
                super.onBackPressed();
            }
        }
    }
}