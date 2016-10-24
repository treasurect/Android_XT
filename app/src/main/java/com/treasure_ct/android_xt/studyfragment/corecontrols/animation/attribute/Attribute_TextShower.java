package com.treasure_ct.android_xt.studyfragment.corecontrols.animation.attribute;

import android.util.Log;
import android.widget.TextView;

/**
 * Created by treasure on 2016.09.12.
 */
public class Attribute_TextShower {
    private TextView textView;
    private String[] strings = {"A","B","C"};

    public Attribute_TextShower(TextView textView) {
        this.textView = textView;
    }

    public void setIndex(int index){
        Log.d("-------------------", "setIndex: "+index);
        textView.setText(strings[index % strings.length]);
    }
}
