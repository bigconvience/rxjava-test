package coder.benpeng.timer;

import rx.Observable;
import rx.Subscriber;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by jiangbenpeng on 4/25/16.
 *
 * @author benpeng.jiang
 * @version 1.0.0
 */
public class Interval {
    public static void main(String[] args) {
        //每隔两秒产生一个数字
        Observable.interval(1, TimeUnit.SECONDS).subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {
                System.out.println("Sequence complete.");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error:" + e.getMessage());
            }

            @Override
            public void onNext(Long aLong) {
                System.out.println("Next:" + aLong.toString());
            }
        });

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
