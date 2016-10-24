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

public class Register_Activity extends AppCompatActivity implements View.OnClickListener {
    private EditText pass,repass;
    private Button setting;
    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simpledemo_fileexplorer_register);
        pass = (EditText) findViewById(R.id.pass);
        repass = (EditText) findViewById(R.id.repass);
        setting = (Button) findViewById(R.id.setting);
         preferences = getSharedPreferences("userinfo", MODE_PRIVATE);
        setting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting:
                String s1 = pass.getText().toString();
                String s2 = repass.getText().toString();
                if (s1.equals("")||s2.equals("")){
                    Toast.makeText(Register_Activity.this, "不要忘了输入密码喔！", Toast.LENGTH_SHORT).show();
                }else if(!s1.equals(s2)){
                    Toast.makeText(Register_Activity.this, "两次输入的密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
                    pass.setText("");
                    repass.setText("");
                }else if(s1.equals(s2)){
                    Toast.makeText(Register_Activity.this, "密码已保存，正在登陆", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("password",pass.getText().toString());
                    editor.apply();
                    startActivity(new Intent(this,File_ExplorerActivity.class));
                    pass.setText("");
                    repass.setText("");
                    finish();
                }
                break;
        }
    }
}
