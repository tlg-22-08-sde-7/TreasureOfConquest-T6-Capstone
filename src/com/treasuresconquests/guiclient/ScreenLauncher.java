package com.treasuresconquests.guiclient;

import com.treasuresconquests.app.GUIController;
import com.treasuresconquests.guiengine.other.Country;
import com.treasuresconquests.guiengine.screens.*;

import javax.swing.*;
import java.awt.*;

public class ScreenLauncher {

    // tagNames for various screens
    // tagNames declared once to be used anywhere
    static JPanel cards; //a panel that uses CardLayout
    final static String STARTSCREEN = "StartScreen";
    final static String GAMESCREEN = "GameScreen";
    final static String HELPSCREEN = "HelpScreen";
    final static String MAINLANDINGPAGESCREEN = "MainLandingPageScreen";
    final static String JAPANLANDINGPAGE = "JapanLandingPage";
    final static String MEXICOLANDINGPAGE = "MexicoLandingPage";
    final static String NIGERIALANDINGPAGE = "NigeriaLandingPage";
    final static String FRANCELANDINGPAGE = "FranceLandingPage";
    final static String JAPANRESTAURANTPAGE = "JapanRestaurantPage";
    final static String JAPANATTRACTIONPAGE = "JapanAttractionPage";

    private static StartScreen startScreen;
    private static GameScreen gameScreen;
    private static HelpScreen helpScreen;
    private static MainLandingPageScreen mainLandingScreen;
    private static JapanLandingPage japanLandingScreen;
    private static JapanRestaurantScreen japanRestaurantScreen;
    private static JapanAttractionScreen japanAttractionScreen;

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
        // was ->> frame.setSize(900, 700);
        frame.setSize(1100, 800);
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
        startScreen = new StartScreen();
        gameScreen = new GameScreen();
        helpScreen = new HelpScreen();
        mainLandingScreen = new MainLandingPageScreen(guiController); // this screen has access to the data
        japanLandingScreen = new JapanLandingPage(guiController);
        japanRestaurantScreen = new JapanRestaurantScreen(guiController);
        japanAttractionScreen = new JapanAttractionScreen(guiController);
        // MexicoLandingPage card6 = new MexicoLandingPage(guiController);

        // Create the panel that contains the "cards".
        // CardLayout is a class in Swing that manages the display of each card, and
        // JPanel uses CardLayout to manage the display of its children.
        cards = new JPanel(new CardLayout());   // 'cards' are aka parent JPanel.
        // cards.setSize(900, 700);
        cards.add(startScreen, STARTSCREEN);  // associating the tagNames to cards JPanel
        cards.add(gameScreen, GAMESCREEN);
        cards.add(helpScreen, HELPSCREEN);
        cards.add(mainLandingScreen, MAINLANDINGPAGESCREEN);
        cards.add(japanLandingScreen, JAPANLANDINGPAGE);
        cards.add(japanRestaurantScreen, JAPANRESTAURANTPAGE);
        cards.add(japanAttractionScreen, JAPANATTRACTIONPAGE);

        // parent added to window.
        // contentPane is the Bigger JPanel (JFrame) that holds cards,
        // and centers the children
        contentPane.add(cards, BorderLayout.CENTER);
    }

    public static void showGameScreen() {
        // to show new screen, CardLayout is fetched 1st,
        // then told to show a new screen using the tagName
        CardLayout cl = (CardLayout) (cards.getLayout()); // cast give ou the type you want.
        // was ->> cl.show(cards, GAMESCREEN);
        cl.show(cards, MAINLANDINGPAGESCREEN);
    }

    public static void showStartScreen() {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, STARTSCREEN);
    }

    public static void showJapanRestaurantScreen(String restaurant) {
        CardLayout cl = (CardLayout) (cards.getLayout());
        japanRestaurantScreen.init(restaurant);
        cl.show(cards, JAPANRESTAURANTPAGE);
    }

    public static void showHelpScreen() {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, HELPSCREEN);
    }

    public static void mainLandingPageScreen() {
        CardLayout cl = (CardLayout) cards.getLayout();
        cl.show(cards, MAINLANDINGPAGESCREEN);
    }

    public static void japanLandingPage() {
        CardLayout cl = (CardLayout) cards.getLayout();
        japanLandingScreen.visaOffice();
        cl.show(cards, JAPANLANDINGPAGE);
    }

    public static void japanLandingPageLocal() {
        CardLayout cl = (CardLayout) cards.getLayout();
        japanLandingScreen.localTransit();
        cl.show(cards, JAPANLANDINGPAGE);
    }

    public static void japanAttractionPage(String selectedAttraction) {
        CardLayout cl = (CardLayout) cards.getLayout();
        japanAttractionScreen.init(selectedAttraction);
        cl.show(cards, JAPANATTRACTIONPAGE);
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


    public static void restartSession() {
        GUIController controller = new GUIController();
        mainLandingScreen.setController(controller);
        japanLandingScreen.setController(controller);
    }

}