package com.treasuresconquests.guiengine;

import com.treasuresconquests.app.GUIController;
import com.treasuresconquests.engine.WorldMap;
import com.treasuresconquests.guiclient.ScreenLauncher;
import com.treasuresconquests.guiengine.other.Country;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Handlers {


    // Static method that can be called from outside
    public static class StartHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ScreenLauncher.showGameScreen();
        }
    }

    public static class LoadHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public static class InstructionsHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ScreenLauncher.showHelpScreen();
        }
    }

    public static class QuitHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            System.exit(0);
        }
    }

    public static class HelpHandler implements ActionListener {

        // prints help with commands / option for instructions(?)
        @Override
        public void actionPerformed(ActionEvent e) {
            ScreenLauncher.showHelpScreen();
        }
    }

    public static class ExitCurrentGameHandler implements ActionListener {

        // exits to the title/start screen
        @Override
        public void actionPerformed(ActionEvent e) {
            //launchTitleScreen();
            ScreenLauncher.restartSession();
            ScreenLauncher.showStartScreen();
        }
    }

    public static class JapanVisitHandler implements ActionListener {
        private GUIController guiController;

        public JapanVisitHandler(GUIController guiController){
            this.guiController = guiController;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if (guiController.getPlayer().getAmountOfCash() >= 1000) {
                ScreenLauncher.japanLandingPage();
            }
        }
    }

    public static class RestaurantChoiceHandler implements
            ActionListener{
        private GUIController guiController;
        private String country;

        public RestaurantChoiceHandler(
                GUIController guiController,
                String country){
            this.guiController = guiController;
            this.country = country;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            // 1. load restaurant choices for country from
            // GUIController
            //List<WorldMap.Countries.Restaurant> restaurants
              //      = guiController.getRestaurantsForCountry(country);
            // 2. show a JOptionPane with combo box of
            // restaurant choices

            // 3. Take that choice and pass to the ScreenLauncher method
            // call ScreenLauncher showRestaurant method
            // passing it the country and
            // restaurant name
            // Inside the show method
            ScreenLauncher.showJapanRestaurantScreen();
        }
    }

}