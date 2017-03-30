package coder.benpeng.loopobservable;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.observables.MathObservable;
import rx.schedulers.Schedulers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jiangbenpeng on 8/12/16.
 *
 * @author benpeng.jiang
 * @version 1.0.0
 */
public class LoopTest {
    public static void main(String[] args) {

        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                int i = 0;
                while (i++ < 10) {
                    subscriber.onNext(i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.newThread()).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("completed");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("current: " + integer);
            }
        });

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
