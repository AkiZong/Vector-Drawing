import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

public class Line extends JComponent implements Observer{

    public int thickness;
    public Model model;

    public Line (){
        thickness = 1;
        setMinimumSize(new Dimension(90,90));
        setPreferredSize(new Dimension(120,200));
        setMaximumSize(new Dimension(120,200));
        setBackground(Color.WHITE);
        addController();
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 =(Graphics2D)g;
        g2.setStroke(new BasicStroke(1));
        g2.drawLine(10,19,75,19);
        g2.setStroke(new BasicStroke(3));
        g2.drawLine(11,33,74,33);
        g2.setStroke(new BasicStroke(5));
        g2.drawLine(12,50,73,50);
        g2.setStroke(new BasicStroke(8));
        g2.drawLine(13,67,72,67);
        if (thickness == 1){
            g.setColor(Color.RED);
            g2.setStroke(new BasicStroke(1));
            g.drawLine(10,19,75,19);
        }else if (thickness == 3){
            g.setColor(Color.RED);
            g2.setStroke(new BasicStroke(3));
            g2.drawLine(11,33,74,33);
        }else if (thickness == 5){
            g.setColor(Color.RED);
            g2.setStroke(new BasicStroke(5));
            g2.drawLine(12,50,73,50);
        }else if (thickness == 8){
            g.setColor(Color.RED);
            g2.setStroke(new BasicStroke(8));
            g2.drawLine(13,67,72,67);
        }
    }

    public void addController(){
        this.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                int tmp_y = e.getY();
                if (tmp_y < 25){
                    thickness = 1;
                    model.current_thickness = thickness;
                    model.clicked();
                }else if (tmp_y < 42){
                    thickness = 3;
                    model.current_thickness = thickness;
                    model.clicked();
                }else if (tmp_y < 58){
                    thickness = 5;
                    model.current_thickness = thickness;
                    model.clicked();
                }else{
                    thickness = 8;
                    model.current_thickness = thickness;
                    model.clicked();
                }
            }
        });
    }

    public void addModel(Model m){
        model = m;
    }

    public void updateModel(){
        model.current_thickness = thickness;
    }

    public void update(Observable o, Object arg){
        if (model.current_thickness != thickness){
            thickness = model.current_thickness;
        }
        repaint();
    }
}
