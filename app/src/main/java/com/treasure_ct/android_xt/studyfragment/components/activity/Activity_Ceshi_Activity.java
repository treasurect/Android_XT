package com.treasure_ct.android_xt.studyfragment.components.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.treasure_ct.android_xt.R;

/**
 * Created by treasure on 2016.08.29.
 */
public class Activity_Ceshi_Activity extends AppCompatActivity implements View.OnClickListener {
    private TextView text,text2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_components_activity_ceshi);
        text = (TextView) findViewById(R.id.text_extra);
        text2 = (TextView) findViewById(R.id.text_app);
        Bundle bundle;
        bundle = getIntent().getExtras();
       text.setText(bundle.getString("name"));
        text.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.putExtra("data","CeshiActivity返回");
        setResult(RESULT_OK,intent);
        finish();
    }
}
