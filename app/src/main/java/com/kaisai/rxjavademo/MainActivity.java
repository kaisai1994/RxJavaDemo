package com.kaisai.rxjavademo;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func1;


public class MainActivity extends AppCompatActivity {

    private Subscriber<String> sur;

    private final static String TAG = MainActivity.class.getName();

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
       /* query("hello guy").flatMap(new Func1<List<String>, Observable<String>>() {
            @Override
            public Observable<String> call(List<String> strings) {
                return Observable.from(strings);
            }
        }).flatMap(new Func1<String, Observable<String>>() {
            @Override
            public Observable<String> call(String s) {
                return getTitle(s);
            }
        }).filter(new Func1<String, Boolean>() {        //filter()是输出和输入相同的元素，并且会过滤掉那些不满足检查条件的
            @Override
            public Boolean call(String s) {
                return s!="404";
            }
        }).take(5).doOnNext(new Action1<String>() {
            @Override
            public void call(String s) {
                //saveTitle();
            }
        }).subscribe(new Action1<String>() {    //take()输出最多指定数量的结果，次数大于源Observable发出的，全部输出
            @Override
            public void call(String s) {
                Log.e(TAG, "call: "+s);
            }
        });*/

        Subscription sub = Observable.just("Hello world")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        //throw new RuntimeException("11111");
                        return s;
                    }
                }).map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                         throw new RuntimeException("22222");
                    }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.e(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "ouch!: ");
            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "next: "+s);
            }
        });

        sub.unsubscribe();
        System.out.println(""+sub.isUnsubscribed());




    }

    public Observable<List<String>> query(String str) {
        List<String> list = new ArrayList<>();
        if (str.contains("hello")) {
            list = Arrays.asList("www.baidu.com", "***.sina.***", "www.360.cn");
        }
        return Observable.just(list);
    }

    public Observable<String> getTitle(String url) {
        if (url.contains("www")) {
            return Observable.just("this is a website");
        } else {
            return Observable.just("404");
        }
    }
}

