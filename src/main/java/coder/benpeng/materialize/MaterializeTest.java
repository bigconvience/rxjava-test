package coder.benpeng.materialize;

import coder.benpeng.util.Log;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import java.rmi.server.ExportException;


/**
 * Created by jiangbenpeng on 19/10/2017.
 *
 * @author benpeng.jiang
 * @version 1.0.0
 */
public class MaterializeTest {
    private static final String TAG = "MaterializeTest";

    public static void main(String[] args) {

        Observable<String> observable =  Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                for (int i = 0; i < 5; i++) {
                    subscriber.onNext(i + "");
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {

                    }
                }
                subscriber.onCompleted();
            }
        });

        Log.d(TAG,"observable................."+observable);

        Subscriber<Object> subscriber = new Subscriber<Object>() {

            @Override
            public void onNext(Object v) {
                Log.d(TAG,"onNext................."+v);
            }

            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted.................");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError.....................");
            }
        };

//        observable.subscribeOn(Schedulers.io()).materialize().observeOn(Schedulers.computation()).dematerialize().subscribe(subscriber);

//        observable.subscribeOn(Schedulers.io()).observeOn(Schedulers.computation()).subscribe(subscriber);

        observable.materialize().subscribe(subscriber);

        try {
            System.in.read();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
