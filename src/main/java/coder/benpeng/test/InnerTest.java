package coder.benpeng.test;

/**
 * Created by jiangbenpeng on 17/01/2018.
 *
 * @author benpeng.jiang
 * @version 1.0.0
 */
public class InnerTest {
    public static void main(String[] args) throws Exception {
        EnvironmentUtils.init("AAA");
        System.out.println("BBB");
        System.out.println(EnvironmentUtils.GeneralParameters.getA());
    }

}
