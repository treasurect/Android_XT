package com.treasure_ct.android_xt.studyfragment.seniorcontrols;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.seniorcontrols.eventdistribution.EventTransActivity;
import com.treasure_ct.android_xt.studyfragment.seniorcontrols.eventdistribution.Scroll_ListView_Finish1;
import com.treasure_ct.android_xt.studyfragment.seniorcontrols.eventdistribution.Scroll_ListView_Finish2;
import com.treasure_ct.android_xt.studyfragment.seniorcontrols.eventdistribution.Scroll_HScroll_Finish;

public class EventDestributionActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seniorcontrols_eventdestribution);
        Button event_trans = (Button) findViewById(R.id.event_trans);
        Button Scroll_List1 = (Button) findViewById(R.id.Scroll_List1);
        Button Scroll_List2 = (Button) findViewById(R.id.Scroll_List2);
        Button Scroll_HSroll = (Button) findViewById(R.id.Scroll_HSroll);
        event_trans.setOnClickListener(this);
        Scroll_List1.setOnClickListener(this);
        Scroll_List2.setOnClickListener(this);
        Scroll_HSroll.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.event_trans:
                startActivity(new Intent(this, EventTransActivity.class));
                break;
            case R.id.Scroll_List1:
                startActivity(new Intent(this, Scroll_ListView_Finish1.class));
                break;
            case R.id.Scroll_List2:
                startActivity(new Intent(this, Scroll_ListView_Finish2.class));
                break;
            case R.id.Scroll_HSroll:
                startActivity(new Intent(this, Scroll_HScroll_Finish.class));
                break;
        }
    }
}
