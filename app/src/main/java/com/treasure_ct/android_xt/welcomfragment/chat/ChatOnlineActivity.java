package com.treasure_ct.android_xt.welcomfragment.chat;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.treasure_ct.android_xt.R;

public class ChatOnlineActivity extends AppCompatActivity {
    private EditText userName;
    private EditText userPass;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 200:
                    Toast.makeText(ChatOnlineActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ChatOnlineActivity.this, ChatContactListActivity.class);
                    String currentUser = EMClient.getInstance().getCurrentUser();
                    intent.putExtra("user",currentUser);
                    startActivity(intent);
                    finish();
                    break;
                case 404:
                    Toast.makeText(ChatOnlineActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                    Toast.makeText(ChatOnlineActivity.this, msg.obj+"", Toast.LENGTH_SHORT).show();
                    break;
                case 400:
                    Toast.makeText(ChatOnlineActivity.this, "正在登陆", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_online);
        userName = (EditText) findViewById(R.id.chat_login_userName);
        userPass = (EditText) findViewById(R.id.chat_login_userPass);

    }

    public void btnRegister(View view) {
        startActivity(new Intent(this,ChatRegisterActivity.class));
    }

    public void btnLogin(View view) {
        String name = userName.getText().toString().trim();
        String pass = userPass.getText().toString().trim();
        if (name.length() > 3 && pass.length() > 3){
            EMClient.getInstance().login(name, pass, new EMCallBack() {
                @Override
                public void onSuccess() {
                    //TODO:登录成功
                    EMClient emClient = EMClient.getInstance();
                    //内部加载所有的组信息，完成后可直接
                    emClient.groupManager().loadAllGroups();
                    //加载所有会话
                    emClient.chatManager().loadAllConversations();
                    //登录成功获取当前用户的信息
                    //是从本地数据库加载聊天记录到内存中，其他获取聊天记录的方法均是从内存读取。
//                    emClient.contactManager().get

                    Message message = handler.obtainMessage(200);
                    handler.sendMessage(message);
                }

                @Override
                public void onError(int i, String s) {
                    Message message = handler.obtainMessage(404);
                    message.obj = s;
                    handler.sendMessage(message);
                }

                @Override
                public void onProgress(int i, String s) {
                    Message message = handler.obtainMessage(400);
                    handler.sendMessage(message);
                }
            });
        }
    }
}
