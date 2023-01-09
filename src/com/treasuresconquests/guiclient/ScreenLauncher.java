package com.treasuresconquests.guiclient;

import com.treasuresconquests.app.GUIController;
import com.treasuresconquests.guielements.*;

import javax.swing.*;
import java.awt.*;

public class ScreenLauncher {

    // tagNames for various screens
    // tagNames declared once to be used anywhere
    private static BottomPanel bottomPanel;
    private static BottomRightPanel bottomRightPanel;
    private static CenterPanel centerPanel;
    private static TopRightPanel topRightPanel;
    //private static SplashScreen splashScreen;
    public static int frameState = 1;

    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);

        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        //Create and set up the window / JFrame.
        JFrame frame = new JFrame("Treasures of Conquests");
        frame.setLayout(null);
        // was ->> frame.setSize(900, 700);
        frame.setSize(1200, 850);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        // When I ask for a content pane from frame, I get a container.
        addComponentToPane(frame.getContentPane());

        // Display the window.
        // frame.pack();
        center(frame);
        frame.setVisible(true);
    }

    private static void addComponentToPane(Container contentPane) {

        // Create the "cards".
        // creating a new JPanel object, and initializing the screens.
        GUIController guiController = new GUIController();
        centerPanel = new CenterPanel(guiController);
        bottomPanel = new BottomPanel();
        topRightPanel = new TopRightPanel(guiController);
        bottomRightPanel = new BottomRightPanel(guiController);
        //splashScreen = new SplashScreen();

        centerPanel.setBounds(0,0,900,700);
        bottomPanel.setBounds(0,701, 900,150);
        topRightPanel.setBounds(901, 0, 300, 350);
        bottomRightPanel.setBounds(901, 351, 300, 500);

        // parent added to window.
        // contentPane is the Bigger JPanel (JFrame) that holds cards,
        // and centers the children
        contentPane.add(centerPanel);
        contentPane.add(bottomPanel);
        contentPane.add(topRightPanel);
        contentPane.add(bottomRightPanel);
    }

    private static void center(JFrame frame) {

        // get the size of the screen, and on systems with multiple displays,
        // the primary display is used
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        // calculate the new location of the window
        int w = frame.getSize().width;
        int h = frame.getSize().height;

        int x = (dim.width - w) / 2;
        int y = (dim.height - h) / 2;

        // moves this component to a new location, the top-left corner of
        // the new location is specified by the x and y
        // parameters in the coordinate space of this component's parent
        frame.setLocation(x, 15);
    }

    public static void updateTopRightPanel() {
        topRightPanel.updateValues();
    }

    public static void showSplashScreenAndExitGame(){
        SwingUtilities.invokeLater(() -> {
            FinalScreen splash = new FinalScreen();
            try {
                // Make JWindow appear for 10 seconds before disappear
                Thread.sleep(3000);
                splash.dispose();
            } catch(Exception e) {
                e.printStackTrace();
            }
            CenterPanel.showStartScreenWithoutPrompt();
        });

    }
}