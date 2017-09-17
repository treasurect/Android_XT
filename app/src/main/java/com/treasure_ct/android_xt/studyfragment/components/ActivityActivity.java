package com.treasure_ct.android_xt.studyfragment.components;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.components.activity.Activity_Ceshi_Activity;

/**
 * android四大组件分别为activity、service、content provider、broadcast receiver。
 * activity的生命周期
 * android中不要出现静态属性，因为值很容易变换
 * 空进程（系统扫描到就挂），后台执行activity进程（资源不足)，后台service进程，前台运行activity进程，系统进程(手机重启)
 *
 */

/**
 * Created by treasure on 2016.08.29.
 */
public class ActivityActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn1,btn2,btn3,btn4;
    private EditText edit;
    private long time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_components_activity);

//        Activity_Base_Application application = (Activity_Base_Application) getApplication();
//        String text = application.getText();
//        Toast.makeText(ActivityActivity.this, text, Toast.LENGTH_SHORT).show();
//        application.setText("启动过一次后");

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        edit = (EditText) findViewById(R.id.edit_text);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        if (SystemClock.uptimeMillis() - time < 3000){
            finish();
//            finishAffinity();//api16以上可以用
//            ActivityCompat.finishAffinity(this);//退出所有的activity，api4以上都可以用
//            System.exit(0);//退出应用，包括activity service 。。。。
//            Process.killProcess(Process.myPid());//杀死进程
        }else {
            Toast.makeText(ActivityActivity.this, "再点一次退出", Toast.LENGTH_SHORT).show();
        }
        time = SystemClock.uptimeMillis();
    }

    /**
     * 四种启动模式
     * stanard ： 123123    返回 321321
     *          1231
     * singleInstance： 123123   返回 31312
     * @param view
     */

    /**
     * Intent的7种模式
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn1:
                 break;
            case R.id.btn2:
                finish();
                break;
            case R.id.btn3:
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("name",edit.getText().toString());
                intent.setClass(ActivityActivity.this,Activity_Ceshi_Activity.class);
                intent.putExtras(bundle);
                Log.d("avd",edit.getText().toString());
//                startActivity(intent);
                startActivityForResult(intent,0);
                break;
            case R.id.btn4:

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 0:
                if (resultCode ==RESULT_OK ){
                    String extra = data.getStringExtra("data");
                    Toast.makeText(ActivityActivity.this, "返回的数据"+extra, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
