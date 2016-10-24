package com.treasure_ct.android_xt.studyfragment.corecontrols.viewpager.loading;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.treasure_ct.android_xt.R;

import java.util.List;

/**
 * Created by treasure on 2016.09.14.
 */

/**
 * PagerAdapter 实现
 *  1.必须要重写、实现四个方法，不止两个
 */
public class LoadingPageAdapter extends PagerAdapter {
    private List<Integer> imageResourceId;
    private Context context;
    private View.OnClickListener mListener;

    public void JumpNext(View.OnClickListener listener) {
        mListener = listener;
    }

    public LoadingPageAdapter(Context context, List<Integer> imageResourceId) {
        this.context = context;
        this.imageResourceId = imageResourceId;
    }

    //返回ViewPager一共有多少页
    @Override
    public int getCount() {
        int ret = 0;
        if (imageResourceId.size() != 0){
            ret = imageResourceId.size();
        }
        return ret;
    }

    /**
     * View参数是否和object有关系
     * ViewPager会进行对象的管理，利用对象来管理和定位View
     * @param view
     * @param object 这个参数，就是instantiateItem返回值对象
     * @return
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        //TODO:
        return view == object;
    }

    /**
     * 当ViewPager需要显示一页的时候，会调用这个方法，传递指定页数
     *
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //加载布局或者创建控件，然后添加到ViewPager
        View ret = null;
        if (position == (imageResourceId.size() - 1)){
            ret = LayoutInflater.from(context).inflate(R.layout.activity_corecontrols_viewpager_welcome_item,container,false);
            ImageView imageView = (ImageView) ret.findViewById(R.id.welcome_image);
            imageView.setBackgroundResource(imageResourceId.get(position));
            TextView textView = (TextView) ret.findViewById(R.id.welcome_text);
            textView.setOnClickListener(mListener);
        }else {
            ImageView imageView = new ImageView(context);
            imageView.setBackgroundResource(imageResourceId.get(position));
            //必须把创建的视图手动添加到ViewGroup
            ret = imageView;
        }
        container.addView(ret);
        return ret;
    }

    /**
     * 当ViewPager把一个页面，左右移动的时候，达到一个限制的位置，就会删除这个页面
     * 调用这个方法
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //必须把手动的创建的View，从ViewGroup删除
        container.removeView(((View) object));
    }
}
