package com.treasure_ct.android_xt.studyfragment.corecontrols;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.corecontrols.listview.ListView_Array;
import com.treasure_ct.android_xt.studyfragment.corecontrols.listview.ListView_Base;
import com.treasure_ct.android_xt.studyfragment.corecontrols.listview.ListView_Simple1;
import com.treasure_ct.android_xt.studyfragment.corecontrols.listview.ListView_Simple2;

/**
 * Created by treasure on 2016.09.02.
 */
public class ListViewActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn1,btn2,btn3,btn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primarycontrols_listview);
        btn1 = (Button) findViewById(R.id.listview_array);
        btn2 = (Button) findViewById(R.id.listview_sim1);
        btn3 = (Button) findViewById(R.id.listview_sim2);
        btn4 = (Button) findViewById(R.id.listview_base);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.listview_array:
                startActivity(new Intent(this, ListView_Array.class));
                break;
            case R.id.listview_sim1:
                startActivity(new Intent(this, ListView_Simple1.class));
                break;
            case R.id.listview_sim2:
                startActivity(new Intent(this,ListView_Simple2.class));
                break;
            case R.id.listview_base:
                Intent intent = new Intent(this, ListView_Base.class);
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeScaleUpAnimation(v, v.getWidth() / 2, v.getHeight() / 2, 0, 0);
                if (Build.VERSION.SDK_INT >= 16) {
                    startActivity(intent,compat.toBundle());
                }else {
                    startActivity(intent);
                }
                break;
        }
    }
}
