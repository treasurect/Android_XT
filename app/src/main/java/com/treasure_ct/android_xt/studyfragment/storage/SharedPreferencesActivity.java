package com.treasure_ct.android_xt.studyfragment.storage;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.treasure_ct.android_xt.R;

/**
 * SharePreferences使用规则
 * 存储方式：储存在文件上
 * 存储格式：key-value
 * 存储的内容： 精简
 * 应用场景：配置字段、用户信息
 */
public class SharedPreferencesActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private EditText username,userpass;
    private Button btnlogin;
    private CheckBox checklogin,check_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferecces);
        username = (EditText) findViewById(R.id.share_username);
        userpass = (EditText) findViewById(R.id.share_password);
        btnlogin = (Button) findViewById(R.id.share_login);
        //SharedPreferences使用的时候，应该先看一下是否保存过数据
        SharedPreferences preferences = getSharedPreferences("app", MODE_PRIVATE);
        //检测是否有记住密码的功能，同时设置checkbox的状态变化
        boolean rp = preferences.getBoolean("rememberPass", false);
        checklogin.setChecked(rp);
        checklogin.setOnCheckedChangeListener(this);
        btnlogin.setOnClickListener(this);
        if(rp) {
            String name = preferences.getString("name", null);
            String pass = preferences.getString("pass", null);
            username.setText(name);
            userpass.setText(pass);
        }
    }

    @Override
    public void onClick(View v) {
        SharedPreferences preferences = getSharedPreferences("app", MODE_PRIVATE);
        //保存内容到SharedPreferences
        SharedPreferences.Editor editor = preferences.edit();
        //添加内容到存储区
        editor.putString("name",username.getText().toString());
        editor.putString("pass",userpass.getText().toString());
        //editor 必须要提交   commit（api1） 或 apply（api9以上  推荐）
        editor.apply();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        SharedPreferences preferences = getSharedPreferences("app", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if (isChecked){
            editor.putBoolean("rememberPass",true);
        }else {
            editor.remove("rememberPass");
        }
        editor.apply();

    }
}
