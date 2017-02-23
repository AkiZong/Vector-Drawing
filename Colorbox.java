import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

public class Colorbox extends JComponent implements Observer {
    public OnPressButton blue;
    public OnPressButton red;
    public OnPressButton org;
    public OnPressButton yellow;
    public OnPressButton green;
    public OnPressButton black;
    public Model model;
    Color current_color;
    public Color blue_curr = Color.BLUE;
    public Color red_curr = Color.RED;
    public Color org_curr = Color.ORANGE;
    public Color yellow_curr = Color.YELLOW;
    public Color green_curr = Color.GREEN;
    public Color black_curr = Color.BLACK;

    public Colorbox(){
        setMinimumSize(new Dimension(90,120));
        setPreferredSize(new Dimension(120,200));
        setMaximumSize(new Dimension(120,200));
        blue = new OnPressButton("");
        red = new OnPressButton("");
        org = new OnPressButton("");
        yellow = new OnPressButton("");
        green = new OnPressButton("");
        black = new OnPressButton("");
        current_color = Color.BLACK;

        setLayout(new GridLayout(3,2));

        blue.setBackground(Color.BLUE);
        red.setBackground(Color.RED);
        org.setBackground(Color.ORANGE);
        yellow.setBackground(Color.YELLOW);
        green.setBackground(Color.GREEN);
        black.setBackground(Color.BLACK);

        black.sel = true;
        black.updateView();
        add(blue);
        add(red);
        add(org);
        add(yellow);
        add(green);
        add(black);
        addController();
    }

    public void addModel(Model m){
        model = m;
    }

    public void addController(){
        blue.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                if (e.getButton() == 1) {
                    current_color = blue_curr;
                    model.current_color = current_color;
                    setFalse();
                    blue.sel = true;
                    if (model.selector) {
                        model.get_comp(model.current_select).current_color = blue_curr;
                    }
                    model.clicked();
                }else{
                    JColorChooser cc = new JColorChooser();
                    Color c = cc.showDialog(null, "Choose a color",blue_curr);
                    if (c != null) {
                        blue.setBackground(c);
                        blue_curr = c;
                        blue.updateView();
                    }
                }
            }
        });

        red.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                if(e.getButton() == 1) {
                    current_color = red_curr;
                    model.current_color = current_color;
                    setFalse();
                    red.sel = true;
                    if (model.selector) {
                        model.get_comp(model.current_select).current_color = red_curr;
                    }
                    model.clicked();
                }else{
                    JColorChooser cc = new JColorChooser();
                    Color c = cc.showDialog(null, "Choose a color",red_curr);
                    if (c != null) {
                        red.setBackground(c);
                        red_curr = c;
                        red.updateView();
                    }
                }
            }
        });

        org.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (e.getButton() == 1) {
                    current_color = org_curr;
                    model.current_color = current_color;
                    setFalse();
                    org.sel = true;
                    if (model.selector) {
                        model.get_comp(model.current_select).current_color = org_curr;
                    }
                    model.clicked();
                }else{
                    JColorChooser cc = new JColorChooser();
                    Color c = cc.showDialog(null, "Choose a color",org_curr);
                    if (c != null) {
                        org.setBackground(c);
                        org_curr = c;
                        org.updateView();
                    }
                }
            }
        });

        yellow.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(e.getButton() == 1) {
                    current_color = yellow_curr;
                    model.current_color = current_color;
                    setFalse();
                    yellow.sel = true;
                    if (model.selector) {
                        model.get_comp(model.current_select).current_color = yellow_curr;
                    }
                    model.clicked();
                }else{
                    JColorChooser cc = new JColorChooser();
                    Color c = cc.showDialog(null, "Choose a color",yellow_curr);
                    if (c != null) {
                        yellow.setBackground(c);
                        yellow_curr = c;
                        yellow.updateView();
                    }
                }
            }
        });

        green.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(e.getButton() == 1) {
                    current_color = green_curr;
                    model.current_color = current_color;
                    setFalse();
                    green.sel = true;
                    if (model.selector) {
                        model.get_comp(model.current_select).current_color = green_curr;
                    }
                    model.clicked();
                }else{
                    JColorChooser cc = new JColorChooser();
                    Color c = cc.showDialog(null, "Choose a color",green_curr);
                    if (c != null) {
                        green.setBackground(c);
                        green_curr = c;
                        green.updateView();
                    }
                }
            }
        });

        black.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(e.getButton() == 1) {
                    current_color = black_curr;
                    model.current_color = current_color;
                    setFalse();
                    black.sel = true;
                    if (model.selector) {
                        model.get_comp(model.current_select).current_color = black_curr;
                    }
                    model.clicked();
                }else{
                    JColorChooser cc = new JColorChooser();
                    Color c = cc.showDialog(null, "Choose a color",black_curr);
                    if (c != null) {
                        black.setBackground(c);
                        black_curr = c;
                        black.updateView();
                    }
                }
            }
        });
    }

    public void setFalse(){
        blue.sel = false;
        red.sel = false;
        org.sel = false;
        black.sel = false;
        green.sel = false;
        yellow.sel = false;

    }

    public void update(Observable o, Object arg){
        if (model.current_color != current_color){
            if (model.current_color == blue_curr){
                setFalse();
                blue.sel = true;
            }else if (model.current_color == red_curr){
                setFalse();
                red.sel = true;
            }else if (model.current_color == org_curr){
                setFalse();
                org.sel = true;
            }else if (model.current_color == black_curr){
                setFalse();
                black.sel = true;
            }else if (model.current_color == green_curr){
                setFalse();
                green.sel = true;
            }else if (model.current_color == yellow_curr){
                setFalse();
                yellow.sel = true;
            }else{
                setFalse();
            }
        }
        blue.updateView();
        black.updateView();
        green.updateView();
        org.updateView();
        yellow.updateView();
        red.updateView();
    }

}
