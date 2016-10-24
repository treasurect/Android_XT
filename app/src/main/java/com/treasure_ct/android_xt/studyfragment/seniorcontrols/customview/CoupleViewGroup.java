package com.treasure_ct.android_xt.studyfragment.seniorcontrols.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.seniorcontrols.customview.widgets.AlphaIndicator;

import java.util.ArrayList;

public class CoupleViewGroup extends AppCompatActivity implements AlphaIndicator.OnIndexSelectedListener {
    private TextView textView;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seniorcontrols_customview_coupleviewgroup);
        textView = (TextView) findViewById(R.id.txt_indicator);
//        指示器接口回调
        AlphaIndicator indicator = (AlphaIndicator) findViewById(R.id.alpha_layout);
        if (indicator != null) {
            indicator.setOnIndexSelectedListener(this);
            listView = (ListView) findViewById(R.id.content_list);
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 10; j++) {
                    list.add("Item" + i + " " + j);
                }
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
            listView.setAdapter(adapter);
        }
    }

    @Override
    public void onIndexSelected(int index) {
        listView.setSelection(index * 10);
        textView.setText(Character.toString((char) ('A' + index)));
    }

    @Override
    public void onFingerUp() {
        textView.setText(null);
    }
}
