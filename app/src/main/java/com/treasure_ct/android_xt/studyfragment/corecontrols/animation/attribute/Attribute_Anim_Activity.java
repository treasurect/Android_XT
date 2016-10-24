package com.treasure_ct.android_xt.studyfragment.corecontrols.animation.attribute;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.treasure_ct.android_xt.R;

public class Attribute_Anim_Activity extends AppCompatActivity implements View.OnClickListener {
    private TextView ceshi;
    private Button move,color,size,own,code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corecontrols_anim_attribute);
        ceshi = (TextView) findViewById(R.id.text_ceshi);
        move = (Button) findViewById(R.id.attr_move);
        color = (Button) findViewById(R.id.attr_color_change);
        size = (Button) findViewById(R.id.attr_size_change);
        own = (Button) findViewById(R.id.attr_own);
        code = (Button) findViewById(R.id.attr_code);
        move.setOnClickListener(this);
        color.setOnClickListener(this);
        size.setOnClickListener(this);
        own.setOnClickListener(this);
        code.setOnClickListener(this);
        ceshi.setRotation(R.animator.animator_move);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.attr_move:
//                float x = ceshi.getTranslationX();
//                ceshi.setTranslationX(x + 100);
                //属性动画的加载
                //1.加载器加载xml文件
                Animator animator = AnimatorInflater.loadAnimator(this, R.animator.animator_move);
                //2.将需要修改的对象传递给属性动画
                animator.setTarget(ceshi);
//                animator.setInterpolator(new OvershootInterpolator());
                //3.动画播放
                animator.start();
                break;
            case R.id.attr_color_change:
//                float x = ceshi.getTranslationX();
//                ceshi.setTranslationX(x + 100);
                //属性动画的加载
                //1.加载器加载xml文件
                Animator animator1 = AnimatorInflater.loadAnimator(this, R.animator.animator_color_change);
                //2.将需要修改的对象传递给属性动画
                animator1.setTarget(ceshi);
                animator1.setInterpolator(new OvershootInterpolator());
                //3.动画播放
                animator1.start();
                break;
            case R.id.attr_size_change:
                Animator animator2 = AnimatorInflater.loadAnimator(this, R.animator.animator_size_change);
                animator2.setTarget(ceshi);
                animator2.setInterpolator(new OvershootInterpolator());
                animator2.start();
                break;
            case  R.id.attr_own:
                /**
                 * 自定义是对象的属性更改
                 */
                Attribute_TextShower shower = new Attribute_TextShower(ceshi);
                Animator animator3 = AnimatorInflater.loadAnimator(this, R.animator.animator_text_show);
                //设置目标
                animator3.setTarget(shower);
                animator3.start();
                break;
            case R.id.attr_code:
                //代码形式的属性动画
                //所有属性的数值，如果位置则以像素为单位
                AnimatorSet animatorSet = new AnimatorSet();
                ObjectAnimator animator4 = ObjectAnimator.ofFloat(ceshi, "translationX", 0, 400).setDuration(2000);
                ObjectAnimator animator5 = ObjectAnimator.ofFloat(ceshi, "rotation", 0, 1800).setDuration(2000);
                animatorSet.playTogether(animator4,animator5);
                animatorSet.start();
                break;
        }
    }
}
