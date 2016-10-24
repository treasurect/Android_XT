package com.treasure_ct.android_xt.studyfragment.corecontrols;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.corecontrols.popupwindow.PW_showAtLocationActivity;
import com.treasure_ct.android_xt.studyfragment.corecontrols.popupwindow.PW_showAsDropDownActivity;

public class PopUpWindowActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corecontrols_popup_window);
        TextView showAtLocation = (TextView) findViewById(R.id.popupWindow_showAtLocation);
        TextView showAsDropDown = (TextView) findViewById(R.id.popupWindow_showAsDropDown);
        showAtLocation.setOnClickListener(this);
        showAsDropDown.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.popupWindow_showAtLocation:
                startActivity(new Intent(this, PW_showAtLocationActivity.class));
                break;
            case R.id.popupWindow_showAsDropDown:
                startActivity(new Intent(this, PW_showAsDropDownActivity.class));
                break;
        }
    }
}
