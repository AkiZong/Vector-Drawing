import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.*;


public class Tools extends JComponent implements Observer{
    public OnPressButton selector;
    public OnPressButton eraser;
    public OnPressButton line;
    public OnPressButton circle;
    public OnPressButton square;
    public OnPressButton fill;
    public Model model;
    public String current_tool;

    public Tools(){
        setMinimumSize(new Dimension(90,120));
        setPreferredSize(new Dimension(120,200));
        setMaximumSize(new Dimension(120,200));
        selector = new OnPressButton("./img/selector.png");
        eraser = new OnPressButton("2");
        line = new OnPressButton("3");
        circle = new OnPressButton("4");
        square = new OnPressButton("5");
        fill = new OnPressButton("6");

        setLayout(new GridLayout(3,2));

        ImageIcon selector_img = new ImageIcon();
        ImageIcon eraser_img = new ImageIcon("./img/eraser.png");
        ImageIcon line_img = new ImageIcon("./img/line.png");
        ImageIcon circle_img = new ImageIcon("./img/circle.png");
        ImageIcon square_img = new ImageIcon("./img/square.png");
        ImageIcon fill_img = new ImageIcon("./img/fill.png");

        try {
            Image im  = ImageIO.read(new File("./img/selector.png"));
            selector_img.setImage(im.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));

            im  = ImageIO.read(new File("./img/eraser.png"));
            eraser_img.setImage(im.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));

            im  = ImageIO.read(new File("./img/line.png"));
            line_img.setImage(im.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));

            im  = ImageIO.read(new File("./img/circle.png"));
            circle_img.setImage(im.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));

            im  = ImageIO.read(new File("./img/square.png"));
            square_img.setImage(im.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));

            im  = ImageIO.read(new File("./img/fill.png"));
            fill_img.setImage(im.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
        } catch (IOException ex) {
            System.out.print("Loading the Pic of tools Failed");
        }

        selector.setIcon(selector_img);
        eraser.setIcon(eraser_img);
        line.setIcon(line_img);
        circle.setIcon(circle_img);
        square.setIcon(square_img);
        fill.setIcon(fill_img);
        selector.updateView();

        add(selector);
        add(eraser);
        add(line);
        add(circle);
        add(square);
        add(fill);

        addController();
    }

    public void addModel(Model m){
        model = m;
    }

    public void addController(){
        selector.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (model.total_shapes() == 0) {
                    JOptionPane.showMessageDialog(null, "There is no shape, you cannot select anything.");
                } else {
                    model.current_tool = "selector";
                    current_tool = "selector";
                    SetFalse();
                    selector.sel = true;
                    model.clicked();
                }
            }
        });

        eraser.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (model.total_shapes() == 0) {
                    JOptionPane.showMessageDialog(null, "There is no shape, you cannot delete anything.");
                } else {
                    model.current_tool = "eraser";
                    model.clear_select();
                    current_tool = "eraser";
                    SetFalse();
                    selector.sel = true;
                    model.clicked();
                }
            }
        });

        line.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                model.current_tool = "line";
                model.clear_select();
                current_tool = "line";
                SetFalse();
                line.sel = true;
                model.clicked();
            }
        });

        circle.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                model.current_tool = "circle";
                model.clear_select();
                current_tool = "circle";
                SetFalse();
                circle.sel = true;
                model.clicked();
            }
        });

        square.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                model.current_tool = "square";
                model.clear_select();
                current_tool = "square";
                SetFalse();
                square.sel = true;
                model.clicked();
            }
        });

        fill.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (model.total_shapes() == 0) {
                    JOptionPane.showMessageDialog(null, "There is no shape, you cannot fill anything.");
                } else {
                    model.current_tool = "fill";
                    model.clear_select();
                    current_tool = "fill";
                    SetFalse();
                    fill.sel = true;
                    model.clicked();
                }
            }
        });

    }


    public void SetFalse(){
        selector.sel = false;
        eraser.sel = false;
        line.sel = false;
        circle.sel = false;
        square.sel = false;
        fill.sel = false;

    }

    public void update(Observable o, Object arg){
        selector.updateView();
        eraser.updateView();
        line.updateView();
        circle.updateView();
        square.updateView();
        fill.updateView();
    }


}
