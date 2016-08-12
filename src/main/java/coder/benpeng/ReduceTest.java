package coder.benpeng;

import rx.Observable;
import rx.functions.Func1;
import rx.observables.MathObservable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jiangbenpeng on 8/12/16.
 *
 * @author benpeng.jiang
 * @version 1.0.0
 */
public class ReduceTest {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 3, 5));
        List<Integer> numbers1 = new ArrayList<>(Arrays.asList(2, 4, 6));

        Observable.from(numbers)
                .filter(s -> s > 4)
                .subscribe(s ->
                                System.out.println("Hello map " + s + "!"),
                        e -> System.out.println("Error"),
                        () -> System.out.println("Completed map: " + Thread.currentThread().getId())
                );

        List<Integer> integers = Observable
                .from(numbers1)
                .map(s -> s + 1)
                .toList()
                .toBlocking().single();

        MathObservable
                .from(Observable.from(numbers))
                .sumInteger(t -> t)
                .subscribe(
                        s -> System.out.println("sum: " + s),
                        e -> System.out.println("error"),
                        () -> System.out.println("completed")
                        );


        System.out.println("list: " + integers);
    }
}
