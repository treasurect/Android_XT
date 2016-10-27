package com.treasure_ct.android_xt.studyfragment.studyadvance.rxjava;

import android.content.pm.ApplicationInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.treasure_ct.android_xt.R;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.subjects.PublishSubject;

public class RXJavaActivity extends AppCompatActivity {
    private static final String TAG = "RXJavaActivity";
    private List<String> alist;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        ListView appList = (ListView) findViewById(R.id.app_list);
        if (appList != null) {
            alist = new ArrayList<>();
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alist);
            appList.setAdapter(adapter);
        }
        final EditText editText = (EditText) findViewById(R.id.rx_editText);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String s1 = s.toString();
                Log.d(TAG, "afterTextChanged: " + s1);
            }
        });
        initViews();
    }

    private void initViews(){
        EditText editText2 = (EditText) findViewById(R.id.rx_editText2);
        RXTextView.textChange(editText2);



        EditText editText = (EditText) findViewById(R.id.rx_editText);
        Observable<String> observable = RXTextView.textChange(editText);
        observable
//                .debounce(500, TimeUt)
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        int length = s.length();
                        char ch = ' ';
                        if (length > 0){
                            ch = s.charAt(length - 1);
                        }

                        return length >2 && ch != ' ';
                    }
                })//设置过滤条件返回 true 订阅者才可以接到
                .subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, "call: "+s);
            }
        });
    }

    public void btnFirstTest(View view) {

        //简单使用 RxJava 方式进行编程

        //1. 创建 Observable
        //2. 创建 Observer

        String[] devices = {"iPhone 6s","MI Note2","Huawei p9"};
        List<String> list = new ArrayList<>();
        list.add("iPhone 6s");
        Observable<String> observable = Observable.from(devices);

        //只要被观察者 Observer 设置 观察者订阅 subscribe
        // 那么 观察者就可以接收到相应的数据，进行响应和处理
        /**
         *Subscribes to an Observable and provides an Observer that implements functions to handle the items the
         * Observable emits and any error or completion notification it issues.
         *
         *  提供一个观察者 订阅到被观察者 观察者实现相应的处理方法
         *  onCompleted、
         *  onError、
         *  onNext
         *
         */
        observable.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.d("RX", "onCompleted: ---->" + "完成");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("RX", "onError: ---->");
                e.printStackTrace();
            }

            @Override
            public void onNext(String s) {
                Log.d("RX", "onNext: ---->" + s);
                if (s.equals("MI Note2")){
                    throw new RuntimeException("爆炸！！！");
                }
            }
        });

    }

    public void btnSecondTest(View view) {
        //获取手机中安装的软件的包名
        getPackages()
                .observeOn(AndroidSchedulers.mainThread())  //执行制定的线程，通常就是  Android 中的 UI 要设置
                .subscribe(new Observer<ApplicationInfo>() {
            @Override
            public void onCompleted() {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {
                alist.clear();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNext(ApplicationInfo applicationInfo) {
                String msg = applicationInfo.toString();
                alist.add(msg);
            }
        });
    }

    private Observable<ApplicationInfo> getPackages() {
        //创建一个 Observable 对象  并且指定    当这个可以被观察者，调用subscribe的时候，对应的接口回调
        return Observable.create(new Observable.OnSubscribe<ApplicationInfo>() {
            @Override
            public void call(Subscriber<? super ApplicationInfo> subscriber) {
                try {
                    //获取安装过的应用程序包
                    List<ApplicationInfo> list = getPackageManager().getInstalledApplications(0);
                    for (ApplicationInfo applicationInfo : list) {
                        //获取数据，并且发送到Observer如果出现错误 onError，完成 onCompleted
                        if (!subscriber.isUnsubscribed()) {
                            //如果subscriber没有取消注册，则发送数据
                            subscriber.onNext(applicationInfo);
                        }
                    }
                } catch (Exception e) {
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onError(e);
                    }
                }

                if (!subscriber.isUnsubscribed()) {
                    //结束
                }
            }
        });
    }

    public void btnSubject(View view) {
        //Subject Demo
        //创建一个主题 可以订阅观察者  并且可以手动的发送观察者想要的数据
        PublishSubject<Integer> subject = PublishSubject.create();
        subject.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG, "call: "+ integer);
            }
        });
        for (int i = 0; i < 100; i++) {
            subject.onNext(i);
        }
        subject.onCompleted();
    }
}
