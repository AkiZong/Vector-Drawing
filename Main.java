import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main extends JFrame implements Observer{

    JMenuBar bar;
    JMenu file, view;
    JMenuItem newfile, loadfile, savefile;
    JMenuItem fullsize, ftw;
    Canvas canvas;
    Tools tools;
    Colorbox colorbox;
    ColorChooser colorchooser;
    Line line;
    Model m;
    boolean FullSize = false;
    boolean Fit_win_or_not = false;


    public Main(){
        setSize(500,400);
        setMinimumSize(new Dimension(740,480));
        //setMaximumSize(new Dimension(1200,1200));
        setTitle("Vector Drawing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Fit_win_or_not = false;

        canvas = new Canvas(Fit_win_or_not);

        canvas.addController();
        line = new Line();
        bar = new JMenuBar();
        file = new JMenu("File");
        view = new JMenu("View");
        newfile = new JMenuItem("New");

        loadfile = new JMenuItem("Load");

        savefile = new JMenuItem("Save");

        fullsize = new JMenuItem("Full size");

        ftw = new JMenuItem("Fit to window");

        file.add(newfile);
        file.add(loadfile);
        file.add(savefile);
        view.add(fullsize);
        view.add(ftw);

        tools = new Tools();
        colorbox = new Colorbox();
        colorchooser = new ColorChooser("s");
        bar.add(file);
        bar.add(view);
        this.setJMenuBar(bar);

        Model model = new Model();
        m = model;
        tools.addModel(m);
        line.addModel(m);
        colorbox.addModel(m);
        canvas.addModel(m);
        colorchooser.addModel(m);

        model.addObserver(tools);
        model.addObserver(colorbox);
        model.addObserver(line);
        model.addObserver(canvas);
        model.addObserver(this);
    }

    public void setLayout(){
        JPanel tmp = new JPanel();
        tmp.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 0;
        c.weighty = 0;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.gridx = 0;
        c.gridy = 0;
        tmp.add(tools,c);
        c.gridy = 1;
        tmp.add(colorbox,c);
        c.gridy = 2;
        tmp.add(colorchooser,c);
        c.gridy = 3;
        tmp.add(line,c);
        c.fill = GridBagConstraints.VERTICAL;

        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 4;

        c.fill = GridBagConstraints.BOTH;
        if (!Fit_win_or_not) {
            tmp.add(canvas.scrollFrame, c);
        }else tmp.add(canvas,c);
        this.add(tmp);
    }

    public void addController(){

        savefile.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (m.total_shapes() == 0) {
                    JOptionPane.showMessageDialog(null, "You cannot save a blank file.");
                } else {
                    save();
                }
            }
        });

        loadfile.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (m.total_shapes() == 0) {
                    load();
                } else {
                    int reply = JOptionPane.showConfirmDialog(null, "Do you want to save current image?", "New File", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                        save();
                        load();
                    } else {
                        load();
                    }
                }
            }
        });

        newfile.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (m.total_shapes() == 0) {
                    m.new_can();

                    m.current_thickness = 1;
                    m.current_color = Color.BLACK;
                    m.current_select = -1;
                    m.selector = false;
                    m.current_tool = "line";

                    m.clear_select();
                    m.num_Drawings = 0;
                    m.clicked();
                } else {
                    JOptionPane j = new JOptionPane();
                    JDialog jd = j.createDialog(null, "Do you want to save current image?");
                    jd.setLocation(100, 100);
                    int reply = j.showConfirmDialog(null, "Do you want to save current image?", "New File", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                        save();
                    } else {
                        m.new_can();
                        m.num_Drawings = 0;
                        m.clicked();
                    }
                }
            }
        });

        ftw.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                Fit_win_or_not = true;
                canvas.ftw = true;
                m.clicked();
            }
        });

        fullsize.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                FullSize = true;
                canvas.fs = true;
                m.clicked();
            }
        });

        fullsize.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                Fit_win_or_not = false;
                canvas.ftw = false;
                m.clicked();
            }
        });
    }

    public void update(Observable o, Object arg){
        if (Fit_win_or_not) {
            this.setSize(500,500);
            canvas.scrollFrame.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            canvas.scrollFrame.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        }else{
            canvas.scrollFrame.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            canvas.scrollFrame.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            canvas.repaint();
        }
    }

    public static void main(String[] args){
        Main drawing = new Main();
        drawing.addController();
        drawing.setLayout();
        drawing.setBackground(Color.WHITE);
        drawing.setVisible(true);
    }

    public void save(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");
        File workingDirectory = new File(System.getProperty("user.dir"));
        fileChooser.setCurrentDirectory(workingDirectory);

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try {
                FileWriter csv = new FileWriter(fileToSave);
                csv.write(m.info());
                csv.close();
            }catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void load(){
        m.new_can();
        m.num_Drawings = 0;

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to load");
        File workingDirectory = new File(System.getProperty("user.dir"));
        fileChooser.setCurrentDirectory(workingDirectory);

        int userSelection = fileChooser.showOpenDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File loadfile = fileChooser.getSelectedFile();
            try {
                Scanner sc = new Scanner(loadfile);
                int num = sc.nextInt();
                for (int i = 0; i < num; i++){

                    int index = sc.nextInt();
                    int start_x = sc.nextInt();
                    int start_y = sc.nextInt();
                    int end_x  = sc.nextInt();
                    int end_y = sc.nextInt();
                    String shape = sc.next();
                    if("circle".equals(shape)) shape = "circle";
                    if("square".equals(shape)) shape = "square";
                    if("line".equals(shape)) shape = "line";
                    Color current_color = new Color(Integer.parseInt(sc.next()));
                    int line_thickness = sc.nextInt();
                    Color fil_color = new Color(Integer.parseInt(sc.next()));
                    boolean fill = sc.nextBoolean();
                    boolean sel = sc.nextBoolean();

                    m.drawing_star(new Point(start_x,start_y),shape,line_thickness,current_color);
                    m.drawing_end(new Point(end_x,end_y));
                    m.get_comp(i).fill = fill;
                    m.get_comp(i).sel = sel;
                    m.get_comp(i).fill_color = fil_color;
                }
                m.clicked();

            }catch (FileNotFoundException e){
                System.out.print("LOAD ERROR");
            }
        }
    }
}
