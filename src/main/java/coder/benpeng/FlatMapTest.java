package coder.benpeng;



import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by benpeng.jbp on 2015/11/15.
 */
public class FlatMapTest {
    private static final String tag = "FlatMapTest";

    public static void main(String[] args) {
        List<String> inputs = new ArrayList<>();
        inputs.add("A");
        inputs.add("B");
        inputs.add("C");
        inputs.add("D");

        Observable.fromArray(inputs)
               .flatMap(new Function<List<String>, ObservableSource<?>>() {
                   @Override
                   public ObservableSource<String> apply(List<String> strings) throws Exception {
                       return Observable.create(new ObservableOnSubscribe<String>() {
                           @Override
                           public void subscribe(ObservableEmitter<String> e) throws Exception {
                               StringBuilder stringBuilder = new StringBuilder();
                               strings.forEach( s -> stringBuilder.append(s));

                               e.onNext(stringBuilder.toString());
                               e.onComplete();
                           }
                       });
                   }
               })
                .subscribe( a -> System.out.println("i: " + a));

    }
}
