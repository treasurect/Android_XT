package com.treasure_ct.android_xt.studyfragment.studyadvance.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.treasure_ct.android_xt.R;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class LongTimeActivity extends AppCompatActivity {
    private static final String TAG = "LongTimeActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_time);
    }

    public void btnLongTime(View view) {
        Observable.from(new String[]{"A","B"});
        Observable<Integer> observable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                //准备数据的部分
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 0; i < 5; i++) {
                    if (!subscriber.isUnsubscribed()){
                        subscriber.onNext(i);
                    }
                }
            }
        });
        observable
                .subscribeOn(Schedulers.newThread())  //// 设置订阅回调，准备数据的部分 执行的方式
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onCompleted() {

                    }
                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "onNext: " + integer);
                    }
                });
    }
}
