package com.treasure_ct.android_xt.studyfragment.seniorcontrols;

import android.content.Context;
import android.graphics.Color;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.treasure_ct.android_xt.R;

public class ModelAdaptationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_adaptation);
        TextView textView = (TextView) findViewById(R.id.screen_dpi);
        TextView textView2= (TextView) findViewById(R.id.screen_dpi2);
        TextView textView3 = (TextView) findViewById(R.id.screen_dpi3);
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            int screen_width = metrics.widthPixels;//屏幕宽度
            int screen_height = metrics.heightPixels;//屏幕高度
            int densityDpi = metrics.densityDpi; //dpi
            float density = metrics.density; //density
            float xdpi = metrics.xdpi;
            float ydpi = metrics.ydpi;
            float height_dp = screen_height / metrics.density;
            float width_dp = screen_width / metrics.density;
            float scaledDensity = metrics.scaledDensity;
            textView.setText("横向像素值："+screen_width+"   纵向像素值："+screen_height);
            textView2.setText("屏幕dpi："+densityDpi+"   density："+density +"     xdpi:"+xdpi +"   ydpi:"+ydpi);
            textView3.setText("宽度的dp长度："+width_dp+"      高度的dp长度："+height_dp+"         scaledDensity"+scaledDensity);

        ViewGroup layout = (ViewGroup) findViewById(R.id.layout);
        TextView view = new TextView(this);
        // LayoutParams 所有的Android标准容器,都有自己的LayoutParams
        // 控件添加到哪种容器类型,就使用这个类型的LayoutParams
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int)dp2px(this, 400), (int)dp2px(this, 50));
        view.setLayoutParams(params);
        view.setText("这是一个什么东西");
//        view.setGravity(Gravity.CENTER);
        view.setTextColor(Color.BLUE);
        layout.addView(view);
    }
    public static float dp2px(Context context,int dp){
        DisplayMetrics metrics = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= 17) {
            DisplayManager systemService = (DisplayManager) context.getSystemService(DISPLAY_SERVICE);
            systemService.getDisplay(0).getMetrics(metrics);
        }else {
            WindowManager systemService = (WindowManager) context.getSystemService(WINDOW_SERVICE);
            systemService.getDefaultDisplay().getMetrics(metrics);
        }
        float px = dp * metrics.density;
        return px;
    }
}
