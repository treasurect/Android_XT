package com.treasure_ct.android_xt.studyfragment.simplecontrols;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.treasure_ct.android_xt.R;

/**
 * Created by treasure on 2016.08.26.
 */
public class RadioButtonActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup group;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simplecontrols_radiobutton);
        group = (RadioGroup) findViewById(R.id.radiogroup);
        ColorStateList list = getResources().getColorStateList(R.drawable.simpel_checkbox_bgsel);
        for (int i = 0; i < group.getChildCount(); i++) {
            RadioButton button = (RadioButton) group.getChildAt(i);
            Drawable drawable = button.getCompoundDrawables()[1];
            DrawableCompat.setTintList(drawable,list);
            DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_ATOP);
//            button.setId(i);
        }
        group.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        group.clearCheck();
//        group.check(2);
    }
}
