package com.treasure_ct.android_xt.studyfragment.studyadvance.rxjava;

/**
 * Created by treasure on 2016.10.27.
 */

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * 使用RxJava模式，创建各种针对TextView/EditText的Observable
 */
public class RXTextView {
    private static class TextChangeOnSubScribe implements Observable.OnSubscribe<String>{
        private TextView mTextView;

        public TextChangeOnSubScribe(TextView textView) {
            mTextView = textView;
        }

        @Override
        public void call(final Subscriber<? super String> subscriber) {
            //利用Text View  addTextChangedListener设置一个持续的发送事件的操作
            mTextView.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    String content = s.toString();
                    subscriber.onNext(content);
                }
            });
        }
    }
    /**
     * 创建可以被观察的，文本内容修改的对象
     * @param editText
     * @return
     */
    public static Observable<String> textChange(TextView editText){
        Observable<String> ret = Observable.create(new TextChangeOnSubScribe(editText));
        ret.observeOn(AndroidSchedulers.mainThread());
        return ret;
    }
}
