package com.treasure_ct.android_xt.studyfragment.seniorcontrols;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.seniorcontrols.sensor.AcceleratedSpeedSensor;
import com.treasure_ct.android_xt.studyfragment.seniorcontrols.sensor.GravitySensor;
import com.treasure_ct.android_xt.studyfragment.seniorcontrols.sensor.LightSensor;

public class SensorActivty extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seniorcontrols_sensor_main);
        Button light = (Button) findViewById(R.id.btn_light);
        Button gravity = (Button) findViewById(R.id.btn_gravity);
        Button accelerated = (Button) findViewById(R.id.btn_accelerated);
        light.setOnClickListener(this);
        gravity.setOnClickListener(this);
        accelerated.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_light:
                startActivity(new Intent(this, LightSensor.class));
                break;
            case R.id.btn_gravity:
                startActivity(new Intent(this, GravitySensor.class));
                break;
            case R.id.btn_accelerated:
                startActivity(new Intent(this, AcceleratedSpeedSensor.class));
                break;
        }
    }
}
