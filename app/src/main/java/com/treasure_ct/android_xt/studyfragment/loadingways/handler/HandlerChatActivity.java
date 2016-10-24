package com.treasure_ct.android_xt.studyfragment.loadingways.handler;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.loadingways.handler.chat.Entry;
import com.treasure_ct.android_xt.studyfragment.loadingways.handler.chat.MyBaseAdapter;
import com.treasure_ct.post_network_xt.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


/**
 * 题型：图灵机器人
 * 思路：首先使用子线程处理消息，根据点击事件发来的“发送信息”，网络申请加JSON解析，将信息传给接收消息的Handler，进行显示
 * 用到两个Handler，第一个sonHandler 用来进行用户发送到子线程的任务，第二个mainHandler用来子线程处理好信息，
 * 接受网络信息，然后发回主线程，进行显示
 */
public class HandlerChatActivity extends AppCompatActivity implements View.OnClickListener,Runnable {
    private List<Entry> list;
    private MyBaseAdapter adapter;
    private EditText input;
    private ListView listView;
    private Handler sonHandler;
    private Handler mainHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 888:
                    int type = msg.arg1;
                    if (type == 100000) {
                        String text = (String) msg.obj;
                        Entry entry = new Entry();
                        entry.setText(text);
                        entry.setUserName("小T");
                        entry.setReceived(true);
                        entry.setTime(System.currentTimeMillis());
                        list.add(entry);
                        adapter.notifyDataSetChanged();
                        int count = adapter.getCount();
                        listView.setSelection(count - 1);
                    }
                    break;
            }
        }
    };
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asyncloading_handlerchat);
        Button send = (Button) findViewById(R.id.btn_send_message);
        input = (EditText) findViewById(R.id.edit);
        listView = (ListView) findViewById(R.id.listview);
        list = new ArrayList<>();
        adapter = new MyBaseAdapter(this,list);
        if (listView != null) {
            listView.setAdapter(adapter);
        }
        Thread thread = new Thread(this);
        thread.start();
        send.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send_message:
                if (sonHandler != null) {
                    Message message = sonHandler.obtainMessage(666);
                    String s = input.getText().toString();
                    if (s != null && !s.equals("")) {
                        message.obj = s;
                        sonHandler.sendMessage(message);

                        Entry entry = new Entry();
                        entry.setReceived(false);
                        entry.setText(s);
                        entry.setTime(System.currentTimeMillis());
                        entry.setUserName("我");
                        list.add(entry);
                        adapter.notifyDataSetChanged();
                        int count = adapter.getCount();
                        listView.setSelection(count - 1);
                        input.setText("");
                    }else {
                        Toast.makeText(HandlerChatActivity.this, "请输入信息", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    @Override
    public void run() {
        Looper.prepare();
        Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 666:
                        JSONObject object = new JSONObject();
                        try {
                            object.put("key","f88cd2cdc8ea4953b51154567ca356d7");
                            object.put("info",msg.obj.toString());
                            object.put("loc","北京市海淀区");
                            object.put("userid",Build.SERIAL);
                            byte[] data = HttpUtil.doPostJson("http://www.tuling123.com/openapi/api", object.toString());
                            if (data != null) {
                                String s = new String(data, "UTF-8");
                                Log.d("-------------------", "handleMessage: "+s);
                                JSONObject jsonObject = new JSONObject(s);
                                int code = jsonObject.getInt("code");
                                if (code == 100000) {
                                    String text = jsonObject.getString("text");
                                    Message message = mainHandler.obtainMessage(888);
                                    message.obj = text;
                                    message.arg1= code;
                                    mainHandler.sendMessage(message);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                }
            }
        };
        sonHandler = handler;
        Looper.loop();
    }
}