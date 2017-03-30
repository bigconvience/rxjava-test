package coder.benpeng.defer;

import rx.Observable;
import rx.functions.Action1;

import java.net.SocketException;

/**
 * Created by benpeng.jbp on 2015/11/15.
 */
public class ExceptionTest {


    public static void main(String[] args) {
        SomeType instance = new SomeType();
        Observable value = instance.valueObservable();

        instance.setValue("SD");

        value.subscribe(System.out::println,
                new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                },
                () -> System.out.println("completed"));

    }

    public static class SomeType {
        private String value;

        public void setValue(String value) {
            this.value = value;
        }

        public Observable<String> valueObservable() {
            return Observable.defer(() -> Observable.error(new SocketException()));
        }
    }

}
