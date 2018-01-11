package coder.benpeng.test;

/**
 * Created by jiangbenpeng on 11/01/2018.
 *
 * @author benpeng.jiang
 * @version 1.0.0
 */
public class MonitorTest1 {
    public static void main(String[] args) throws Exception{


        Object lock = new Object();

        Runnable runnable1 = () -> case1(lock);
        Runnable runnable2 = () -> case2(lock);

        Thread thread1 = new Thread(runnable1);
        thread1.start();
        Thread.sleep(500);

        Thread thread2 = new Thread(runnable2);
        thread2.start();
        Thread.sleep(1000);

        synchronized (lock) {
            lock.notifyAll();
        }
        System.out.println("end main thread");
    }

    public static void case1(Object lock) {
        synchronized (lock) {
            System.out.println("case1");
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("case1 release lock");
    }

    public static void case2(Object lock) {
        synchronized (lock) {
            System.out.println("case2");
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("case2 release lock");

    }
}
