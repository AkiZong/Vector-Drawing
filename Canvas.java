import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;
import java.util.Observable;
import java.util.List;
import java.util.ArrayList;

public class Canvas extends JPanel implements Observer{

    public Model model;
    public Point start_point;
    public Point end_point;
    public boolean ftw, fs;
    int width,height;
    public JScrollPane scrollFrame;
    boolean resizeing = false;

    public Canvas(boolean f){
        width = 1200;
        height = 1200;
        ftw = f;
        setBackground(Color.WHITE);
        setMinimumSize(new Dimension(640,480));
        setPreferredSize(new Dimension(800,800));
        setMaximumSize(new Dimension(1200,1200));
        setLayout(new GridBagLayout());
        scrollFrame = new JScrollPane(this);
        this.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension(800,800));
    }

    @Override
    public void paintComponent(Graphics g) {

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    model.clear_select();
                    model.clicked();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        this.setFocusable(true);
        this.requestFocusInWindow();

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.drawRect(0, 0, 2000, 2000);
        for (int i = 0; i < model.components.size(); i++) {
            if ((model.get_comp(i).start_x == model.get_comp(i).end_x) &&
                    (model.get_comp(i).start_y == model.get_comp(i).end_y)) {
            } else {
                if (model.get_comp(i).shape == "circle") {
                    if (model.get_comp(i).fill) {
                        g2.setColor(model.get_comp(i).fill_color);
                        g2.fillOval(model.circle_start_x(i), model.circle_start_y(i), model.circle_r(i) * 2, model.circle_r(i) * 2);
                    }
                    g2.setColor(model.get_comp(i).current_color);
                    g2.setStroke(new BasicStroke(model.get_comp(i).line_thickness));
                    if (model.get_comp(i).sel) {
                        g2.setStroke(new BasicStroke(1));
                        g2.drawRect(model.get_comp(i).end_x, model.get_comp(i).end_y, 10, 10);
                        g2.fillRect(model.get_comp(i).end_x, model.get_comp(i).end_y, 10, 10);
                        float[] dash1 = {2f, 0f, 0f};
                        BasicStroke bs = new BasicStroke(model.get_comp(i).line_thickness, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1.0f, dash1, 2f);
                        g2.setStroke(bs);
                    }
                    g2.drawOval(model.circle_start_x(i), model.circle_start_y(i), model.circle_r(i) * 2, model.circle_r(i) * 2);

                } else if (model.get_comp(i).shape == "square") {
                    if (model.get_comp(i).fill) {
                        g2.setColor(model.get_comp(i).fill_color);
                        g2.fillRect(model.drawing_start_x(i), model.drawing_start_y(i), model.drawing_width(i), model.drawing_height(i));
                    }
                    g2.setStroke(new BasicStroke(model.get_comp(i).line_thickness));
                    g2.setColor(model.get_comp(i).current_color);
                    if (model.get_comp(i).sel) {
                        g2.setStroke(new BasicStroke(1));
                        g2.drawRect(model.get_comp(i).end_x, model.get_comp(i).end_y, 10, 10);
                        g2.fillRect(model.get_comp(i).end_x, model.get_comp(i).end_y, 10, 10);
                        float[] dash1 = {2f, 0f, 0f};
                        BasicStroke bs = new BasicStroke(model.get_comp(i).line_thickness, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1.0f, dash1, 2f);
                        g2.setStroke(bs);
                    }
                    g2.drawRect(model.drawing_start_x(i), model.drawing_start_y(i), model.drawing_width(i), model.drawing_height(i));
                } else if (model.get_comp(i).shape == "line") {
                    g2.setColor(model.get_comp(i).current_color);
                    g2.setStroke(new BasicStroke(model.get_comp(i).line_thickness));
                    if (model.get_comp(i).sel) {
                        g2.setStroke(new BasicStroke(1));
                        g2.drawRect(model.get_comp(i).end_x, model.get_comp(i).end_y, 10, 10);
                        g2.fillRect(model.get_comp(i).end_x, model.get_comp(i).end_y, 10, 10);
                        float[] dash1 = {2f, 0f, 0f};
                        BasicStroke bs = new BasicStroke(model.get_comp(i).line_thickness, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1.0f, dash1, 2f);
                        g2.setStroke(bs);
                    }
                    g2.drawLine(model.get_comp(i).start_x, model.get_comp(i).start_y, model.get_comp(i).end_x, model.get_comp(i).end_y);
                }
            }
        }
    }

    public void addModel(Model m){
        model = m;
    }

    public void addController() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                resizeing = false;
                start_point = e.getPoint();
                if (model.draw_or_not()) {
                    model.clear_select();
                    model.drawing_star(start_point, model.current_tool, model.current_thickness, model.current_color);
                } else {
                    if (model.selector) {
                        if (model.current_tool == "eraser") {
                            if (model.total_shapes() == 0) {
                                JOptionPane.showMessageDialog(null, "There is no shape, you cannot delete anything.");
                            } else {
                                model.current_tool = "eraser";
                                model.clear_select();
                                model.clicked();
                            }
                        } else if ((e.getX() > model.get_comp(model.current_select).end_x) &&
                                (e.getX() < model.get_comp(model.current_select).end_x + 10)) {
                            if ((e.getY() > model.get_comp(model.current_select).end_y) &&
                                    (e.getY() < model.get_comp(model.current_select).end_y + 10)) {
                                resizeing = true;
                                start_point = e.getPoint();
                                return;
                            }
                        }
                    }
                    if (model.current_tool == "selector") {
                        int index = model.select_index(e.getX(), e.getY());
                        if (index == -1) {
                            model.clear_select();
                            model.clicked();
                            return;
                        }
                        model.current_color = model.components.get(index).current_color;
                        model.current_thickness = model.components.get(index).line_thickness;
                        model.clear_select();
                        model.components.get(index).sel = true;
                        model.selector = true;
                        model.current_select = index;
                        model.clicked();
                    } else if (model.current_tool == "fill") {
                        model.fill(e.getX(), e.getY());
                        model.clear_select();
                        model.clicked();
                    } else if (model.current_tool == "eraser") {
                        model.clear_select();
                        int index = model.select_index(e.getX(), e.getY());
                        if (index == -1) {
                            model.clicked();
                            return;
                        } else {
                            model.num_Drawings--;
                            model.components.remove(index);
                            model.clicked();
                        }
                    }
                }
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (model.draw_or_not()) {
                    super.mouseDragged(e);
                    end_point = e.getPoint();
                    model.drawing_end(end_point);
                    model.clicked();
                } else {
                    super.mouseDragged(e);
                    end_point = e.getPoint();
                    int dx = (int) -start_point.getX() + (int) end_point.getX();
                    int dy = (int) -start_point.getY() + (int) end_point.getY();
                    start_point = e.getPoint();
                    if (model.current_tool == "selector") {
                        if (resizeing) {
                            model.get_comp(model.current_select).end_x = model.get_comp(model.current_select).end_x + dx;
                            model.get_comp(model.current_select).end_y = model.get_comp(model.current_select).end_y + dy;
                            model.clicked();
                        } else {
                            for (int i = 0; i < model.components.size(); i++) {
                                if (model.get_comp(i).sel == true) {
                                    model.get_comp(i).start_x = model.get_comp(i).start_x + dx;
                                    model.get_comp(i).start_y = model.get_comp(i).start_y + dy;
                                    model.get_comp(i).end_x = model.get_comp(i).end_x + dx;
                                    model.get_comp(i).end_y = model.get_comp(i).end_y + dy;
                                    model.clicked();
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    public void update(Observable o, Object arg){
        repaint();
    }


    public void ftw_full_size(){
        System.out.print(width);
        float wid_timer = 1000/width;
        float height_timer = 1000/height;
        System.out.print(wid_timer);
        for (int i = 0; i < model.components.size(); i++){
            model.get_comp(i).start_x =(int) Math.floor(model.get_comp(i).start_x * wid_timer);
            model.get_comp(i).end_x = (int) Math.floor(model.get_comp(i).end_x * wid_timer);
            model.get_comp(i).start_y = (int) Math.floor(model.get_comp(i).start_y * height_timer);
            model.get_comp(i).end_y = (int) Math.floor(model.get_comp(i).end_y * height_timer);
        }
    }

    public void record_size(){
        width = this.getWidth();
        height = this.getHeight();
}

}
