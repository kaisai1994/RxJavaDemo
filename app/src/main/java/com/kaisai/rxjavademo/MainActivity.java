package com.kaisai.rxjavademo;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;


public class MainActivity extends AppCompatActivity {

    private Subscriber<String> sur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      /*  rx.Observable<String> on = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("HelloWorld");
                subscriber.onCompleted();
            }
        });
        sur = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e("xxx", "onNext: "+s);
            }
        };
        on.subscribe(sur);*/

      /*  Observable<String> mo = Observable.just("Hello world");

        Action1<String> onAction = new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d("xxx", "call: "+s);
            }
        };
        mo.subscribe(onAction);*/

        /*Observable.just("Hello World").map(new Func1<String, Integer>() {
            @Override
            public Integer call(String s) {
                return s.hashCode();
            }
        }).map(new Func1<Integer, String>() {
            @Override
            public String call(Integer i) {
                return i.toString();
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e("xxx", "call: " + s);
            }
        });*/

        /*List list = Arrays.asList("1","2","3");
        Observable.from(list).subscribe(new Action1<String>() {
            @Override
            public void call(String o) {
                Log.e("xxx", "call: "+o);
            }
        });*/
      /*  query("hello guy").subscribe(new Action1<List<String>>() {
            @Override
            public void call(List<String> strings) {
                Observable.from(strings).subscribe(new Action1<String>() {
                    @Override
                    public void call(String o) {
                        Log.e("xxx", "call: "+o);
                    }
                });
            }
        });*/
        /**
         * Observable.flatMap()接收一个Observable的输出作为输入，同时输出另外一个Observable
         *
         * 理解：flatMap输出的新的Observable正是我们在Subscriber想要接收的
         * */
        query("hello guy").flatMap(new Func1<List<String>, Observable<String>>() {
            @Override
            public Observable<String> call(List<String> strings) {
                return Observable.from(strings);
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {

            }
        });

    }

    public Observable<List<String>> query(String str){
        List<String> list = new ArrayList<>();
        if(str.contains("hello")){
            list = Arrays.asList("www.baidu.com","www.sina.com","www.360.cn");
        }
        return Observable.just(list);
    }
}

