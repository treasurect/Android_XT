package com.treasure_ct.android_xt.studyfragment.simpledemo.file_explorer_xt;

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
    private EditText lo_pass;
    private Button btnlogin;
    private SharedPreferences perferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simpledemo_fileexploer_login);
        lo_pass = (EditText) findViewById(R.id.login_pass);
        btnlogin = (Button) findViewById(R.id.btn_login);
        perferences = getSharedPreferences("userinfo",MODE_PRIVATE);
        btnlogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                String s1 = lo_pass.getText().toString();
                String s2 = perferences.getString("password",null);
                if (s1.equals(s2)){
                    Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this,File_ExplorerActivity.class));
                    lo_pass.setText("");
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this, "密码错误，请重新输入", Toast.LENGTH_SHORT).show();
                    lo_pass.setText("");
                }
                break;
        }
    }
}
