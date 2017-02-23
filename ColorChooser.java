import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;

public class ColorChooser extends JComponent {
    public Model model;
    ActionEvent ae = null;
    String text = null;



    public ColorChooser(String s) {
        text = s;
        this.setMinimumSize(new Dimension(90,30));
        this.setPreferredSize(new Dimension(120,60));
        this.setMaximumSize(new Dimension(120,60));
        setBorder(BorderFactory.createRaisedBevelBorder());
        setOpaque(true);

        this.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                setBorder(BorderFactory.createLoweredBevelBorder());
                Color c = JColorChooser.showDialog(null, "Choose a color",Color.BLUE);
                model.current_color = c;
                if(model.selector){
                    model.get_comp(model.current_select).current_color = c;
                }
                model.clicked();
                repaint();
            }

            public void mouseReleased(MouseEvent e){
                setBorder(BorderFactory.createRaisedBevelBorder());
                repaint();
            }

            public void mouseEntered(MouseEvent e){
                setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,
                        Color.WHITE, Color.BLACK));
                repaint();
            }
            public void mouseExited(MouseEvent e){
                setBorder(BorderFactory.createRaisedBevelBorder());
                repaint();
            }

        });
    }

    public void addActionListener(ActionListener l) {
        listenerList.add(ActionListener.class, l);
    }

    public void removeActionListener(ActionListener l) {
        listenerList.remove(ActionListener.class, l);
    }


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawString("    Chooser",3,20);
    }

    public void addModel(Model m){
        model = m;
    }
}

