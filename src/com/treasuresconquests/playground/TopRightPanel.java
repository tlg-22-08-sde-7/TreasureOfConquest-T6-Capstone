package com.treasuresconquests.playground;

import com.treasuresconquests.app.GUIController;
import com.treasuresconquests.guiclient.ScreenLauncher;
import com.treasuresconquests.guiengine.other.Utilities;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class TopRightPanel extends JPanel {

    private JLabel healthText;
    private JLabel healthValue;
    private JLabel locationText;
    private JLabel locationValue;//mt fuji, Japan
    private JLabel featureText;
    private JLabel featureValue; //Attraction
    private JLabel moneyText;
    private JLabel moneyValue;
    private JLabel treasureText;
    private JList treasureList;
    private GUIController guiController;

    public TopRightPanel(GUIController guiController){
        this.guiController = guiController;
        setLayout(new GridLayout(5,2));
        healthText = new JLabel("Health: ");
        int health = guiController.getPlayer().getHealth();
        healthValue = new JLabel("" + health);
        locationText = new JLabel("Location: ");
        locationValue = new JLabel(extractLocation());
        featureText = new JLabel("Feature: ");
        featureValue = new JLabel(guiController.getPlayer().getCurrentFeature());
        moneyText = new JLabel("Money: ");
        moneyValue = new JLabel("" + guiController.getPlayer().getAmountOfCash());
        treasureText = new JLabel("Rewards: ");
        treasureList = new JList(loadTreasures());
        JScrollPane scrollPane = new JScrollPane(treasureList);

        add(healthText);
        add(healthValue);
        add(locationText);
        add(locationValue);
        add(featureText);
        add(featureValue);
        add(moneyText);
        add(moneyValue);
        add(treasureText);
        add(scrollPane);
    }

    public void updateValues(){
        healthValue.setText("" + guiController.getPlayer().getHealth());
        locationValue.setText(extractLocation());
        featureValue.setText(guiController.getPlayer().getCurrentFeature());
        moneyValue.setText("" + guiController.getPlayer().getAmountOfCash());
        DefaultListModel model = new DefaultListModel();
        populateModel(model);
        treasureList.setModel(model);
        repaint();
    }

//    private void populateModel(DefaultListModel model) {
//       Vector<String> list = loadTreasures();
//       if(list.size() >= 3){
//           ScreenLauncher.showSplashScreenAndExitGame();
//       }
//       else {
//           for (String item : list) {
//               model.addElement(item);
//           }
//       }
//    }

    // IF I don't wan't rewards = 3
    private void populateModel(DefaultListModel model) {
        Vector<String> list = loadTreasures();

            for (String item : list) {
                model.addElement(item);
            }

    }


    private String extractLocation(){
        return guiController.getPlayer().getCurrentCountry();
    }

    private Vector<String> loadTreasures(){
        return Utilities.convertTreasuresToVector(
                guiController.getPlayer().getTreasures());
    }

}