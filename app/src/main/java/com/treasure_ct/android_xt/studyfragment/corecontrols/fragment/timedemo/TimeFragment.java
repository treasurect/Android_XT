package com.treasure_ct.android_xt.studyfragment.corecontrols.fragment.timedemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.treasure_ct.android_xt.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by treasure on 2016.09.13.
 */

/**
 * Fragment 的创建步骤
 * 1） 继承v4包下的Fragment
 * 2）有且仅有一个无参构造方法
 * 3）如果Fragment需要显示界面，需要重写onCreateView、
 * 4）指定布局资源
 */
public class TimeFragment extends Fragment implements Runnable{
    private TextView time,data;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //主线程执行这个方法
            //1.Message内部的what
            //假定666 设置日期
            String obj = (String) msg.obj;
            switch (msg.what) {
                case 333:
                    time.setText(obj);
                    break;
                case 666:
                    data.setText(obj);
                    break;
            }
        }
    };
    public TimeFragment() {
    }

    //返回Fragment的界面部分，和Adapter的getView（）方法类似
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_corecontrols_fragment_fragmentdemo1_time_fragment, container, false);

        //在onCreateView内部，可以设置控件的内容、开启线程、连接网络
         time = (TextView) view.findViewById(R.id.date_frag_1);
         data = (TextView) view.findViewById(R.id.time_frag_1);
        Date date = new Date();
        //SimpleDateFormat 允许以为日期-时间格式化选择任何用户指定的方式启动。 但是，
        // 希望用 DateFormat 中的 getTimeInstance、 getDateInstance 或 getDateTimeInstance 创建一个日期-时间格式化程序。
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
        data.setText(format.format(date));
        format.applyPattern("HH:mm:ss");
        time.setText(format.format(date));

        //启动线程
        Thread thread = new Thread(this);
        thread.start();
        return view;
    }

    @Override
    public void run() {
            try {
                Calendar calendar = Calendar.getInstance();
                StringBuilder builder = new StringBuilder();
                while (true) {
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    int year = calendar.get(Calendar.YEAR);
                    int mouth = calendar.get(Calendar.MONTH);
                    int day = calendar.get(Calendar.DATE);
                    int hour = calendar.get(Calendar.HOUR_OF_DAY);
                    int minute = calendar.get(Calendar.MINUTE);
                    int second = calendar.get(Calendar.SECOND);
                    builder.append(year).append("-")
                            .append(mouth).append("-")
                            .append(day);
                    String s = builder.toString();
                    //不允许在子线程修改UI控件
                    Message message = handler.obtainMessage(666);
                    message.obj =s;
                    //
                    handler.sendMessage(message);
                    builder.setLength(0);
                    builder.append(hour).append(":")
                            .append(minute).append(":")
                            .append(second);
                     s = builder.toString();
                    message = handler.obtainMessage(333);
                    message.obj =s;
                    handler.sendMessage(message);
                    builder.setLength(0);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}
