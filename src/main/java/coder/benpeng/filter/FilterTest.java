package coder.benpeng.filter;

import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by benpeng.jbp on 2015/11/15.
 */
public class FilterTest {
    private static List<Integer> numbers;
    private static List<Integer> numbers2;

    public static void main(String[] args) {
        numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        numbers2 = new ArrayList<>(Arrays.asList(5, 6, 7, 8));

        Observable o1 = Observable
                .from(numbers)
                .filter(s ->
                        s > 5
                );

        Observable o2 = Observable
                .from(numbers2)
                .filter(s ->
                        s > 9
                );


        Observable
                .merge(o1, o2)
                .defaultIfEmpty(100)
                .subscribe(s ->
                                System.out.println("Hello map " + s + "!"),
                        e -> System.out.println("Error"),
                        () -> System.out.println("Completed! ")
                );
    }


}
