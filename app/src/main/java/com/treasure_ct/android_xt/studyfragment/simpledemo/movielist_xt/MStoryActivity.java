package com.treasure_ct.android_xt.studyfragment.simpledemo.movielist_xt;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.simpledemo.movielist_xt.entries.MS_Entry;
import com.treasure_ct.android_xt.studyfragment.simpledemo.movielist_xt.entries.MS_Result;
import com.treasure_ct.network_xt.NetworkTask;
import com.treasure_ct.network_xt.Tools;

public class MStoryActivity extends Activity implements NetworkTask.Callback<MS_Result>{
    private TextView title,content,time;
    private long id;
    private ML_Server server;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_simpledemo_movie_story_main);
        time = (TextView) findViewById(R.id.ms_time);
        title = (TextView) findViewById(R.id.ms_title);
        content = (TextView) findViewById(R.id.ms_content);
        id = getIntent().getLongExtra("id",0);
         server = Tools.getInstance(ML_Server.class);
        server.getStory(id).execute(this);
    }

    @Override
    public void onSuccess(MS_Result text) {
        MS_Entry data = text.getMsData().getData();
        title.setText(data.getTitle());
        time.setText(data.getTime());
        content.setText(data.getContent());
    }

    @Override
    public void onFail(Exception e) {
        e.printStackTrace();
        Toast.makeText(MStoryActivity.this, "服务器错误", Toast.LENGTH_SHORT).show();
    }
}
