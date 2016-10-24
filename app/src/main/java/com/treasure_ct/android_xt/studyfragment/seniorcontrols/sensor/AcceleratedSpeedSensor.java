package com.treasure_ct.android_xt.studyfragment.seniorcontrols.sensor;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.AnimationSet;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.treasure_ct.android_xt.R;

import java.util.List;

public class AcceleratedSpeedSensor extends AppCompatActivity implements SensorEventListener{
    private SensorManager manager;
    private TextView textInfo;
    private Sensor acceleratedSensor;
    private int mShakeCount = 0;
//    private MediaPlayer player;
    private SoundPool soundPool;
    private ImageView img1,img2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seniorcontrols_accelerated_speed_sensor);
        textInfo = (TextView) findViewById(R.id.accelerated_senior_text1);
        img1 = (ImageView) findViewById(R.id.accelerated_senior_img1);
        img2 = (ImageView) findViewById(R.id.accelerated_senior_img2);

        //1.获取SensorManager
        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //2.测试部分：获取所有的传感器，显示出来
        if (manager != null){
            //使用重力传感器
            acceleratedSensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            if (acceleratedSensor != null){
                manager.registerListener(this,acceleratedSensor,Sensor.TYPE_ACCELEROMETER);
            }
        }
//        player = MediaPlayer.create(this,R.raw.ring_weixin);
         soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC,5);
        soundPool.load(this,R.raw.ring_weixin,1);
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
        if (type == Sensor.TYPE_ACCELEROMETER){
            processAccelerated(event);
        }
    }
    //加速度传感器
    private void processAccelerated(SensorEvent event) {
        float[] values = event.values;
        float x = values[0];
        float y = values[1];
        float z = values[2];
        StringBuilder builder = new StringBuilder();
        builder.append("AX:").append(x).append('\n');
        builder.append("AY:").append(y).append('\n');
        builder.append("AZ:").append(z).append('\n');
        String s = builder.toString();
        textInfo.setText(s);
        float ax = Math.abs(x);
        float ay = Math.abs(y);
        float az = Math.abs(z);
        if (ax >= 20 || ay >= 20 || az >= 20){
            mShakeCount ++;
            if (mShakeCount > 6){
                Toast.makeText(this, "摇一摇耶耶耶耶耶耶耶耶耶耶耶", Toast.LENGTH_SHORT).show();
//                player.start();
                soundPool.play(1,1,1,0,0,1);
                setAnimation();
                mShakeCount = 0;
            }
        }
    }

    private void setAnimation() {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(img1, "transformY", 0, -400).setDuration(1000);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(img2, "transformY", 0, 400).setDuration(1000);
        animatorSet.playTogether(animator1,animator2);
        animatorSet.start();
        AnimatorSet animatorSet1 = new AnimatorSet();
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(img1, "transformY", 0, -400).setDuration(1000);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(img2, "transformY", 0, 400).setDuration(1000);
        animatorSet1.playTogether(animator3,animator4);
        animatorSet1.start();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onDestroy() {
//        if (player != null){
//            player.release();
//            player = null;
//        }
        soundPool.stop(1);
        if (manager != null){
            //取消特定传感器的监听，如果监听器还有其他传感器，仍然可以使用，不会取消
            manager.unregisterListener(this,acceleratedSensor);
        }
        //只有this，则取消监听器处理的所有传感器
//        manager.unregisterListener(this);
        super.onDestroy();
    }
}
