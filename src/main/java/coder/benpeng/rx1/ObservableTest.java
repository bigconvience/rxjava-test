package coder.benpeng.rx1;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by jiangbenpeng on 11/01/2018.
 *
 * @author benpeng.jiang
 * @version 1.0.0
 */
public class ObservableTest {
    public static void main(String[] args) {
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> e) {
                e.onNext("1 ");
                e.onNext("2 ");
                e.onCompleted();
            }
        });
    }
}
