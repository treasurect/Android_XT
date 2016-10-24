package com.treasure_ct.android_xt.studyfragment.simplecontrols;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.treasure_ct.android_xt.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by treasure on 2016.08.28.
 */
public class ScrollViewActivity extends AppCompatActivity implements NestedScrollView.OnScrollChangeListener{
    private NestedScrollView scroll;
    private TextView top;
    private RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simplecontrols_scrollview);
        scroll = (NestedScrollView) findViewById(R.id.scroll);
        top = (TextView) findViewById(R.id.top_text);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        LayoutInflater inflater = LayoutInflater.from(this);
        List<String> list = new ArrayList<>();
        list.add("头条");
        list.add("科技");
        list.add("体育");
        list.add("金融");
        list.add("搞笑");
        list.add("热点");
        list.add("汽车");
        list.add("历史");
        list.add("头条");
        list.add("科技");
        list.add("体育");
        list.add("金融");
        list.add("搞笑");
        list.add("热点");
        list.add("汽车");
        list.add("历史");
        list.add("头条");
        list.add("科技");
        list.add("体育");
        list.add("金融");
        list.add("搞笑");
        list.add("热点");
        list.add("汽车");
        list.add("历史");
        for (String str : list) {
            RadioButton button = (RadioButton) inflater.inflate(R.layout.activity_simplecontrols_scrollview_item, radioGroup, false);
            button.setText(str);
            radioGroup.addView(button);
        }
        scroll.setOnScrollChangeListener(this);
    }

    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        Log.d("abc", String.valueOf(scrollY));
        LinearLayout layout = (LinearLayout) v.getChildAt(0);
        TextView text1 = null;
        TextView text2 = null;
        for (int i = 0; i < layout.getChildCount(); i++) {
            View view = layout.getChildAt(i);
            //getTop 是当前的view距父控件的距离,scrollY是滚头条距上部的距离
            if(view.getTop() > scrollY){
                Log.d("abc", String.valueOf(scrollY)+"    "+view.getTop());
                if(view instanceof TextView){
                    text2 = ((TextView) view);
                }
                break;
            }
            if(view instanceof TextView){
                text1 = ((TextView) view);
            }
        }
        top.setText(text1.getText());
        //优化替换的过程
        if(text2 != null){
            int offset = text2.getTop() - scrollY;
            ViewCompat.setTranslationY(top,Math.min(offset - top.getHeight(),0));
        }else{
            ViewCompat.setTranslationY(top,0);
        }
    }
}
