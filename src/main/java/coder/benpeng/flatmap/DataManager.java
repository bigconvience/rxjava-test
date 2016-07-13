package coder.benpeng.flatmap;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;
import rx.schedulers.NewThreadScheduler;
import rx.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jiangbenpeng on 4/24/16.
 *
 * @author benpeng.jiang
 * @version 1.0.0
 */
public class DataManager {
    private final List<Integer> numbers;



    public DataManager() {
        this.numbers = new ArrayList<>(Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10));
    }

    public Observable<Integer> getNumbers() {
        return Observable.from(numbers);
    }

    public List<Integer> getNumbersSync() {
        return this.numbers;
    }

    public Observable<Integer> squareOf(int number) {
        return Observable.just(number * number).subscribeOn(Schedulers.immediate());
    }
}
