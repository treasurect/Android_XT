package com.treasure_ct.android_xt.studyfragment.corecontrols.sliding;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.corecontrols.sliding.fragment.SlidingMenuLeftFragment;
import com.treasure_ct.android_xt.studyfragment.corecontrols.sliding.fragment.SlidingMenuRightFragment;
import com.treasure_ct.android_xt.studyfragment.corecontrols.sliding.fragment.SlidingMenuTab01;
import com.treasure_ct.android_xt.studyfragment.corecontrols.sliding.fragment.SlidingMenuTab02;
import com.treasure_ct.android_xt.studyfragment.corecontrols.sliding.fragment.SlidingMenuTab03;

import java.util.ArrayList;
/**
 * 第一种方式
 */
//public class SlidingMenu_Activity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_corecontrols_sliding_menu);
//        SlidingMenu menu = new SlidingMenu(this);
//        menu.setMode(SlidingMenu.LEFT);
//        //设置触摸屏幕的模式
//        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
//        menu.setShadowWidthRes(R.dimen.shadow_width);
//        menu.setShadowDrawable(R.drawable.slidingmenu_shadow_item);
//
//        //设置滑动菜单视图的宽度
//        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
//        //设置渐入渐出效果的值
//        menu.setFadeDegree(0.35f);
//        menu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);
//        //为侧滑菜单设置布局
//        menu.setMenu(R.layout.slidingmenu_left_item);
//    }
//}

/**
 * 第二种方式
 */
//public class SlidingMenu_Activity extends SlidingActivity {
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_corecontrols_sliding_menu);
//        setBehindContentView(R.layout.slidingmenu_left_item);
//        SlidingMenu menu = getSlidingMenu();
//        menu.setMode(SlidingMenu.LEFT);
//        //设置触摸屏幕的模式
//        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
//        menu.setShadowWidthRes(R.dimen.shadow_width);
//        menu.setShadowDrawable(R.drawable.slidingmenu_shadow_item);
//
//        //设置滑动菜单视图的宽度
//        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
//        //设置渐入渐出效果的值
//        menu.setFadeDegree(0.35f);
//    }
//}
/**
 * 第三种方式
 */
//public class SlidingMenu_Activity extends AppCompatActivity {
//
//    private SlidingMenu menu;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_corecontrols_sliding_menu);
//        menu = (SlidingMenu) findViewById(R.id.slidingmenu2);
//        if (menu != null) {
//            menu.setMode(SlidingMenu.LEFT);
//            menu.setShadowWidthRes(R.dimen.shadow_width);
//            menu.setShadowDrawable(R.drawable.slidingmenu_shadow_item);
//            menu.setMenu(R.layout.slidingmenu_left_item);
//            menu.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (menu.isMenuShowing()) {
//                        menu.toggle();
//                    }
//                }
//            });
//        }
//    }
//}

public class SlidingMenu_Activity extends SlidingFragmentActivity {

    private ViewPager pager;
    private ArrayList<Fragment> list;
    private FragmentPagerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corecontrols_sliding_menu);
        pager = (ViewPager) findViewById(R.id.slidingmenu_viewpager);
        initSlidingMenu();
        initViewPager();
    }

    private void initSlidingMenu() {
        SlidingMenuLeftFragment leftFragment = new SlidingMenuLeftFragment();
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
        SlidingMenuRightFragment rightFragment = new SlidingMenuRightFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.slidingmenu_right_fragm,rightFragment).commit();
    }

    private void initViewPager() {
        SlidingMenuTab01 tab01 = new SlidingMenuTab01();
        SlidingMenuTab02 tab02 = new SlidingMenuTab02();
        SlidingMenuTab03 tab03 = new SlidingMenuTab03();
         list = new ArrayList<>();
        list.add(tab01);
        list.add(tab02);
        list.add(tab03);
         adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }
        };
        pager.setAdapter(adapter);
    }
}