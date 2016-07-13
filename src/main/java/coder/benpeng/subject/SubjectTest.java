package coder.benpeng.subject;

import rx.Observable;
import rx.Observer;
import rx.Producer;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jiangbenpeng on 4/27/16.
 *
 * @author benpeng.jiang
 * @version 1.0.0
 */
public class SubjectTest {
    public static void main(String[] args) {

        Subject<String, String> subject = PublishSubject.create();

        subject.onNext("a");
        subject.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("Subject completed");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("Subject: " + s);
            }
        });

        subject.onNext("afa");

        List<String> a = new ArrayList<>(Arrays.asList("f", "c", "k"));

        Observable
                .from(a)
                .subscribe(s ->
                                System.out.println("Hello map " + s + "!"),
                        e -> System.out.println("Error"),
                        () -> {
                            System.out.println("Completed map: " + Thread.currentThread().getId());
                            subject.onNext("cccc");
                            subject.onCompleted();
                        }
                );

    }


}
