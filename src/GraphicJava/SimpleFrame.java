package GraphicJava;

import java.awt.*;

import javax.swing.*;

class SimpleFrame extends  JFrame {
    public static final int DEFAULT_WIDTH = 1000;
    public static final int DEFAULT_HEIGHT = 800;

    public SimpleFrame(){
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        MyPanel panel = new MyPanel();
        //panel.setBackground(Color.BLUE);
        add(panel);
    }

}
