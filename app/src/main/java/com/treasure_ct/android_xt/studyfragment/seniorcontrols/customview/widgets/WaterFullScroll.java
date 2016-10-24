package com.treasure_ct.android_xt.studyfragment.seniorcontrols.customview.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ViewFlipper;

/**
 * Created by treasure on 2016.09.29.
 */

public class WaterFullScroll extends ScrollView{
    public WaterFullScroll(Context context) {
        this(context,null);
    }

    public WaterFullScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }
    public void init(Context context,AttributeSet attrs){

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        TextView textView = new TextView(context);
        textView.setText("hello世界");
        for (int i = 0; i < 20; i++) {

            linearLayout.addView(textView);
        }
        addView(linearLayout);
    }

}
