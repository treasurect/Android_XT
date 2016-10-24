package com.treasure_ct.android_xt.studyfragment.basedcontrols;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.treasure_ct.android_xt.R;

/**
 * Created by treasure on 2016.08.23.
 */
public class ImageViewActivity extends AppCompatActivity implements View.OnClickListener {
    private View view1,view2,view3,view4,view5,view6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_network_image);
        view1 = findViewById(R.id.main_image_1);
        view2 = findViewById(R.id.main_image_2);
        view3 = findViewById(R.id.main_image_3);
        view4 = findViewById(R.id.main_image_4);
        view5 = findViewById(R.id.main_image_5);
        view6 = findViewById(R.id.main_image_6);
        view1.setOnClickListener(this);
        view3.setOnClickListener(this);
        view3.setOnClickListener(this);
        view4.setOnClickListener(this);
        view5.setOnClickListener(this);
        view6.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, ImageView2Activity.class);
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                Pair.create(view1, "share_1"),
                Pair.create(view2, "share_2"),
                Pair.create(view3, "share_3"),
                Pair.create(view4, "share_4"),
                Pair.create(view5, "share_5"),
                Pair.create(view6, "share_6"));
        ActivityCompat.startActivity(this,intent,compat.toBundle());
    }
}
