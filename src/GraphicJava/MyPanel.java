package GraphicJava;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

class MyPanel extends JPanel {
    JTextField textfield1, textfield2, textfield3;
    float xx0 = 300, yy0 = 100;
    MyPanel(){
        ActionListener listener = new MyListener();
        JButton buttonU = new JButton("Up");
        JButton buttonD = new JButton("Down");
        JButton buttonL = new JButton("Left");
        JButton buttonR = new JButton("Right");

        buttonU.addActionListener(listener);
        buttonD.addActionListener(listener);
        buttonL.addActionListener(listener);
        buttonR.addActionListener(listener);
        add(buttonU);
        add(buttonD);
        add(buttonL);
        add(buttonR);
    }

    GeneralPath DrawSixUg(int R, float xx0, float yy0){
        double alpha = Math.PI * 120 / 180;
        float ww = R * 2, hh = R * 2;
        float  xx = xx0 + ww, yy = yy0 + hh/2;

        GeneralPath sixug = new GeneralPath(GeneralPath.WIND_EVEN_ODD,12);
        sixug.reset();
        sixug.moveTo(xx,yy);
        sixug.lineTo(xx + Math.cos(alpha) * R,  yy - Math.sin(alpha) * R);
        xx += Math.cos(alpha) * R;
        yy -= Math.sin(alpha) * R;

        sixug.lineTo(xx - R, yy);
        xx -= R;
        sixug.lineTo(xx + Math.cos(alpha) * R,  yy + Math.sin(alpha) * R);
        xx += Math.cos(alpha) * R;
        yy += Math.sin(alpha) * R;
        sixug.lineTo(xx - Math.cos(alpha) * R,  yy + Math.sin(alpha) * R);
        xx -= Math.cos(alpha) * R;
        yy += Math.sin(alpha) * R;
        sixug.lineTo(xx + R, yy);

        return sixug;



    }

    public void paintComponent(Graphics g){
        float mx1 = 200, my1 = 200, mx2 = 700, my2 = 400;
        int n = 10, m = 6;
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int R=50;

        Area sixugArea = new Area(DrawSixUg(R, xx0, yy0));

        Rectangle.Float rect1 = new Rectangle2D.Float(mx1, my1, Math.abs(mx2 - mx1), Math.abs(my2 - my1));
        g2.setStroke(new BasicStroke(2.0f));
        g2.setPaint(Color.BLACK);
        g2.draw(rect1);

        float w1 = Math.abs(mx2-mx1)/m,  h1 = Math.abs(my2-my1)/n;
        float x0 = mx1;
        int count = 0;
        for (int i = 0; i<n; i++){
            for (int j = 0; j<m; j++){
                if (sixugArea.intersects(new Rectangle2D.Float(x0, my1, w1, h1))){
                    g2.setPaint(Color.GRAY);
                    g2.fill(new Rectangle2D.Float(x0, my1, w1, h1));
                    count++;
                }
                g2.setPaint(Color.BLACK);
                g2.draw(new Rectangle2D.Float(x0, my1, w1, h1));
                x0 += w1;
            }
            my1 += h1;
            x0 = mx1;
        }

        g2.setPaint(Color.BLUE);
        g2.draw(rect1);

        g2.setPaint(Color.ORANGE);
        g2.fill(sixugArea);
        g2.setPaint(Color.BLACK);
        g2.draw(sixugArea);

        g2.drawString("Количество прямоугольников:"+count, 50, 50);

    }

    public class MyListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            // TODO Auto-generated method stub
            if (event.getActionCommand().equals("Up")) {
                yy0 -= 30;
            }
            if (event.getActionCommand().equals("Down")) {
                yy0 += 30;
            }
            if (event.getActionCommand().equals("Left")) {
                xx0 -= 30;
            }
            if (event.getActionCommand().equals("Right")) {
                xx0 += 30;
            }
            repaint();
        }

    }
}
