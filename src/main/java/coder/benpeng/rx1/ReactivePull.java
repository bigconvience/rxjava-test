package coder.benpeng.rx1;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import java.io.IOException;

/**
 * Created by jiangbenpeng on 11/01/2018.
 *
 * @author benpeng.jiang
 * @version 1.0.0
 */
public class ReactivePull {


    public static void main(String[] args) {
        //被观察者将产生100000个事件
        Observable observable = Observable.range(1, 100);
        observable.observeOn(Schedulers.newThread())
                .subscribe(new MySubscriber());

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class MySubscriber extends Subscriber<Integer> {
        @Override
        public void onStart() {
            //一定要在onStart中通知被观察者先发送一个事件
            request(1);
        }

        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onNext(Integer n) {
            System.out.println("TAG-->" + n);
            //处理完毕之后，在通知被观察者发送下一个事件
            request(1);
        }
    }
}
