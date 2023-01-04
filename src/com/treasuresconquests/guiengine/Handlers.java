package com.treasuresconquests.guiengine;

import com.treasuresconquests.app.GUIController;
import com.treasuresconquests.engine.WorldMap;
import com.treasuresconquests.guiclient.ScreenLauncher;
import com.treasuresconquests.guiengine.other.Country;
import com.treasuresconquests.guiengine.other.PurchaseData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.Vector;

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
            List<WorldMap.Countries.Restaurant> restaurants
                    = guiController.getRestaurantsForCountry(country);
            // 2. show a JOptionPane with combo box of
            // restaurant choices
            Vector<String> items = new Vector<>();
            for (WorldMap.Countries.Restaurant restaurant: restaurants
                 ) {
                items.add(restaurant.getName());
            }

            //Object[] sports = { "Football", "Cricket", "Squash", "Baseball", "Fencing", "Volleyball", "Basketball" };
            JComboBox comboBox = new JComboBox(items);
            comboBox.setSelectedIndex(0);
            JOptionPane.showMessageDialog(null, comboBox, "Choose a restaurant:",
                    JOptionPane.QUESTION_MESSAGE);
            String selectedRestaurant = (String) comboBox.getSelectedItem();

            // 3. Take that choice and pass to the ScreenLauncher method
            // call ScreenLauncher showRestaurant method
            // passing it the country and
            // restaurant name
            // Inside the show method
            ScreenLauncher.showJapanRestaurantScreen(selectedRestaurant);
        }
    }

    public static class BackHandler implements ActionListener{
        private String preferredDestination;

        public BackHandler(String preferredDestination){
            this.preferredDestination = preferredDestination;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(preferredDestination.equalsIgnoreCase("JapanLandingPage")){
                ScreenLauncher.japanLandingPageLocal();
            }
        }
    }

    public static class FoodHandler implements ActionListener{

        private GUIController guiController;
        private String country;
        private String restaurantName;
        private PurchaseData foodItemSelected;

        public FoodHandler(
                GUIController guiController,
                String country, String restaurantName,
                PurchaseData foodItemSelected){
            this.guiController = guiController;
            this.country = country;
            this.restaurantName = restaurantName;
            this.foodItemSelected = foodItemSelected;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // 1. load restaurant choices for country from
            // GUIController
            List<WorldMap.Countries.Restaurant> restaurants
                    = guiController.getRestaurantsForCountry(country);
            // 2. get the restaurant object for the restaurant name given
            WorldMap.Countries.Restaurant restaurant = null;
            for (WorldMap.Countries.Restaurant restaurantObj: restaurants
            ) {
                if(restaurantObj.getName().
                        equalsIgnoreCase(this.restaurantName)){
                    restaurant = restaurantObj;
                    break;
                }
            }
            // 3. If restaurant is found, find the cost of the
            // food selected
            int cost = 0;
            int healthGain = 0;
            System.out.println("Food item selected " + foodItemSelected);
            if(restaurant != null){
                List<WorldMap.Countries.Restaurant.Items> items =
                        restaurant.getItems();
                for (WorldMap.Countries.Restaurant.Items item : items) {
                    if(item.getName().equalsIgnoreCase(foodItemSelected.getItem())){
                        cost = item.getCost();
                        healthGain = item.getValue();
                        break;
                    }
                }
            }
            // On getting the cost of the food, debit it from the player's wallet

            // check if wallet is funded
            if(guiController.getPlayer().getAmountOfCash() < cost){
                JOptionPane.showMessageDialog(null,
                        "You haven't got enough funds to purchase this food");
                return;
            }
            // if it is, debit him
             int newWalletBalance = guiController.getPlayer().getAmountOfCash() - cost;
            guiController.getPlayer().setAmountOfCash(newWalletBalance);
            // add the food to his health balance
            int newHealth =
                    (guiController.getPlayer().getHealth() + healthGain) > 100 ? 100 :
                            guiController.getPlayer().getHealth() + healthGain;
            guiController.getPlayer().setHealth(newHealth);

            // show the updated balance
            ScreenLauncher.japanLandingPageLocal();
        }
    }

    public static class FoodSelectionHandler implements ItemListener{
        private PurchaseData selectedItem;

        public FoodSelectionHandler(PurchaseData selectedFoodRef){
            this.selectedItem = selectedFoodRef;
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            if(e.getStateChange() == ItemEvent.SELECTED){
                String selected = (String) e.getItem();
                selectedItem.setItem(selected);
            }
        }
    }

    public static class AttractionChoiceHandler implements
            ActionListener{
        private GUIController guiController;
        private String country;

        public AttractionChoiceHandler(
                GUIController guiController,
                String country){
            this.guiController = guiController;
            this.country = country;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // 1. load restaurant choices for country from
            // GUIController
            List<WorldMap.Countries.Attraction> attractions
                    = guiController.getAttractionsForCountry(country);
            // 2. show a JOptionPane with combo box of
            // restaurant choices
            Vector<String> items = new Vector<>();
            for (WorldMap.Countries.Attraction attraction: attractions
            ) {
                items.add(attraction.getName());
            }

            //Object[] sports = { "Football", "Cricket", "Squash", "Baseball", "Fencing", "Volleyball", "Basketball" };
            JComboBox comboBox = new JComboBox(items);
            comboBox.setSelectedIndex(0);
            JOptionPane.showMessageDialog(null, comboBox, "Choose an attraction",
                    JOptionPane.QUESTION_MESSAGE);
            String selectedAttraction = (String) comboBox.getSelectedItem();

            // 3. Take that choice and pass to the ScreenLauncher method
            // call ScreenLauncher showRestaurant method
            // passing it the country and
            // restaurant name
            // Inside the show method
            ScreenLauncher.japanAttractionPage(selectedAttraction);
        }
    }

}