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

public class LightSensor extends AppCompatActivity implements SensorEventListener{
    private SensorManager manager;
    private Sensor lightSensor;
    private TextView textView;
    private TextView textInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seniorcontrols_light_sensor);
        textInfo = (TextView) findViewById(R.id.light_senior_text1);
        textView = (TextView) findViewById(R.id.light_senior_text2);

        //1.获取SensorManager
         manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //2.测试部分：获取所有的传感器，显示出来
        if (manager != null){
            List<Sensor> sensorList = manager.getSensorList(Sensor.TYPE_ALL);
            StringBuilder builder = new StringBuilder("传感器类型：\n");
            builder.append("传感器的数目为："+sensorList.size()).append('\n');
            for (Sensor sensor : sensorList) {
                builder.append(sensor.toString()).append('\n');
            }
            String s = builder.toString();
            textView.setText(s);

            //使用亮度传感器
            lightSensor = manager.getDefaultSensor(Sensor.TYPE_LIGHT);
            if (lightSensor != null){
                //传感器获取之后一定判断下是否存在
                manager.registerListener(this, lightSensor,SensorManager.SENSOR_DELAY_NORMAL);
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
        if (type == Sensor.TYPE_LIGHT) {
            processLight(event);
        }
    }

    //光传感器
    private void processLight(SensorEvent event) {
        float[] values = event.values;
        //3.亮度数值只有 一个元素， 根据Sensor Manager常量来进行处理
        float light = values[0];
        textInfo.setText("当前屏幕的亮度值为：     "+String.valueOf(light));
        float screenLight = 1;
        if (light >= SensorManager.LIGHT_NO_MOON && light < SensorManager.LIGHT_FULLMOON){
            screenLight = 0.1f;
        }else if (light >= SensorManager.LIGHT_FULLMOON && light < SensorManager.LIGHT_CLOUDY){
            screenLight = 0.2f;
        }else if (light >= SensorManager.LIGHT_CLOUDY && light < SensorManager.LIGHT_SUNRISE){
            screenLight = 0.3f;
        }else if (light >= SensorManager.LIGHT_SUNRISE && light < SensorManager.LIGHT_OVERCAST){
            screenLight = 0.4f;
        }else if (light >= SensorManager.LIGHT_OVERCAST && light < SensorManager.LIGHT_SHADE){
            screenLight = 0.6f;
        }else if (light >= SensorManager.LIGHT_SHADE && light < SensorManager.LIGHT_SUNLIGHT){
            screenLight = 0.8f;
        }else if (light >= SensorManager.LIGHT_SUNLIGHT && light < SensorManager.LIGHT_SUNLIGHT_MAX){
            screenLight = 1;
        }
        //调正屏幕亮度
        adjustScreenLight(screenLight);
    }
    //调整屏幕亮度
    private void adjustScreenLight(float l) {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.3f;
        params.screenBrightness = l;
        getWindow().setAttributes(params);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onDestroy() {
        if (manager != null){
            //取消特定传感器的监听，如果监听器还有其他传感器，仍然可以使用，不会取消
            manager.unregisterListener(this,lightSensor);
        }
        //只有this，则取消监听器处理的所有传感器
//        manager.unregisterListener(this);
        super.onDestroy();
    }
}
