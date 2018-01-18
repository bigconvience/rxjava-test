package coder.benpeng.rx2.parallel;

import io.reactivex.Observable;

import io.reactivex.schedulers.Schedulers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jiangbenpeng on 18/01/2018.
 *
 * @author benpeng.jiang
 * @version 1.0.0
 */
public class ListParallel {

    private static Observable<List<UploadData>> uploadImage(List<Integer> dataList) {
        List<Observable<UploadData>> tasks = new ArrayList<>();

        for (Integer data : dataList) {
            Observable<UploadData> task = Observable.just(data)
                    .subscribeOn(Schedulers.io())
                    .map((s) -> {
                        System.out.println(Thread.currentThread() + " observe: " + s);
                        UploadData uploadData = new UploadData();
                        uploadData.mUrl = s + " AAA";
                        uploadData.mOriginUrl = s;
                        Thread.sleep(500);
                        return uploadData;
                    });

            tasks.add(task);

        }

        List<UploadData> result = new ArrayList<>();

        // 等待运行结束并收集结果
        for (UploadData dataModel : Observable.merge(tasks).blockingIterable()) {
            result.add(dataModel);
        }

        return Observable.just(result)
                .doOnNext((d) ->
                    sort(d, dataList));
    }

    private static void sort(List<UploadData> uploadDataList, List<Integer> originList) {
        Map<Integer, UploadData> integerStringMap = new HashMap<>();
        for (UploadData uploadData : uploadDataList) {
            integerStringMap.put(uploadData.mOriginUrl, uploadData);
        }
        uploadDataList.clear();
        for (Integer integer : originList) {
            uploadDataList.add(integerStringMap.get(integer));
        }
    }

    private static Observable<List<UploadData>> uploadImages(OriginData originData) {
        return uploadImage(originData.mInput);
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
        public Integer mOriginUrl;

        @Override
        public String toString() {
            return mUrl;
        }
    }
}
