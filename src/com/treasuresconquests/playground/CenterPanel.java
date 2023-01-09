package com.treasuresconquests.playground;

import com.treasuresconquests.app.GUIController;
import com.treasuresconquests.guiengine.callbacks.Navigable;
import com.treasuresconquests.guiengine.callbacks.NavigbleSubscriber;
import com.treasuresconquests.playground.centercards.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CenterPanel extends JPanel {
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

    private static CardLayout cardLayout;

    private static  GUIController controller;

    private static CenterPanel cards;

    private static java.util.List<NavigbleSubscriber> subscriberList;

    static {
        subscriberList = new ArrayList<>();
    }

    public CenterPanel(GUIController controller){
        controller = controller;
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        cards = this;

        startScreen = new StartScreen(controller);
        gameScreen = new GameScreen();
        helpScreen = new HelpScreen();
        mainLandingScreen = new MainLandingPageScreen(controller); // this screen has access to the data
        japanLandingScreen = new JapanLandingPage(controller);
        japanRestaurantScreen = new JapanRestaurantScreen(controller);
        japanAttractionScreen = new JapanAttractionScreen(controller);

        add(startScreen, STARTSCREEN);  // associating the tagNames to cards JPanel
        add(gameScreen, GAMESCREEN);
        add(helpScreen, HELPSCREEN);
        add(mainLandingScreen, MAINLANDINGPAGESCREEN);
        add(japanLandingScreen, JAPANLANDINGPAGE);
        add(japanRestaurantScreen, JAPANRESTAURANTPAGE);
        add(japanAttractionScreen, JAPANATTRACTIONPAGE);

    }

    public static void subscribe(NavigbleSubscriber subscriber){
        subscriberList.add(subscriber);
    }

    public static void notifySubscribers(Navigable inFocus){
        for (NavigbleSubscriber subscriber : subscriberList) {
            subscriber.currentPage(inFocus);
        }
    }

    public static void showGameScreen() {
        // to show new screen, CardLayout is fetched 1st,
        // then told to show a new screen using the tagName
       // was ->> cl.show(cards, GAMESCREEN);
        cardLayout.show(cards, MAINLANDINGPAGESCREEN);
    }

    public static void showStartScreen() {
        // ask the user if he wants to exit current game
        // if he says yes, reset the player in GUIController to a new Player
        // navigate to start screen
        int result = JOptionPane.showConfirmDialog(null, "Do you want to exit the current game?",
                "Confirm Exit", JOptionPane.YES_NO_OPTION);
        if(result == JOptionPane.YES_OPTION) {
            CardLayout cl = (CardLayout) (cards.getLayout());
            cl.show(cards, STARTSCREEN);
            notifySubscribers(startScreen);

        }
    }

    public static void showJapanRestaurantScreen(String restaurant) {
        CardLayout cl = (CardLayout) (cards.getLayout());
        japanRestaurantScreen.init(restaurant);
        cl.show(cards, JAPANRESTAURANTPAGE);
        notifySubscribers(japanRestaurantScreen);
    }

    public static void showHelpScreen() {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, HELPSCREEN);
        notifySubscribers(helpScreen);
    }

    public static void mainLandingPageScreen() {
        CardLayout cl = (CardLayout) cards.getLayout();
        cl.show(cards, MAINLANDINGPAGESCREEN);
        mainLandingScreen.init();
        notifySubscribers(mainLandingScreen);
    }

    public static void japanLandingPage() {
        CardLayout cl = (CardLayout) cards.getLayout();
        japanLandingScreen.visaOffice();
        cl.show(cards, JAPANLANDINGPAGE);
        notifySubscribers(japanLandingScreen);
    }

    public static void japanLandingPageLocal() {
        CardLayout cl = (CardLayout) cards.getLayout();
        japanLandingScreen.localTransit();
        cl.show(cards, JAPANLANDINGPAGE);
        notifySubscribers(japanLandingScreen);
    }

    public static void japanAttractionPage(String selectedAttraction) {
        CardLayout cl = (CardLayout) cards.getLayout();
        japanAttractionScreen.init(selectedAttraction);
        cl.show(cards, JAPANATTRACTIONPAGE);
        notifySubscribers(japanAttractionScreen);
    }

    //find out the tag name of te current screen
    // used by the Help screen
    public static String findTagName(Navigable screen){

        if(screen instanceof StartScreen){
            return STARTSCREEN;
        }
        else if(screen instanceof MainLandingPageScreen){
            return MAINLANDINGPAGESCREEN;
        }
        else if(screen instanceof JapanLandingPage){
            return JAPANLANDINGPAGE;
        }
        else if(screen instanceof JapanRestaurantScreen){
            return JAPANRESTAURANTPAGE;
        }
        else if(screen instanceof JapanAttractionScreen){
            return JAPANATTRACTIONPAGE;
        }
        return "";
    }

    public static void showPage(String tagName){
        cardLayout.show(cards, tagName);

        if(tagName.equalsIgnoreCase(STARTSCREEN)){
            notifySubscribers(startScreen);
        }
        else if(tagName.equalsIgnoreCase(MAINLANDINGPAGESCREEN) ){
            notifySubscribers(mainLandingScreen);
        }
        else if(tagName.equalsIgnoreCase(JAPANLANDINGPAGE) ){
            notifySubscribers(japanLandingScreen);
        }
        else if(tagName.equalsIgnoreCase(JAPANRESTAURANTPAGE) ){
            notifySubscribers(japanRestaurantScreen);
        }
        else if(tagName.equalsIgnoreCase(JAPANATTRACTIONPAGE) ){
            notifySubscribers(japanAttractionScreen);
        }
    }

    public static void showStartScreenWithoutPrompt() {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, STARTSCREEN);
        notifySubscribers(startScreen);
    }
}