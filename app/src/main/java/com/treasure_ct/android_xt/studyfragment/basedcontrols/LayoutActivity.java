package com.treasure_ct.android_xt.studyfragment.basedcontrols;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.basedcontrols.layout.CalculatorLayout2Activity;
import com.treasure_ct.android_xt.studyfragment.basedcontrols.layout.CalculatorLayoutActivity;
import com.treasure_ct.android_xt.studyfragment.basedcontrols.layout.LinearLayoutActivity;
import com.treasure_ct.android_xt.studyfragment.basedcontrols.layout.PercentRelativeLayoutActivity;
import com.treasure_ct.android_xt.studyfragment.basedcontrols.layout.V7GridLayoutActivity;
import com.treasure_ct.android_xt.studyfragment.basedcontrols.layout.FrameLayoutActivity;
import com.treasure_ct.android_xt.studyfragment.basedcontrols.layout.RelativeLayoutActivity;

/**
 * Created by treasure on 2016.08.24.
 */
public class LayoutActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn1,btn2,btn3,btn4,btn5,btn6,btn7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basedcontrols_layout);
        btn1 = (Button) findViewById(R.id.btn_layout1);
        btn2 = (Button) findViewById(R.id.btn_layout2);
        btn3 = (Button) findViewById(R.id.btn_layout3);
        btn4 = (Button) findViewById(R.id.btn_layout4);
        btn5 = (Button) findViewById(R.id.btn_layout5);
        btn6 = (Button) findViewById(R.id.calculator);
        btn7 = (Button) findViewById(R.id.calculator2);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_layout1:
                Intent intent = new Intent().setClass(LayoutActivity.this, FrameLayoutActivity.class);
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
                ActivityCompat.startActivity(this,intent,compat.toBundle());
                break;
            case R.id.btn_layout2:
                Intent intent1 = new Intent().setClass(LayoutActivity.this, LinearLayoutActivity.class);
                ActivityOptions compat1 = ActivityOptions.makeClipRevealAnimation(view,0, 0,0, 0 );
                startActivity(intent1,compat1.toBundle());
                break;
            case R.id.btn_layout3:
                startActivity(new Intent().setClass(LayoutActivity.this, RelativeLayoutActivity.class));
                break;
            case R.id.btn_layout4:
                startActivity(new Intent().setClass(LayoutActivity.this, V7GridLayoutActivity.class));
                break;
            case R.id.btn_layout5:
                startActivity(new Intent().setClass(LayoutActivity.this, PercentRelativeLayoutActivity.class));
                break;
            case R.id.calculator:
                startActivity(new Intent().setClass(LayoutActivity.this, CalculatorLayoutActivity.class));
                break;
            case R.id.calculator2:
                startActivity(new Intent().setClass(LayoutActivity.this, CalculatorLayout2Activity.class));
                break;
        }
    }
}
