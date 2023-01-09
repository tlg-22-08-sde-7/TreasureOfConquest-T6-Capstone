package com.treasuresconquests.playground.centercards;

import com.treasuresconquests.app.GUIController;
import com.treasuresconquests.guiclient.ScreenLauncher;
import com.treasuresconquests.guiengine.Handlers;
import com.treasuresconquests.guiengine.callbacks.Navigable;
import com.treasuresconquests.playground.BottomRightPanel;
import com.treasuresconquests.playground.TopRightPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class JapanLandingPage extends JPanel implements Navigable {

    private JLabel lblAccountBalance, lblCurrentCountry;
    private JButton btnTreasures, btnXP;
    private JButton btnAttraction, btnAirport, btnRestaurant, btnConquest;
    private JLabel question;

    Handlers.QuitHandler handlerQuit = new Handlers.QuitHandler();
    Handlers.HelpHandler handlerHelp = new Handlers.HelpHandler();
    Handlers.ExitCurrentGameHandler handlerExitCurrentGame = new Handlers.ExitCurrentGameHandler();
    Handlers.RestaurantChoiceHandler restaurantChoiceHandler;
    Handlers.AttractionChoiceHandler attractionChoiceHandler;
    private GUIController guiController;

    public JapanLandingPage(GUIController guiController) {
        setLayout(null);    // set to 'null' because we need full control of the Swing layout.
        this.guiController = guiController;     // this Screen now has data, and can access methods.

        restaurantChoiceHandler =
                new Handlers.RestaurantChoiceHandler(guiController, "japan");
        attractionChoiceHandler =
                new Handlers.AttractionChoiceHandler(guiController, "japan");
//        lblCurrentCountry = new JLabel("Current Country: " + guiController.getPlayer().getCurrentCountry());
//        lblCurrentCountry.setBounds(25, 45, 200, 50);
//        lblCurrentCountry.setForeground(Color.white);
//
//        lblAccountBalance = new JLabel("Money $" + guiController.getPlayer().getAmountOfCash());
//        lblAccountBalance.setBounds(350, 45, 150, 50);
//        lblAccountBalance.setForeground(Color.white);
//
//        btnTreasures = new JButton("Treasures");
//        btnTreasures.setBounds(525, 45, 150, 50);
//
//        btnXP = new JButton("XP");
//        btnXP.setBounds(700, 45, 150, 50);
//
//        question = new JLabel("What would you like to do ?");
//        question.setBounds(300, 150, 400, 50);
//        question.setForeground(Color.white);

        btnAttraction = new JButton("Go to Attraction");
        btnAttraction.setBounds(660, 625, 125, 50);
        btnAttraction.addActionListener(attractionChoiceHandler);

        btnAirport = new JButton("Explore Airport");
        btnAirport.setBounds(60, 625, 125, 50);

        btnRestaurant = new JButton("Go to Restaurant");
        btnRestaurant.setBounds(460, 625, 150, 50);
        btnRestaurant.addActionListener(restaurantChoiceHandler);

        btnConquest = new JButton("Go to Hotel");
        btnConquest.setBounds(260, 625, 125, 50);

        // add(lblAccountBalance); add(btnTreasures); add(btnXP);
        add(btnAttraction); add(btnAirport); add(btnRestaurant); add(btnConquest);
        // add(lblCurrentCountry);
        //add(question);

    }

    // for background
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Image image = null;
        try {
            image = ImageIO.read(new File("resources/assets/images/japanLanding.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (image != null) {
            g.drawImage(image, 0, 0, null);
        }
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Japan Landing");
        GUIController guiController = new GUIController();
        JapanLandingPage jlp = new JapanLandingPage(guiController);
        jFrame.setSize(900, 700);
        jFrame.getContentPane().add(jlp);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);

    }

    public void visaOffice() {
        // your passport is stamped with Japan
        guiController.getPlayer().setCurrentCountry("Japan");
//        lblCurrentCountry.setText("Current Country: " +
//                guiController.getPlayer().getCurrentCountry());
        guiController.getPlayer().setAmountOfCash(guiController.getPlayer().getAmountOfCash() - 1000 );
//        lblAccountBalance.setText("Money $" + guiController.getPlayer().getAmountOfCash());
        BottomRightPanel.showInformationPanel("Hi, I am the tour guide. \n" +
                "Would you like to visit an attraction, explore airport, restaurant, or hotel? ");
        guiController.getPlayer().setCurrentFeature("Airport");
        ScreenLauncher.updateTopRightPanel();

    }
    public void setController(GUIController controller) {
        this.guiController = controller;

    }

    public void localTransit() {
        guiController.getPlayer().setCurrentCountry("Japan");
//        lblCurrentCountry.setText("Current Country: " +
//                guiController.getPlayer().getCurrentCountry());
//        lblAccountBalance.setText("Money $" + guiController.getPlayer().getAmountOfCash());
        guiController.getPlayer().setCurrentFeature("Airport");
        guiController.getPlayer().setCurrentAttraction("Tokyo Airport");
        BottomRightPanel.showInformationPanel("Hi, I am the tour guide. \n" +
                "Would you like to visit an attraction, explore airport, restaurant, or hotel? ");

        ScreenLauncher.updateTopRightPanel();
    }

    @Override
    public void navigateBack() {

    }

    @Override
    public void printSelf() {
        System.out.println("Japan landing page");
    }
}