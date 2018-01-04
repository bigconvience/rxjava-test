package coder.benpeng.javax;

/**
 * Created by jiangbenpeng on 27/12/2017.
 *
 * @author benpeng.jiang
 * @version 1.0.0
 */

import java.awt.*;
import javax.swing.*;

public class Text2 {

    /**
     * @param args
     */
    public Label l1 = new Label("性别");
    public Label l2 = new Label("学历");
    public Label l3 = new Label();
    public JRadioButton s1 = new JRadioButton("男");
    public JRadioButton s2 = new JRadioButton("女", true);
    public JCheckBox s3 = new JCheckBox("初中");
    public JCheckBox s4 = new JCheckBox("高中", true);
    public JCheckBox s5 = new JCheckBox("本科");
    public JCheckBox s6 = new JCheckBox("硕士");
    public ButtonGroup g = new ButtonGroup();

    public void display() {
        JFrame jf = new JFrame("测试程序");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int x = screenSize.height;
        int y = screenSize.width;
        int xx = (x - 200) / 2;
        int yy = (y - 300) / 2;
        jf.setSize(300, 200);
        jf.setVisible(true);
        jf.setLocation(yy, xx);
        JPanel pane = new JPanel(new GridLayout(5, 2));
        jf.setContentPane(pane);
        jf.setResizable(false);
        g.add(s1);
        g.add(s2);
        pane.add(l1);
        pane.add(l3);
        pane.add(s1);
        pane.add(s2);
        pane.add(l2);
        pane.add(s3);
        pane.add(s4);
        pane.add(s5);
        pane.add(s6);
        jf.setVisible(true);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new Text2().display();

    }

}
