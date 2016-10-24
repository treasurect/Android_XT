package com.treasure_ct.android_xt.studyfragment.corecontrols;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.corecontrols.recyclerview.RecyclerViewGridHorActivity;
import com.treasure_ct.android_xt.studyfragment.corecontrols.recyclerview.RecyclerViewGridVerActivity;
import com.treasure_ct.android_xt.studyfragment.corecontrols.recyclerview.RecyclerViewListActivity;

public class RecyclerViewActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corecontrols_recyclerview_main);
        Button list = (Button) findViewById(R.id.recyclerview_list);
        Button girdVer = (Button) findViewById(R.id.recyclerview_girdVer);
        Button girdHor = (Button) findViewById(R.id.recyclerview_girdHor);
        list.setOnClickListener(this);
        girdVer.setOnClickListener(this);
        girdHor.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.recyclerview_list:
                startActivity(new Intent(this, RecyclerViewListActivity.class));
                break;
            case R.id.recyclerview_girdVer:
                startActivity(new Intent(this, RecyclerViewGridVerActivity.class));
                break;
            case R.id.recyclerview_girdHor:
                startActivity(new Intent(this, RecyclerViewGridHorActivity.class));
                break;
        }
    }
}
