package coder.benpeng.test;

/**
 * Created by jiangbenpeng on 17/01/2018.
 *
 * @author benpeng.jiang
 * @version 1.0.0
 */
public class EnvironmentUtils {
    private static String sE;

    public static void init(String a) {
        sE = a;
        System.out.println("EnvironmentUtils init: " + sE);
    }

    public static class GeneralParameters {
        private static String sA;

        static {
            System.out.println("static: " + sE);
            init(sE);
        }


        public static void init(String a) {
            sA = a;
            System.out.println("int: " + sA);
        }

        public static String getA() {
            return sA;
        }
    }
}
