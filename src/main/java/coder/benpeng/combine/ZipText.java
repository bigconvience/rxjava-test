package coder.benpeng.combine;

import rx.Observable;
import rx.exceptions.Exceptions;

import java.io.IOException;

/**
 * Created by jiangbenpeng on 4/25/16.
 *
 * @author benpeng.jiang
 * @version 1.0.0
 */
public class ZipText {
    public static void main(String[] args) {

        Observable<Integer> zip = Observable.zip(
                Observable.just(1, 3, 4),
                Observable.empty(),
                (a, b) -> a );

        zip.subscribe(
                sum -> System.out.println("sum: " + sum),
                (e) -> System.err.println("error for zip"),
                () -> System.out.println("Zip Completed"));


        Observable<Integer> merge = Observable.merge(
                Observable.just(1, 3, 5),
                Observable.just(2, 4, 6)
        );

        merge.subscribe(
                (sum) -> System.out.println("sum: " + sum),
                (e) -> System.err.println("error for merge"),
                () -> System.out.println("Merge Completed"));


        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
