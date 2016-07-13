package coder.benpeng;

import coder.benpeng.util.Log;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;

/**
 * Created by benpeng.jbp on 2015/11/15.
 */
public class Knife {
    private static final String tag = "Knife";

    public static void main(String[] args) {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onNext(String s) {
                Log.d(tag, "Item: " + s);
            }

            @Override
            public void onCompleted() {
                Log.d(tag, "Completed!");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(tag, "Error!");
            }
        };

        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                subscriber.onNext("Hi");
                subscriber.onNext("Aloha");
                subscriber.onCompleted();
            }
        });

        observable.subscribe(observer);

        Observable.just("a", "b").subscribe(observer);
    }
}
