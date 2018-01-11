package coder.benpeng.test;

import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by jiangbenpeng on 11/01/2018.
 *
 * @author benpeng.jiang
 * @version 1.0.0
 */
public class TimeTest {
    public static void main(String[] args) throws Exception {
        Timer myTimer = new Timer();

        myTimer.schedule(new Worker(), 1000, 20);//1秒后执行
//      2012-02-28 09:58:00执行
//        myTimer.schedule(new Worker(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2012-02-28 09:58:00"));
//        myTimer.schedule(new Worker(), 5000,1000);//5秒后执行 每一秒执行一次
//      2012-02-28 09:58:00执行一次 以后每秒执行一次，如果设定的时间点在当前时间之前，任务会被马上执行，然后开始按照设定的周期定时执行任务
//        myTimer.schedule(new Worker(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2012-02-28 09:58:00"),1000);
//        myTimer.scheduleAtFixedRate(new Worker(), 5000,1000);//5秒后执行 每一秒执行一次 如果该任务因为某些原因（例如垃圾收集）而延迟执行，那么接下来的任务会尽可能的快速执行，以赶上特定的时间点
//        myTimer.scheduleAtFixedRate(new Worker(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2012-02-28 09:58:00"),1000);//和上个类似
    }


    public static class Worker extends TimerTask {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                System.out.println("Current: " + System.currentTimeMillis()/1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
