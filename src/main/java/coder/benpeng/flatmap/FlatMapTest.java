package coder.benpeng.flatmap;

import coder.benpeng.util.Log;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by benpeng.jbp on 2015/11/18.
 */
public class FlatMapTest {
    private static final String TAG = FlatMapTest.class.getSimpleName();

    public static void main(String[] args) {
        Subscriber<Course> subscriber = new Subscriber<Course>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Course course) {
                Log.d(TAG, "course name: " + course.getName());
            }
        };


        Observable.from(buildStudent(5))
                .flatMap(student -> {
                    Log.d(TAG, "student name: " + student.getName());
                    return Observable.from(student.getCourses());
                })
                .subscribe(subscriber);

        final List<String> pathList = new ArrayList<>();
        pathList.add("aaa");
        pathList.add("bbb");

        Observable
                .from(pathList)
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {
                        System.out.println("error");
                        return Observable.error(new Exception("fff"));
                    }
                }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error");
            }

            @Override
            public void onNext(String s) {

            }
        });

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

   private static List<Student> buildStudent(int count) {
       List<Student> students = new ArrayList<Student>();
       for (int i = 0; i < count; i++) {
           String name = "Agent: " + i;
           students.add(new Student(name, buildCourse(name, 1 + (int)(Math.random() * count))));
       }
       return students;
   }

   private static List<Course> buildCourse(String pren, int count) {
        List<Course> courses = new ArrayList<Course>();
       for (int i = 0; i < count; i++) {
           courses.add(new Course(pren + ":" + i));
       }
       return courses;
   }

    static class Student {
        private String mName;
        private List<Course> mCourses;

        public String getName() {
            return mName;
        }

        public List<Course> getCourses() {
            return mCourses;
        }

        public Student(String name, List<Course> courses) {
            mName = name;
            mCourses = courses;
        }
    }

    static class Course {
        private String mName;

        public String getName() {
            return mName;
        }

        public Course(String name) {
            mName = name;
        }
    }
}
