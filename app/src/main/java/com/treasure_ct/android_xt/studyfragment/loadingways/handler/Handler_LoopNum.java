package com.treasure_ct.android_xt.studyfragment.loadingways.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.treasure_ct.android_xt.R;

/**
 * Created by treasure on 2016.08.30.
 */

public class Handler_LoopNum extends AppCompatActivity {
    private static final String TAG = Handler_LoopNum.class.getSimpleName();
    private TextView text_handler;
    private Handler handler = new Handler(new Handler.Callback() {
//        Handler 在哪个线程创建，handleMessage这个方法就可以在那个线程执行
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    Log.d(TAG, "handleMessage:"+Thread.currentThread());
                    text_handler.setText(((String) message.obj));
                    break;
            }
            return false;
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_loopnum);
//        Message               产品
//        MessageQueue          仓库
//        Looper                循环
//        Handler               物流
//        Handler.Callback      回调接口
        text_handler = (TextView) findViewById(R.id.text_handler);
        text_handler.setText("0");
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
//                    Message message = new Message();//不推荐使用
//                    第一种
//                    Message message = Message.obtain(); //不一定会创建一个新的message，如果之前有闲置的message会直接用
//                    message.what = 0;//区分
//                    message.obj = String.valueOf(i + 1);
//                    sendMessage只是简单地把消息添加到消息队列中，不会处理其他操作
//                    handler.sendMessage(message);
//                    第二种
                    Message message = Message.obtain(handler,0,String.valueOf(i + 1));
                    message.sendToTarget();
//                    第三种
//                    handler.obtainMessage(0,String.valueOf(i + 1)).sendToTarget();


//                    text_handler.setText(String.valueOf(i + 1));
                    Log.d(TAG,"Run"+Thread.currentThread());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
//        错误示例1
//        for (int i = 0; i < 100; i++) {
//            text_handler.setText(i + 1);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
