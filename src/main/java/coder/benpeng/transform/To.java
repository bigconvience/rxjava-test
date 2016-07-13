package coder.benpeng.transform;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Func1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

/**
 * Created by jiangbenpeng on 4/26/16.
 *
 * @author benpeng.jiang
 * @version 1.0.0
 */
public class To {

    public static void main(String[] args) {
        //hello("ben", "peng", "cc", "fdas", "fda");
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
        System.out.println("list: " + integers);
    }
}
