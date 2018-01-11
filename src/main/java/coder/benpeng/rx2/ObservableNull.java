package coder.benpeng.rx2;

import coder.benpeng.rx2.Backpressure;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by jiangbenpeng on 11/01/2018.
 *
 * @author benpeng.jiang
 * @version 1.0.0
 */
public class ObservableNull {
    private static final String tag = "Knife";

    enum Irrelevant {INSTANCE;}


    public static void main(String[] args) {

        Observable<String> b = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("1 ");
                e.onNext("2 ");
                e.onComplete();
            }
        });

        b.onErrorReturnItem("Error ").map(s -> s + "map")
                .subscribe(s -> System.out.println("subscribe: " + s));


        Observable<Object> b1 = Observable.create(s -> {
            s.onNext(Backpressure.Irrelevant.INSTANCE);
            s.onComplete();
        });
        b1.subscribe(s -> System.out.println("subscribe: " + s + (s instanceof Backpressure.Irrelevant)),
                throwable -> {
                    System.out.println("error");
                });
    }
}
