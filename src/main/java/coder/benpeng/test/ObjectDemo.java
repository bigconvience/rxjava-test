package coder.benpeng.test;

/**
 * Created by jiangbenpeng on 12/01/2018.
 *
 * @author benpeng.jiang
 * @version 1.0.0
 */
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ObjectDemo extends Object {

    private List synchedList;

    public ObjectDemo() {
        // create a new synchronized list to be used
        synchedList = Collections.synchronizedList(new LinkedList());
    }

    // method used to remove an element from the list
    public String removeElement() throws InterruptedException {
        synchronized (synchedList) {

            // while the list is empty, wait up to 10 seconds
            while (synchedList.isEmpty()) {
                System.out.println(Thread.currentThread().getName() + ": List is empty...");
                synchedList.wait(10000);
                System.out.println(Thread.currentThread().getName() + ": Waiting...");
            }
            String element = (String) synchedList.remove(0);
            System.out.println(Thread.currentThread().getName() + ": remove");
            return element;
        }
    }

    // method to add an element in the list
    public void addElement(String element) {
        System.out.println("Opening...");
        synchronized (synchedList) {

            // add an element and notify all that an element exists
            synchedList.add(element);
            System.out.println("New Element:'" + element + "'");

            synchedList.notifyAll();
            System.out.println("notifyAll called!");
        }
        System.out.println("Closing...");
    }

    public static void main(String[] args) {
        final ObjectDemo demo = new ObjectDemo();

        Runnable runA = new Runnable() {

            public void run() {
                try {
                    String item = demo.removeElement();
                    System.out.println(Thread.currentThread().getName() + ": " + item);
                } catch (InterruptedException ix) {
                    System.out.println(Thread.currentThread().getName() + ": Interrupted Exception!");
                } catch (Exception x) {
                    System.out.println("Exception thrown.");
                }
            }
        };

        Runnable runB = new Runnable() {

            // run adds an element in the list and starts the loop
            public void run() {
                demo.addElement("Hello!");
            }
        };

        try {
            Thread threadA1 = new Thread(runA, "A");
            threadA1.start();

            Thread.sleep(500);

            Thread threadA2 = new Thread(runA, "B");
            threadA2.start();

            Thread.sleep(500);

            Thread threadB = new Thread(runB, "C");
            threadB.start();

            Thread.sleep(1000);

            threadA1.interrupt();
            threadA2.interrupt();
        } catch (InterruptedException x) {
        }
    }
}
