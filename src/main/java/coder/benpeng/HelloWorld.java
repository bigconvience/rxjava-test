package coder.benpeng;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Action1;
import rx.internal.operators.OnSubscribeRedo;
import rx.internal.operators.OperatorOnErrorReturn;
import rx.internal.schedulers.EventLoopsScheduler;
import rx.schedulers.Schedulers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.jar.JarEntry;

/**
 * Created by benpeng.jbp on 2015/11/15.
 */
public class HelloWorld {

    private static List<Integer> numbers;

    public static void hello(String... names) {

        Observable.from(names).flatMap(name -> {
            return Observable.just(name);
        }).
                subscribeOn(Schedulers.newThread()).
                observeOn(Schedulers.immediate()).
                subscribe(s ->
                                System.out.println("Hello " + s + "!"),
                        e -> System.out.println("Error"),
                        () -> System.out.println("Completed: " + Thread.currentThread().getId()));

        Observable.from(names).observeOn(Schedulers.newThread())
                .map(str -> {
                            try {
                                Thread.sleep(300 * (new Random().nextInt(10)));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return "FFFF:" + str + " : " + Thread.currentThread().getId();
                        }
                ).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.immediate())
                .subscribe(s ->
                                System.out.println("Hello " + s + "!"),
                        e -> System.out.println("Error"),
                        () -> System.out.println("Completed: " + Thread.currentThread().getId())
                );
    }

    public static Observable<Integer> squareOf(int number) {
        return Observable.just(number * number).subscribeOn(Schedulers.newThread());
    }

    public static void main(String[] args) {
        //hello("ben", "peng", "cc", "fdas", "fda");
        numbers = new ArrayList<>(Arrays.asList());
        Observable.from(numbers).
                flatMap(number -> {

                    System.out.println("number: " + number + " id: " + Thread.currentThread().getId());
                    return Observable.just(number * number);
                }).
                map(v -> {
                    System.out.println("flat");
                    return v;
                })
                .doOnNext(integer -> {
                    System.out.println("i: " + integer);
                })
                .subscribe(s ->
                                System.out.println("Hello map " + s + "!"),
                        e -> System.out.println("Error"),
                        () -> System.out.println("Completed map: " + Thread.currentThread().getId())
                );

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
