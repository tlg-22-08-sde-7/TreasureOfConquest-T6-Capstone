package com.treasuresconquests.guiclient;

import com.treasuresconquests.guiengine.screens.GameScreen;
import com.treasuresconquests.guiengine.screens.HelpScreen;
import com.treasuresconquests.guiengine.screens.StartScreen;

import javax.swing.*;
import java.awt.*;

public class ScreenLauncher {

    // tagNames for various screens
    // tagNames declared once to be used anywhere
    static JPanel cards; //a panel that uses CardLayout
    final static String STARTSCREEN = "StartScreen";
    final static String GAMESCREEN = "GameScreen";
    final static String HELPSCREEN = "HelpScreen";

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
        frame.setSize(900, 700);
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
        StartScreen card1 = new StartScreen();
        GameScreen card2 = new GameScreen();
        HelpScreen card3 = new HelpScreen();

        // Create the panel that contains the "cards".
        // CardLayout is a class in Swing that manages the display of each card, and
        // JPanel uses CardLayout to manage the display of its children.
        cards = new JPanel(new CardLayout());   // 'cards' are aka parent JPanel.
        // cards.setSize(900, 700);
        cards.add(card1, STARTSCREEN);  // associating the tagNames to cards JPanel
        cards.add(card2, GAMESCREEN);
        cards.add(card3, HELPSCREEN);

        // parent added to window.
        // contentPane is the Bigger JPanel (JFrame) that holds cards,
        // and centers the children
        contentPane.add(cards, BorderLayout.CENTER);
    }

    public static void showGameScreen() {
        // to show new screen, CardLayout is fetched 1st,
        // then told to show a new screen using the tagName
        CardLayout cl = (CardLayout) (cards.getLayout()); // cast give ou the type you want.
        cl.show(cards, GAMESCREEN);
    }

    public static void showStartScreen() {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, STARTSCREEN);
    }

    public static void showHelpScreen() {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, HELPSCREEN);
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

}