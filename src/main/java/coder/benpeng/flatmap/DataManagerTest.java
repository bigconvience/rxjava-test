package coder.benpeng.flatmap;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by jiangbenpeng on 4/24/16.
 *
 * @author benpeng.jiang
 * @version 1.0.0
 */
public class DataManagerTest {
    private static DataManager mDataManager = new DataManager();

    private static final Func1<Integer, Observable<Integer>> SQUARE_OF_NUMBER = (number) ->
         mDataManager.squareOf(number);


    public static void main(String[] args) {
        mDataManager.getNumbers()
                .flatMap(SQUARE_OF_NUMBER)
                .subscribe(s->{
            System.out.println("s :" + s);
        });
    }
}
