package com.treasure_ct.android_xt.studyfragment.simpledemo.keymanager_xt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.treasure_ct.android_xt.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferences password;
    private EditText pass;
    private Button start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simpledemo_keymanager_login);
         password = getSharedPreferences("keymg_password", MODE_PRIVATE);
        pass = (EditText) findViewById(R.id.start_userpass);
        start = (Button) findViewById(R.id.start);
        start.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String password1 = password.getString("password", null);
        String s = pass.getText().toString();
        if (s == null){
            Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
        }else if (!s.equals(password1)){
            Toast.makeText(LoginActivity.this, "密码不正确，请确认后再输入", Toast.LENGTH_SHORT).show();
            pass.setText("");
        }else if (s.equals(password1)){
            Toast.makeText(LoginActivity.this, "成功登录", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(this,KeyManagerActivity.class));
            Toast.makeText(LoginActivity.this, "长摁可以删除哦", Toast.LENGTH_LONG).show();
        }
    }
}
