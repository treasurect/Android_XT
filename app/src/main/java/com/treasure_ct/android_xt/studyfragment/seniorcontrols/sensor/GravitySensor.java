package com.treasure_ct.android_xt.studyfragment.seniorcontrols.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.treasure_ct.android_xt.R;

import java.util.List;

public class GravitySensor extends AppCompatActivity implements SensorEventListener{
    private SensorManager manager;
    private TextView textInfo;
    private Sensor gravitySensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seniorcontrols_gravity_sensor);
        textInfo = (TextView) findViewById(R.id.gravity_senior_text1);

        //1.获取SensorManager
        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //2.测试部分：获取所有的传感器，显示出来
        if (manager != null){
            //使用重力传感器
            gravitySensor = manager.getDefaultSensor(Sensor.TYPE_GRAVITY);
            if (gravitySensor != null){
                manager.registerListener(this,gravitySensor,Sensor.TYPE_GRAVITY);
            }
        }
    }

    /**
     * 当程序采样传感器的数值，数据就会被封装成Sensor Event传递给这个方法
     * 根据这个方法的持续调用，就可以了解数据的变化
     * @param event
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
        //处理传感器数据，  1.检查传感器的类型   2.处理传感器的数值
        int type = event.sensor.getType();
        if (type == Sensor.TYPE_GRAVITY){
            processGravity(event);
        }
    }
    //重力传感器
    private void processGravity(SensorEvent event) {
        float[] values = event.values;
        float gx = values[0];
        float gy = values[1];
        float gz = values[2];
        StringBuilder builder = new StringBuilder();
        builder.append("GX:").append(gx).append('\n');
        builder.append("GY:").append(gy).append('\n');
        builder.append("GZ:").append(gz).append('\n');
        String s = builder.toString();
        textInfo.setText(s);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onDestroy() {
        if (manager != null){
            //取消特定传感器的监听，如果监听器还有其他传感器，仍然可以使用，不会取消
            manager.unregisterListener(this,gravitySensor);
        }
        //只有this，则取消监听器处理的所有传感器
//        manager.unregisterListener(this);
        super.onDestroy();
    }
}
