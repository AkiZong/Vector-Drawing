/*
OnPressButton class is based on the example
"custombutton" that provided by professor
*/

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OnPressButton extends JButton {
    ActionEvent ae = null;
    String text = null;
    public boolean sel = false;

    public OnPressButton (String s) {
        text = s;
        this.setMinimumSize(new Dimension(45,45));
        this.setPreferredSize(new Dimension(45,45));
        setBorder(BorderFactory.createRaisedBevelBorder());
        setOpaque(true);

        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if(sel) setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.WHITE, Color.WHITE));
                else setBorder(BorderFactory.createRaisedBevelBorder());
                repaint();
            }

            public void mouseReleased(MouseEvent e) {
                if(sel) setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.WHITE, Color.WHITE));
                else setBorder(BorderFactory.createRaisedBevelBorder());
                repaint();
            }

            public void mouseEntered(MouseEvent e) {
                setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.WHITE, Color.BLACK));
                repaint();
            }

            public void mouseExited(MouseEvent e) {
                if(sel) setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.WHITE, Color.WHITE));
                else setBorder(BorderFactory.createRaisedBevelBorder());
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

    public void updateView(){
        if(sel) setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.WHITE, Color.WHITE));
        else setBorder(BorderFactory.createRaisedBevelBorder());
        repaint();
    }

}
