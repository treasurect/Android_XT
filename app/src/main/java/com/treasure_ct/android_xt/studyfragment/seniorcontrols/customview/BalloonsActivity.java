package com.treasure_ct.android_xt.studyfragment.seniorcontrols.customview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.treasure_ct.android_xt.R;
import com.treasure_ct.android_xt.studyfragment.seniorcontrols.customview.widgets.BalloonBoom;

public class BalloonsActivity extends AppCompatActivity implements Runnable {
    private boolean isMove;
    private int num = 0;
    private BalloonBoom mBoom;
    private TextSwitcher score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seniorcontrols_customview_balloons);
        mBoom = (BalloonBoom) findViewById(R.id.balloon_move);
        score = (TextSwitcher)findViewById(R.id.balloon_score);
        score.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(BalloonsActivity.this);
                textView.setTextColor(Color.YELLOW);
                textView.setTextSize(25);
                textView.setGravity(Gravity.CENTER);
                textView.setBackgroundColor(Color.GRAY);
                return textView;
            }
        });
        new Thread(this).start();

    }

    @Override
    public void run() {
        isMove = true;
        try {
            while (isMove) {
                mBoom.shownext();
                num = mBoom.score;
                    score.post(new Runnable() {
                        @Override
                        public void run() {
                            score.setText("Score" + num);
                        }
                    });
                Thread.sleep(15);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        isMove = false;
        super.onDestroy();
    }
}
