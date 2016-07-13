package coder.benpeng.defer;

import rx.Observable;
import rx.schedulers.Schedulers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by benpeng.jbp on 2015/11/15.
 */
public class DefferTest {


    public static void main(String[] args) {
        SomeType instance = new SomeType();
        Observable value = instance.valueObservable();

        instance.setValue("SD");

        value.subscribe(System.out::println,
                e -> {
                },
                () -> System.out.println("completed"));



    }

    public static class SomeType {
        private String value;

        public void setValue(String value) {
            this.value = value;
        }

        public Observable<String> valueObservable() {
            return Observable.defer(() -> Observable.just(value));
        }
    }

}
