package coder.benpeng.rx2;


import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by benpeng.jbp on 2015/11/15.
 */
public class Backpressure {
    private static final String tag = "Knife";

    enum Irrelevant {INSTANCE;}


    public static void main(String[] args) {
        //被观察者在主线程中，每1ms发送一个事件
        Disposable disposable = Observable.interval(1, TimeUnit.MILLISECONDS)
                //.subscribeOn(Schedulers.newThread())
                //将观察者的工作放在新线程环境中
                .observeOn(Schedulers.newThread())
                //观察者处理每1000ms才处理一个事件
                .subscribe((aLong) -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("observable TAG ---->" + aLong);
                });

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
