package com.treasure_ct.android_xt.welcomfragment.chat;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.treasure_ct.android_xt.R;

public class ChatRegisterActivity extends AppCompatActivity {

    private EditText userName;
    private EditText userPass;
    private EditText userPassRe;
    private String name;
    private String pass;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 200:
                    Toast.makeText(ChatRegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_register);
        userName = (EditText) findViewById(R.id.chat_userName);
        userPass = (EditText) findViewById(R.id.chat_userPass);
        userPassRe = (EditText) findViewById(R.id.chat_userPassRe);
    }

    public void btnRegisterSend(View view) {
         name = userName.getText().toString().trim();
         pass = userPass.getText().toString().trim();
        String passre = userPassRe.getText().toString().trim();
        if (name.length() >3 && pass.length() > 3 && passre.length() > 3 && pass.equals(passre)){
            //TODO:注册账号
           new Thread() {
                @Override
                public void run() {
                    //同步代码
                    try {
                        EMClient.getInstance().createAccount(name,pass);
                        Message message = mHandler.obtainMessage(200);
                        mHandler.sendMessage(message);
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}
