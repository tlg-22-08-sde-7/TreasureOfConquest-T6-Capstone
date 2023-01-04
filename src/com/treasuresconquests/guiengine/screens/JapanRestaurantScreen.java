package com.treasuresconquests.guiengine.screens;

import com.treasuresconquests.app.GUIController;
import com.treasuresconquests.engine.WorldMap;
import com.treasuresconquests.guiclient.ScreenLauncher;
import com.treasuresconquests.guiengine.Handlers;
import com.treasuresconquests.guiengine.other.PurchaseData;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class JapanRestaurantScreen extends JPanel {

    private String restaurantName = "";
    private PurchaseData chosenFood = new PurchaseData();

    private JLabel lblAccountBalance, lblCurrentCountry;
    private JButton btnTreasures, btnXP;
    // private JButton btnAttraction, btnAirport, btnRestaurant, btnConquest;
    private JLabel restaurant;
    private JButton btnBack, btnExitCurrentGame, btnQuit, btnHelp;
    private JLabel question;
    private JComboBox foodMenu;
    private JButton confirmFoodChoiceButton;

    Handlers.QuitHandler handlerQuit = new Handlers.QuitHandler();
    Handlers.HelpHandler handlerHelp = new Handlers.HelpHandler();
    Handlers.ExitCurrentGameHandler handlerExitCurrentGame = new Handlers.ExitCurrentGameHandler();
    Handlers.BackHandler backHandler
            = new Handlers.BackHandler("JapanLandingPage");
    Handlers.FoodHandler foodHandler;
    Handlers.FoodSelectionHandler foodSelectionHandler =
            new Handlers.FoodSelectionHandler(chosenFood);
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
        question = new JLabel("");
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


        btnBack = new JButton("Return to Previous \nLocation");
        btnBack.setBounds(300, 550, 200, 50);
        btnBack.addActionListener(backHandler);

        btnExitCurrentGame = new JButton("Exit current game");
        btnExitCurrentGame.setBounds(525, 550, 150, 50);
        btnExitCurrentGame.addActionListener(handlerExitCurrentGame);

        btnQuit = new JButton("Quit");
        btnQuit.setBounds(700, 550, 150, 50);
        btnQuit.addActionListener(handlerQuit);

        btnHelp = new JButton("Help");
        btnHelp.setBounds(50, 550, 150, 50);
        btnHelp.addActionListener(handlerHelp);


        // Position combobox
        foodMenu = new JComboBox();
        foodMenu.setBounds(790, 180, 100, 50);
        // on item select, set the foodChosen to the
        // selectedItem from comboBox
        foodMenu.addItemListener(foodSelectionHandler);
        // Add a confirm button
        confirmFoodChoiceButton = new JButton("Confirm");
        confirmFoodChoiceButton.setBounds(790, 250, 100, 50);
        // When confirm button is clicked,
        //  calls FoodHandler
        //confirmFoodChoiceButton.addActionListener(foodHandler);

        add(lblAccountBalance);
        add(btnTreasures);
        add(btnXP);
        add(question);
        add(restaurant);
        add(foodMenu);
        add(confirmFoodChoiceButton);

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

    public void init(String currentRestaurantName) {
        System.out.println("Restaurant chosen: " + currentRestaurantName);
        this.restaurantName = currentRestaurantName;

        confirmFoodChoiceButton.removeActionListener(foodHandler);
        foodHandler = new Handlers.FoodHandler(
                guiController, "japan",
                currentRestaurantName, this.chosenFood);
        confirmFoodChoiceButton.addActionListener(foodHandler);

        // load the food items for the selected Restaurant
        Vector<String> items =
                guiController.loadFoodItems(this.restaurantName,
                        "japan");
        // Put items into the JComboBox
        foodMenu.setModel(new DefaultComboBoxModel(items));
        if(items != null && items.size() > 0){
            this.chosenFood.setItem(items.get(0));
        }

        guiController.getPlayer().setCurrentCountry("Japan");
        lblCurrentCountry.setText("Current Country: " +
                guiController.getPlayer().getCurrentCountry());
        lblAccountBalance.setText("Money $" + guiController.getPlayer().getAmountOfCash());
        question.setText(restaurantName);
        String pathname = "";
        if (restaurantName.equalsIgnoreCase("Ise Sueyoshi")) {
            pathname = "resources/assets/images/japaneseRestaurant.jpg";
        }
        else if (restaurantName.equalsIgnoreCase("Sushisho Masa")) {
            pathname = "resources/assets/images/japaneseRestaurant2.png";
        }
        Image image = null;
        // use the restaurant name given to choose the right restaurant image to show
        try {
            image = ImageIO.read(new File(pathname));
            Icon eateryIcon = new ImageIcon(image);
            restaurant.setIcon(eateryIcon);

        } catch (IOException e) {
            e.printStackTrace();
        }
        repaint();
    }

    public void setController(GUIController controller) {
        this.guiController = controller;

    }

}