package com.treasuresconquests.guiengine.screens;

import com.treasuresconquests.app.GUIController;
import com.treasuresconquests.guiclient.ScreenLauncher;
import com.treasuresconquests.guiengine.Handlers;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class JapanRestaurantScreen extends JPanel {

    private JLabel lblAccountBalance, lblCurrentCountry;
    private JButton btnTreasures, btnXP;
    // private JButton btnAttraction, btnAirport, btnRestaurant, btnConquest;
    private JLabel restaurant;
    private JButton btnBack, btnExitCurrentGame, btnQuit, btnHelp;
    private JLabel question;

    Handlers.QuitHandler handlerQuit = new Handlers.QuitHandler();
    Handlers.HelpHandler handlerHelp = new Handlers.HelpHandler();
    Handlers.ExitCurrentGameHandler handlerExitCurrentGame = new Handlers.ExitCurrentGameHandler();
    private GUIController guiController;

    public JapanRestaurantScreen(GUIController guiController) {
        setLayout(null);    // set to 'null' because we need full control of the Swing layout.
        this.guiController = guiController;     // this Screen now has data, and can access methods.

        lblCurrentCountry = new JLabel("Current Country: " + guiController.getPlayer().getCurrentCountry());
        lblCurrentCountry.setBounds(25, 45, 200, 50);
        lblCurrentCountry.setForeground(Color.white);

        lblAccountBalance = new JLabel("Money $" + guiController.getPlayer().getAmountOfCash());
        lblAccountBalance.setBounds(350, 45, 150, 50);
        lblAccountBalance.setForeground(Color.white);

        btnTreasures = new JButton("Treasures");
        btnTreasures.setBounds(525, 45, 150, 50);
        btnXP = new JButton("XP");
        btnXP.setBounds(700, 45, 150, 50);
        question = new JLabel("What would you like to do ?");
        question.setBounds(300, 150, 400, 50);
        question.setForeground(Color.white);

//        btnAttraction = new JButton("Attractions");
//        btnAttraction.setBounds(150, 250, 175, 50);
//        btnAirport = new JButton("Airport");
//        btnAirport.setBounds(425, 250, 175, 50);
//        btnRestaurant = new JButton("Restaurant");
//        btnRestaurant.setBounds(150, 375, 175, 50);
//        btnConquest = new JButton("Conquest");
//        btnConquest.setBounds(425, 375, 175, 50);

        restaurant = new JLabel("");
        restaurant.setBounds(120, 120, 650, 400);


        btnBack = new JButton("Back");
        btnBack.setBounds(350, 550, 150, 50);

        btnExitCurrentGame = new JButton("Exit current game");
        btnExitCurrentGame.setBounds(525, 550, 150, 50);
        btnExitCurrentGame.addActionListener(handlerExitCurrentGame);

        btnQuit = new JButton("Quit");
        btnQuit.setBounds(700, 550, 150, 50);
        btnQuit.addActionListener(handlerQuit);

        btnHelp = new JButton("Help");
        btnHelp.setBounds(50, 550, 150, 50);
        btnHelp.addActionListener(handlerHelp);

        add(lblAccountBalance);
        add(btnTreasures);
        add(btnXP);
        add(question);
        //add(btnAttraction);
        //add(btnAirport);
        //add(btnRestaurant);
        //add(btnConquest);
        add(restaurant);
        add(btnBack);
        add(btnExitCurrentGame);
        add(btnQuit);
        add(btnHelp);
        add(lblCurrentCountry);

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
        JapanRestaurantScreen jlp = new JapanRestaurantScreen(guiController);
        jlp.init("");
        jFrame.setSize(900, 700);
        jFrame.getContentPane().add(jlp);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

    public void init(String restaurantName) {
        // your passport is stamped with Japan
        guiController.getPlayer().setCurrentCountry("Japan");
        lblCurrentCountry.setText("Current Country: " +
                guiController.getPlayer().getCurrentCountry());
        guiController.getPlayer().setAmountOfCash(guiController.getPlayer().getAmountOfCash() - 1000);
        lblAccountBalance.setText("Money $" + guiController.getPlayer().getAmountOfCash());

        Image image = null;
        // use the restaurant name given to choose the right restaurant image to show
        try {
            image = ImageIO.read(new File(
                    "resources/assets/images/japaneseRestaurant.jpg"));
            Icon eateryIcon = new ImageIcon(image);
            restaurant.setIcon(eateryIcon);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setController(GUIController controller) {
        this.guiController = controller;

    }

}