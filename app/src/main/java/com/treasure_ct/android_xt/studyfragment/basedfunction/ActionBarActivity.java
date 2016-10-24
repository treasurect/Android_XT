package com.treasure_ct.android_xt.studyfragment.basedfunction;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.basedfunction.actionbar.ActionBar_Spinner_Activity;
import com.treasure_ct.android_xt.studyfragment.basedfunction.actionbar.ToolBarActivity;
import com.treasure_ct.android_xt.studyfragment.basedfunction.actionbar.ActionBar_Search_Activity;
import com.treasure_ct.android_xt.studyfragment.basedfunction.actionbar.ActionBar_SimpleUse_Activity;

public class ActionBarActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basedfunction_actionbar);
        Button simpleuse = (Button) findViewById(R.id.actionbar_simple_use_btn);
        Button spinner = (Button) findViewById(R.id.actionbar_spinner_btn);
        Button search = (Button) findViewById(R.id.actionbar_search_btn);
        Button toolbar = (Button) findViewById(R.id.actionbar_toolbar_btn);
        simpleuse.setOnClickListener(this);
        search.setOnClickListener(this);
        spinner.setOnClickListener(this);
        toolbar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.actionbar_simple_use_btn:
                startActivity(new Intent(this,ActionBar_SimpleUse_Activity.class));
                break;
            case R.id.actionbar_spinner_btn:
                startActivity(new Intent(this,ActionBar_Spinner_Activity.class));
                break;
            case R.id.actionbar_search_btn:
                startActivity(new Intent(this,ActionBar_Search_Activity.class));
                break;
            case R.id.actionbar_toolbar_btn:
                startActivity(new Intent(this,ToolBarActivity.class));
                break;
        }
    }
}
