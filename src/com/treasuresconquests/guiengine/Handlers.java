package com.treasuresconquests.guiengine;

import com.treasuresconquests.app.GUIController;
import com.treasuresconquests.engine.WorldMap;
import com.treasuresconquests.guiclient.ScreenLauncher;
import com.treasuresconquests.guiengine.callbacks.ComboCallback;
import com.treasuresconquests.guiengine.callbacks.Navigable;
import com.treasuresconquests.guiengine.other.*;
import com.treasuresconquests.playground.BottomRightPanel;
import com.treasuresconquests.playground.CenterPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.Vector;

public class Handlers {
    public static Music music = new Music();


    // Static method that can be called from outside
    public static class StartHandler implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {

            ScreenLauncher.frameState = 2;
            //sound

            if (Music.musicOn == true){
                Music.stopMusic();
                Music.playMusic("com/treasuresconquests/guiengine/Songs/nyc.wav", -1);
            }

            //ScreenLauncher.showGameScreen();
            CenterPanel.mainLandingPageScreen();
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
            CenterPanel.showHelpScreen();
        }
    }

    public static class MuteHandler implements ActionListener {

        // prints help with commands / option for instructions(?)
        @Override
        public void actionPerformed(ActionEvent e) {
            CenterPanel.showHelpScreen();
        }
    }

    public static class ExitCurrentGameHandler implements ActionListener {

        // exits to the title/start screen
        @Override
        public void actionPerformed(ActionEvent e) {

            CenterPanel.showStartScreen();
        }
    }

    public static class JapanVisitHandler implements ActionListener {
        private GUIController guiController;

        public JapanVisitHandler(GUIController guiController){
            this.guiController = guiController;

        }
        @Override
        public void actionPerformed(ActionEvent e) {
            ScreenLauncher.frameState = 3;
            if (guiController.getPlayer().getAmountOfCash() >= 1000) {
                CenterPanel.japanLandingPage();
                if (Music.musicClip.isActive()){
                    Music.stopMusic();
                    Music.playMusic("com/treasuresconquests/guiengine/Songs/Tokyo.wav",-1);
                }
            /*    music.stopMusic();
                Music.playMusic("com/treasuresconquests/guiengine/Songs/Tokyo.wav", -1);*/
            }
            else {
                BottomRightPanel.showInformationPanel("You do not have enough funds for this trip.");
            }
        }
    }

    public static class RestaurantChoiceHandler implements
            ActionListener, ComboCallback {
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

            JComboBox<String> comboBox = new JComboBox<>(items);
            comboBox.setSelectedIndex(0);
            JOptionPane.showMessageDialog(null, comboBox,
                    "Choose a restaurant: ", JOptionPane.QUESTION_MESSAGE);
            String selected = (String) comboBox.getSelectedItem();
            itemSelected(selected, "", "");

        }

        @Override
        public void itemSelected(String restaurantChoice, String section, String country) {
            CenterPanel.showJapanRestaurantScreen(restaurantChoice);

        }
    }

    public static class BackHandler implements ActionListener{
        private Navigable currentPage;

        @Override
        public void actionPerformed(ActionEvent e) {
            if(currentPage != null){
                currentPage.printSelf();
                currentPage.navigateBack();
            }
        }

        public void setCurrentPage(Navigable currentPage) {
            this.currentPage = currentPage;
        }

        public Navigable getCurrentPage() {
            return currentPage;
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
                BottomRightPanel.showInformationPanel(
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
            ScreenLauncher.updateTopRightPanel();
            CenterPanel.japanLandingPageLocal();
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
            ActionListener, ComboCallback{
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

            JComboBox<String> comboBox = new JComboBox<>(items);
            comboBox.setSelectedIndex(0);
            JOptionPane.showMessageDialog(null, comboBox,
                    "Choose an attraction: ", JOptionPane.QUESTION_MESSAGE);
            String selected = (String) comboBox.getSelectedItem();
            itemSelected(selected, "", "");

            //Combo combo = new Combo("Choose an attraction:", items);

            //BottomRightPanel.showComboMenuPanel(combo, this);



        }

        // 3. Take that choice and pass to the CenterPanel method
        // passing it the country and
        // restaurant name
        // Inside the show method
        @Override
        public void itemSelected(String selectedAttraction, String section, String country) {
            CenterPanel.japanAttractionPage(selectedAttraction);
        }
    }

    public static class QuizHandler implements ActionListener{
        private ButtonGroup buttonGroup;
        private Quiz quiz;
        private GUIController guiController;

        public QuizHandler(ButtonGroup buttonGroup, Quiz quiz,
                           GUIController guiController) {
            this.buttonGroup = buttonGroup;
            this.quiz = quiz;
            this.guiController = guiController;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String chosenAnswer = buttonGroup.
                    getSelection().getActionCommand();
            if(chosenAnswer.
                    equalsIgnoreCase(quiz.getAnswer())){
                guiController.getPlayer().setAmountOfCash(
                        guiController.getPlayer().getAmountOfCash() + quiz.getMoneyValue()
                );
                guiController.getPlayer().getTreasures().add(quiz.getTreasureValue());
                ScreenLauncher.updateTopRightPanel();
                BottomRightPanel.showInformationPanel("That was the correct answer!\n"+
                        "You have been credited with $" + quiz.getMoneyValue() +
                        "\n and with treasure named "+ quiz.getTreasureValue().getName());
            }
            else{
                BottomRightPanel.showInformationPanel("That was the wrong answer!\n"+
                        "The correct answer is " + quiz.getAnswer());
            }
        }
    }

    public static class ComboHandler implements ActionListener{

        private JComboBox<String> combo;
        private ComboCallback callback;

        public ComboHandler(JComboBox<String> combo){
            this.combo = combo;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String choice = (String) combo.getSelectedItem();
            if(callback != null){
                callback.itemSelected(choice,"", "");
            }
        }

        public ComboCallback getCallback() {
            return callback;
        }

        public void setCallback(ComboCallback callback) {
            this.callback = callback;
        }
    }

}