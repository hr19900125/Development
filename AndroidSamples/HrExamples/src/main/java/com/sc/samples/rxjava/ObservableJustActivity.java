package com.sc.samples.rxjava;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sc.samples.BaseActivity;
import com.sc.samples.R;

import rx.Observable;
import rx.Subscriber;

/**
 * 使用Observable.just可以用来创建只发出一个事件就结束的Observable对象
 */
public class ObservableJustActivity extends BaseActivity {

    @Override
    protected void click() {
        just();
    }

    private void just() {
        Observable<String> ob = Observable.just("hello world");
        Subscriber<String> sub = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                printlnToTextView(mResultTextView, "Subscriber call onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                printlnToTextView(mResultTextView, "Subscriber call onError");
            }

            @Override
            public void onNext(String s) {
                printlnToTextView(mResultTextView, s);
            }
        };
        ob.subscribe(sub);
    }
}
