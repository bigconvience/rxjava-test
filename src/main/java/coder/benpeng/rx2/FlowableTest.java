package coder.benpeng.rx2;

import io.reactivex.Flowable;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import sun.rmi.runtime.Log;

/**
 * Created by jiangbenpeng on 11/01/2018.
 *
 * @author benpeng.jiang
 * @version 1.0.0
 */
public class FlowableTest {

    public static void main(String[] args) {
        Flowable.range(0,10)
                .subscribe(new Subscriber<Integer>() {
                    Subscription sub;
                    //当订阅后，会首先调用这个方法，其实就相当于onStart()，
                    //传入的Subscription s参数可以用于请求数据或者取消订阅
                    @Override
                    public void onSubscribe(Subscription s) {
                        System.out.println("TAG onsubscribe start");
                        sub=s;
                        sub.request(1);
                        System.out.println("TAG onsubscribe end");
                    }

                    @Override
                    public void onNext(Integer o) {
                        System.out.println("TAG onNext--->"+o);
                        sub.request(1);
                    }
                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                    }
                    @Override
                    public void onComplete() {
                        System.out.println("TAG onComplete");
                    }
                });
    }
}
