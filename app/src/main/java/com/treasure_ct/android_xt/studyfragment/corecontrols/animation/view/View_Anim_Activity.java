package com.treasure_ct.android_xt.studyfragment.corecontrols.animation.view;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.treasure_ct.android_xt.R;

public class View_Anim_Activity extends AppCompatActivity implements View.OnClickListener, Animation.AnimationListener {
    private TextView textZan;
    private TextView textJia1;
    private int zanCount;
    private ImageView anim_image;
    private Button ani_btn,gift_btn,gift_stop;
    private ImageView heart_image,gift_image;
    private Button heart_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corecontrols_anim_view);
        textZan = (TextView) findViewById(R.id.ani_zan);
        textJia1 = (TextView) findViewById(R.id.ani_jia1);
        anim_image = (ImageView) findViewById(R.id.ani_image);
        ani_btn = (Button) findViewById(R.id.ani_btn);
        heart_btn = (Button) findViewById(R.id.scale_btn);
        gift_btn = (Button) findViewById(R.id.gift_btn);
        gift_stop = (Button) findViewById(R.id.gift_stop_btn);
        heart_image = (ImageView) findViewById(R.id.heart_scale);
        gift_image = (ImageView) findViewById(R.id.gift_image);
        textJia1.setVisibility(View.INVISIBLE);
        zanCount = 0;
        textZan.setOnClickListener(this);
        anim_image.setOnClickListener(this);
        ani_btn.setOnClickListener(this);
        heart_btn.setOnClickListener(this);
        gift_btn.setOnClickListener(this);
        gift_stop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ani_zan:
                //1. 加载动画
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.corecontrols_anim_inc);
                //3. 设置动画监听器，当动画开始、完成、重复时，进行回调
                animation.setAnimationListener(this);
                //4. 控件播放之前，setVisibility(View.VISIBLE);
                textJia1.setVisibility(View.VISIBLE);
                //2. 播放动画
                textJia1.startAnimation(animation);
                break;
            case R.id.ani_image:
                Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.corecontrols_anim_rotate);
                //设置插值器，平滑旋转
                animation1.setInterpolator(new LinearInterpolator());
                anim_image.startAnimation(animation1);
                break;
            case R.id.ani_btn:
                Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.corecontrols_anim_move);
                animation2.setDuration(1000);
                animation2.setInterpolator(new LinearInterpolator());
                ani_btn.startAnimation(animation2);
                break;
            case R.id.scale_btn:
                if (heart_image != null){
                    Animation animation3 = AnimationUtils.loadAnimation(this, R.anim.corecontrols_anim_heartbeat);
                    animation3.setDuration(1000);
                    //设置第一次执行完之后，循环播放的次数
                    animation3.setRepeatCount(Animation.INFINITE);
                    // Defines what this animation should do when it reaches the end.
                    animation3.setRepeatMode(Animation.REVERSE);
                    heart_image.startAnimation(animation3);
                }
                break;
            case R.id.gift_btn:
                //          TODO:播放动画
                //  1.因为逐帧动画是图片，所以要冲ImageView中取出来
                //  获取src属性
                Drawable drawable = gift_image.getDrawable();
                // shape -> shapeDrawable
                //selector - > StateListDrawable
                //animation-list -> AnimationDrawable
                if (drawable != null && drawable instanceof AnimationDrawable){
                    //  2.播放动画
                    AnimationDrawable animationDrawable = (AnimationDrawable) drawable;
                    animationDrawable.start();
                }
                break;
            case R.id.gift_stop_btn:
                Drawable drawable1 = gift_image.getDrawable();
                if (drawable1 != null && drawable1 instanceof AnimationDrawable) {
                    AnimationDrawable animationDrawable = (AnimationDrawable) drawable1;
                    //stop会将动画暂停，再次播放会从头开始
                    animationDrawable.stop();
                }
                break;
        }

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        textJia1.setVisibility(View.INVISIBLE);
        zanCount++;
        textZan.setText("赞"+" +"+zanCount);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
