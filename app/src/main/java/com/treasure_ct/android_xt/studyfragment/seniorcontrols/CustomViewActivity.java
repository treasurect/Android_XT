package com.treasure_ct.android_xt.studyfragment.seniorcontrols;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.seniorcontrols.customview.BalloonsActivity;
import com.treasure_ct.android_xt.studyfragment.seniorcontrols.customview.CoupleViewGroup;
import com.treasure_ct.android_xt.studyfragment.seniorcontrols.customview.ExtendsView_NotePadEdit;
import com.treasure_ct.android_xt.studyfragment.seniorcontrols.customview.FullSizeActivity;
import com.treasure_ct.android_xt.studyfragment.seniorcontrols.customview.Full_ShapeActivity;
import com.treasure_ct.android_xt.studyfragment.seniorcontrols.customview.Moving_WaterActivity;
import com.treasure_ct.android_xt.studyfragment.seniorcontrols.customview.FlowActivity;
import com.treasure_ct.android_xt.studyfragment.seniorcontrols.customview.FullCustomViewDemo;
import com.treasure_ct.android_xt.studyfragment.seniorcontrols.customview.ExtendView_AliPayEdit;

public class CustomViewActivity extends AppCompatActivity implements View.OnClickListener {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seniorcontrols_destribution_custom_view);
        Button alipay = (Button) findViewById(R.id.extends_alipay);
        Button notepad = (Button) findViewById(R.id.extends_notepad);
        Button phonebook = (Button) findViewById(R.id.couple_phonebook);
        Button flowlayout = (Button) findViewById(R.id.couple_flowlayout);
        Button movingwater = (Button) findViewById(R.id.couple_movingwater);
        Button rectcircle = (Button) findViewById(R.id.fullcustom_rectcircle);
        Button picture = (Button) findViewById(R.id.fullcustom_picture);
        Button simple = (Button) findViewById(R.id.fullcustom_a);
        Button balloon_boom = (Button) findViewById(R.id.balloon_boom);
        alipay.setOnClickListener(this);
        notepad.setOnClickListener(this);
        phonebook.setOnClickListener(this);
        flowlayout.setOnClickListener(this);
        movingwater.setOnClickListener(this);
        rectcircle.setOnClickListener(this);
        picture.setOnClickListener(this);
        simple.setOnClickListener(this);
        balloon_boom.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.extends_alipay:
                startActivity(new Intent(this, ExtendView_AliPayEdit.class));
                break;
            case R.id.extends_notepad:
                startActivity(new Intent(this, ExtendsView_NotePadEdit.class));
                break;
            case R.id.couple_phonebook:
                startActivity(new Intent(this, CoupleViewGroup.class));
                break;
            case R.id.couple_flowlayout:
                startActivity(new Intent(this, FlowActivity.class));
                break;
            case R.id.couple_movingwater:
                startActivity(new Intent(this, Moving_WaterActivity.class));
                break;
            case R.id.fullcustom_rectcircle:
                startActivity(new Intent(this, Full_ShapeActivity.class));
                break;
            case R.id.fullcustom_picture:
                startActivity(new Intent(this, FullCustomViewDemo.class));
                break;
            case R.id.fullcustom_a:
                startActivity(new Intent(this, FullSizeActivity.class));
                break;
            case R.id.balloon_boom:
                startActivity(new Intent(this, BalloonsActivity.class));
                break;
        }
    }
}
