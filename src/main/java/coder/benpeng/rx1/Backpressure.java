package coder.benpeng.rx1;


import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by jiangbenpeng on 11/01/2018.
 *
 * @author benpeng.jiang
 * @version 1.0.0
 */
public class Backpressure {
    public static void main(String[] args) {
        //被观察者在主线程中，每1ms发送一个事件
        Subscription subscription = Observable.interval(1, TimeUnit.MILLISECONDS)
                //将观察者的工作放在新线程环境中
                .observeOn(Schedulers.newThread())
                //观察者处理每1000ms才处理一个事件
                .subscribe(aLong -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("TAG---->" + aLong);
                });


        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
