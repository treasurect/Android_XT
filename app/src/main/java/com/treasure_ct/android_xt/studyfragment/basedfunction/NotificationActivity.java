package com.treasure_ct.android_xt.studyfragment.basedfunction;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

import com.treasure_ct.android_xt.MainActivity;
import com.treasure_ct.android_xt.R;

/**
 * Created by treasure on 2016.09.05.
 */
public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basedfuncton_notification);
        Button btn1 = (Button) findViewById(R.id.notification);
        Button btn2 = (Button) findViewById(R.id.notification_cancel);
        Button btn3 = (Button) findViewById(R.id.notification_progress);
        Button play = (Button) findViewById(R.id.notification_play);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        play.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.notification:
                NotificationCompat.Builder builder3 = new NotificationCompat.Builder(this);
                builder3.setSmallIcon(R.mipmap.icon_main3);
                builder3.setContentTitle("标题");
                builder3.setContentText("文本内容");
                builder3.setDefaults(Notification.DEFAULT_ALL);
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("url","http://www.baidu.com");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,intent, PendingIntent.FLAG_UPDATE_CURRENT);;
                builder3.setContentIntent(pendingIntent);
                Notification notification = builder3.build();
                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
                managerCompat.notify(666,notification);
                break;
            case R.id.notification_cancel:
                NotificationManagerCompat managerCompat1 = NotificationManagerCompat.from(this);
                managerCompat1.cancel(666);
                break;
            case R.id.notification_progress:
                NotificationCompat.Builder builder4 = new NotificationCompat.Builder(this);
                builder4.setSmallIcon(R.mipmap.lufei);
                builder4.setContentTitle("下载中").setContentText("正在下载");
                builder4.setProgress(100,20,false);
                builder4.setDefaults(Notification.DEFAULT_ALL);
                Notification build = builder4.build();
                NotificationManagerCompat from = NotificationManagerCompat.from(this);
                from.notify(6,build);
                break;
            case R.id.notification_play:
                NotificationCompat.Builder builder5 = new NotificationCompat.Builder(this);
                builder5.setSmallIcon(R.mipmap.icon_main3);
                builder5.setContentTitle("标题");
                builder5.setDefaults(Notification.DEFAULT_ALL);
                builder5.setProgress(100,20,false);
                Intent intent1 = new Intent(this, MainActivity.class);
                intent1.putExtra("url","http://www.baidu.com");
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                PendingIntent pendingIntent1 = PendingIntent.getActivity(this, 0,intent1, PendingIntent.FLAG_UPDATE_CURRENT);;
                builder5.setContentIntent(pendingIntent1);
                builder5.addAction(android.R.drawable.ic_media_play,"播放",pendingIntent1);
                builder5.addAction(android.R.drawable.ic_media_pause,"暂停",pendingIntent1);
                builder5.addAction(android.R.drawable.ic_media_next,"下一首",pendingIntent1);
                Notification notification1 = builder5.build();
                NotificationManagerCompat managerCompat2 = NotificationManagerCompat.from(this);
                managerCompat2.notify(666,notification1);
                break;
        }
    }
}
