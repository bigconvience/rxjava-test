package coder.benpeng.rx2;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangbenpeng on 18/01/2018.
 *
 * @author benpeng.jiang
 * @version 1.0.0
 */
public class ListLine {
    private static Observable<UploadData> doUpload(Integer s) {
        return Observable.defer(() -> {
            Observable<UploadData> observable = Observable.create((e) -> {
                System.out.println(Thread.currentThread() + " observe: " + s);
                UploadData uploadData = new UploadData();
                uploadData.mUrl = s + " AAA";
                Thread.sleep(500);

                e.onNext(uploadData);
                e.onComplete();
            });

            return observable;
        });

    }

    private static Observable<UploadData> uploadImage(List<Integer> dataList) {
        return Observable
                .fromIterable(dataList)
                .flatMap(new Function<Integer, Observable<UploadData>>() {
                    @Override
                    public Observable<UploadData> apply(Integer s) {
                        System.out.println(Thread.currentThread() + " map: " + s);

                        return doUpload(s);
                    }
                });
    }

    private static Observable<List<UploadData>> uploadImages(OriginData originData) {
        return uploadImage(originData.mInput)
                .subscribeOn(Schedulers.newThread())
                .toList().toObservable()
                .doOnNext(new Consumer<List<UploadData>>() {
                    @Override
                    public void accept(List<UploadData> postPicDOs) {
                        originData.mOutPut = postPicDOs;
                    }
                });
    }

    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.add(4);
        integerList.add(5);

        OriginData originData = new OriginData();
        originData.mInput = integerList;

        uploadImages(originData)
                .observeOn(Schedulers.single())
                .subscribe((d) -> System.out.println(Thread.currentThread() + " subscribe" + d));


        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class OriginData {
        List<Integer> mInput;
        List<UploadData> mOutPut;
    }

    static class UploadData {
        public String mUrl;

        @Override
        public String toString() {
            return mUrl;
        }
    }
}
