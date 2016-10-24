package com.treasure_ct.android_xt.studyfragment.corecontrols;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.corecontrols.animation.attribute.Attribute_Anim_Activity;
import com.treasure_ct.android_xt.studyfragment.corecontrols.animation.view.View_Anim_Activity;

public class AnimationActivity extends AppCompatActivity implements View.OnClickListener {
    private Button textZan;
    private Button textJia1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corecontrols_animation);
        textZan = (Button) findViewById(R.id.view_anim);
        textJia1 = (Button) findViewById(R.id.attribute_anim);
        textZan.setOnClickListener(this);
        textJia1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_anim:
                startActivity(new Intent(this, View_Anim_Activity.class));
                break;
            case R.id.attribute_anim:
                startActivity(new Intent(this, Attribute_Anim_Activity.class));
                break;
        }
    }
}
