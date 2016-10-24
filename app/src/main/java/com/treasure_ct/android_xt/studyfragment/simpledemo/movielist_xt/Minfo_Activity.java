package com.treasure_ct.android_xt.studyfragment.simpledemo.movielist_xt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.simpledemo.movielist_xt.entries.MIF_Entry;
import com.treasure_ct.android_xt.studyfragment.simpledemo.movielist_xt.entries.MIF_Result;
import com.treasure_ct.network_xt.ImageUtil;
import com.treasure_ct.network_xt.NetworkTask;
import com.treasure_ct.network_xt.Tools;

/**
 * Created by treasure on 2016.09.04.
 */
public class Minfo_Activity extends Activity implements NetworkTask.Callback<MIF_Result>, View.OnClickListener {
    private TextView title,time,score,info,story,MS_btn;
    private ImageView image,image1,image2,image3,image4;
    private Long id;
    private ML_Server server;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_simpledemo_movie_info_main);
        title = (TextView) findViewById(R.id.mif_title);
        time = (TextView) findViewById(R.id.mif_retime);
        score = (TextView) findViewById(R.id.mif_score);
        info = (TextView) findViewById(R.id.mif_info);
        story = (TextView) findViewById(R.id.mif_story);
        MS_btn = (TextView) findViewById(R.id.MS_btn);
        image = (ImageView) findViewById(R.id.mif_image);
        image1 = (ImageView) findViewById(R.id.mif_photo_1);
        image2 = (ImageView) findViewById(R.id.mif_photo_2);
        image3 = (ImageView) findViewById(R.id.mif_photo_3);
        image4 = (ImageView) findViewById(R.id.mif_photo_4);
        id = getIntent().getLongExtra("id", 0);
        server = Tools.getInstance(ML_Server.class);
        server.getData(id).execute(this);
        MS_btn.setOnClickListener(this);
    }

    @Override
    public void onSuccess(MIF_Result text) {
        MIF_Entry list = text.getData();
        title.setText(list.getTitle());
        time.setText(list.getTime());
        score.setText(String.valueOf(list.getScore()));
        info.setText(list.getActor());
        story.setText(list.getStory());
        ImageUtil.loadImage(image,list.getImage());
        ImageUtil.loadImage(image1,list.getPhoto().get(0));
        ImageUtil.loadImage(image2,list.getPhoto().get(1));
        ImageUtil.loadImage(image2,list.getPhoto().get(2));
        ImageUtil.loadImage(image2,list.getPhoto().get(3));
    }

    @Override
    public void onFail(Exception e) {
        e.printStackTrace();
        Toast.makeText(Minfo_Activity.this, "服务器错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MStoryActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }
}
