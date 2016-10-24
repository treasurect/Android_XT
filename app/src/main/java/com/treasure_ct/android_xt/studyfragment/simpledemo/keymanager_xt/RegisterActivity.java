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

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText pass,repass;
    private Button save;
    private SharedPreferences password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simpledemo_keymanager_register);
        pass = (EditText) findViewById(R.id.userpass);
        repass = (EditText) findViewById(R.id.repass);
        save = (Button) findViewById(R.id.save);
         password = getSharedPreferences("keymg_password", MODE_PRIVATE);
        save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String s1 = pass.getText().toString();
        String s2 = repass.getText().toString();
        if (s1 == null || s2 == null){
            Toast.makeText(RegisterActivity.this, "密码不可为空", Toast.LENGTH_SHORT).show();
        }else if (!s1.equals(s2)){
            Toast.makeText(RegisterActivity.this, "两次密码输入不一致，请重新输入", Toast.LENGTH_SHORT).show();
            pass.setText("");
            repass.setText("");
        }else if(s1.equals(s2)){
            Toast.makeText(RegisterActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor edit = password.edit();
            edit.putString("password",s1);
            edit.apply();
            pass.setText("");
            repass.setText("");
            finish();
            startActivity(new Intent(this,KeyManagerActivity.class));
            Toast.makeText(RegisterActivity.this, "长摁可以删除哦", Toast.LENGTH_LONG).show();
        }
    }
}
