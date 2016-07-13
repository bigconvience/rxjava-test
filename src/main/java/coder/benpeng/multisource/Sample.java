package coder.benpeng.multisource;

import rx.Observable;

import java.util.concurrent.TimeUnit;

/**
 * Created on 2015/12/26.
 *
 * @author benpeng.jbp
 * @version 1.0.0
 *          <a href="https://github.com/dlew/rxjava-multiple-sources-sample">rxjava-multiple-sources-sample</a>
 */
public class Sample {
    public static void main(String[] args) {
        Sources sources = new Sources();

        // Create our sequence for querying best available data
        Observable<Data> source = Observable.concat(
                sources.memory(),
                sources.disk(),
                sources.network()
        )
                .first(data -> data != null && data.isUpToDate());

        // "Request" latest data once a second
        Observable.interval(1, TimeUnit.SECONDS)
                .flatMap(__ -> source)
                .subscribe(data -> System.out.println("Received: " + data.value));

        // Occasionally clear memory (as if app restarted) so that we must go to disk
        Observable.interval(3, TimeUnit.SECONDS)
                .subscribe(__ -> sources.clearMemory());

        // Java will quit unless we idle
        sleep(15 * 1000);
    }

    static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            // Ignore
        }
    }
}
