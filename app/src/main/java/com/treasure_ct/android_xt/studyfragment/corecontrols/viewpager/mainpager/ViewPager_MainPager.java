package com.treasure_ct.android_xt.studyfragment.corecontrols.viewpager.mainpager;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.corecontrols.viewpager.tablayout.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPager_MainPager extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {
    private ViewPager pager;
    private RadioGroup mRadioGroup;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corecontrols_viewpager_mainpager);
         pager = (ViewPager) findViewById(R.id.top_viewpager);
        mRadioGroup = (RadioGroup) findViewById(R.id.mainpager_tab_bar);
        textView = (TextView) findViewById(R.id.mainpager_tab_bar_bottom);
        List<BaseFragment> list = new ArrayList<>();
        list.add(new FirstFragment());
        list.add(new SecondFragment());
        list.add(new ThirdFragment());
        list.add(new FourthFragment());
        //参数1 FragmentManager ： 如果在Activity中，穿件的Adapter，必须使用getSupportFragmentManager
        //                          如果在Fragment中，穿件的Adapter，必须使用getChildFragmentManager
        CommonFragmentPagerAdapter adapter = new CommonFragmentPagerAdapter(getSupportFragmentManager(), list);
        pager.setAdapter(adapter);
        //以上  实现了 ViewPager的滑动效果

        //实现了顶部RadioButton的点击，切换Fragment 效果
        mRadioGroup.setOnCheckedChangeListener(this);

        //实现ViewPager滑动  和 RadioButton点击 的联动
        pager.addOnPageChangeListener(this);
    }

    //实现了顶部RadioButton的点击，切换Fragment 效果
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.mainpager_tab_item0:
                pager.setCurrentItem(0,false);
                //第二个参数的设置，防止跨页面点击，依次加载
                break;
            case R.id.mainpager_tab_item1:
                pager.setCurrentItem(1,false);
                break;
            case R.id.mainpager_tab_item2:
                pager.setCurrentItem(2,false);
                break;
            case R.id.mainpager_tab_item3:
                pager.setCurrentItem(3,false);
                break;
        }
    }

    //实现了RadioButton下方滚动条的变化
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        /**
         * 1.获取RG中指定的Child，position对应的Child
         * 2.获取制定指示器控件的坐标
         * 3.计算指示器的位置
         */
        View at = mRadioGroup.getChildAt(position);
        float x = ViewCompat.getX(at);
        x = x + (at.getWidth() * positionOffset);
        ViewCompat.setX(textView,x);
    }

    //实现了 当前选择 的RadioButton属性的变化  比如字体颜色
    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                mRadioGroup.check(R.id.mainpager_tab_item0);
                break;
            case 1:
                mRadioGroup.check(R.id.mainpager_tab_item1);
                break;
            case 2:
                mRadioGroup.check(R.id.mainpager_tab_item2);
                break;
            case 3:
                mRadioGroup.check(R.id.mainpager_tab_item3);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
