package com.treasuresconquests.playground;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class SplashScreen extends JWindow{
    //private final JWindow window;
    private long startTime;
    private int minimumMilliseconds;
    private String pathname
            = "resources/assets/images/japaneseRestaurant2.png";


    public SplashScreen() {
       // window = new JWindow();
        Image img = null;
        try {
            img = ImageIO.read(new File(pathname));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageIcon image = new ImageIcon(img);
        getContentPane().add(
                new JLabel("Congratulations, you won!",
                        image, SwingConstants.CENTER));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(
                (int) ((screenSize.getWidth() - image.getIconWidth()) / 2),
                (int) ((screenSize.getHeight() - image.getIconHeight()) / 2),
                image.getIconWidth(), image.getIconHeight());
    }

//    @Override
//    protected void paintComponent(Graphics g) {
//
//        super.paintComponent(g);
//        Image image = null;
//        try {
//            image = ImageIO.read(new File(pathname));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        if (image != null) {
//            g.drawImage(image, 0, 0, null);
//        }
//    }


    public void show(int minimumMilliseconds) {
        this.minimumMilliseconds = minimumMilliseconds;

        setVisible(true);
        startTime = System.currentTimeMillis();
    }

    public void hide() {
        long elapsedTime = System.currentTimeMillis() - startTime;
        try {
            Thread.sleep(Math.max(minimumMilliseconds - elapsedTime, 0));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       setVisible(false);
    }

//        static void renderSplashFrame(Graphics2D g, int frame) {
//            final String[] comps = {"foo", "bar", "baz"};
//            g.setComposite(AlphaComposite.Clear);
//            g.fillRect(120,140,200,40);
//            g.setPaintMode();
//            g.setColor(Color.BLACK);
//            g.drawString("Loading "+comps[(frame/5)%3]+"...", 120, 150);
//        }
//        public SplashScreenDemo() {
//            super("SplashScreen demo");
//            setSize(300, 200);
//            setLayout(new BorderLayout());
//            Menu m1 = new Menu("File");
//            MenuItem mi1 = new MenuItem("Exit");
//            m1.add(mi1);
//            mi1.addActionListener(this);
//            this.addWindowListener(closeWindow);
//
//            MenuBar mb = new MenuBar();
//            setMenuBar(mb);
//            mb.add(m1);
//            final SplashScreen splash =
//                    SplashScreen.getSplashScreen();
//            if (splash == null) {
//                System.out.println("SplashScreen.getSplashScreen() returned null");
//                return;
//            }
//            Graphics2D g = splash.createGraphics();
//            if (g == null) {
//                System.out.println("g is null");
//                return;
//            }
//            for(int i=0; i<100; i++) {
//                renderSplashFrame(g, i);
//                splash.update();
//                try {
//                    Thread.sleep(90);
//                }
//                catch(InterruptedException e) {
//                }
//            }
//            splash.close();
//            setVisible(true);
//            toFront();
//        }
//        public void actionPerformed(ActionEvent ae) {
//            System.exit(0);
//        }
//
//        private static WindowListener closeWindow = new WindowAdapter(){
//            public void windowClosing(WindowEvent e){
//                e.getWindow().dispose();
//            }
//        };

        public static void main (String args[]) {
            SplashScreen test = new SplashScreen();
            test.show(10000);
            test.hide();
        }
    }
