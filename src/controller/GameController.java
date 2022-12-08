package controller;


import com.apps.util.Prompter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import com.google.gson.Gson;
import model.Player;
import model.SplashScreen;
import model.WorldMap;
import view.GameView;


public class GameController {
    private GameView gameView;
    private SplashScreen splashScreen;
    private Player player;
    private Prompter prompter;
    private static boolean gameOver = false;
    private WorldMap worldMap;
    private WorldMap.CountriesStructure[] countries;
    private TextParser textParser;
    // private NPC waiter;
    // private NPC airportAgent
    // private NPC tourGuide
    // private NPC weaponsAgent

    public GameController() {
        setupGame();
    }

    public void welcomeScreen(){
        gameView.showSplashScreen();
        gameView.showInstructions();
    }

    public void run() {
        String newGamePrompt = prompter.prompt("Would you like to start a new game?");

        if(newGamePrompt.equals("yes")){
            player.playerSetup();
        }
        else if(newGamePrompt.equals("no")){
            System.out.println("Welcome back " + player.getPlayerName());
        }
        else {
            System.out.println("Please enter yes or no!");
        }

        while (!isGameOver()){
            startQuest();
        }

        if (isGameOver()){
            System.out.println("Game Over");
        }

    }

    public void startQuest(){
        String userInput;
        String parsedUserInput;
        String[] listOfCommands;
        String[] listOfNouns;

        switch (player.getPlayerCurrentLocation()) {
            case "airport":
                userInput = playerVisitsAirport();
                break;
            case "restaurant":
                userInput = playerVisitsRestaurant();
                break;
            case "weapon store":
                userInput = playerVisitsWeaponStore();
                break;
            case "attraction":
                userInput = playerVisitsAttraction();
            default:
                break;
    }

        playerSpeaksWithTourGuide();

        if (Math.random() * 100 % 2 != 0) {
            playerInteractsWithRandomNPC();
        }
    }

    private String playerVisitsAirport() {
        String command;
        String[] availableFlights = new String[]{};
        String[] acceptableCommands = new String[]{};

        System.out.println("You are at the airport in "+ player.getPlayerTown() + ". " +
                "In front of you is the desk with airline agent" );

        command = prompter.prompt("Where would you like to go?");

        return command;
    }

    private String playerVisitsRestaurant() {
        String command;

        return command;
    }

    private String playerVisitsWeaponStore() {
        String command;

        return command;
    }

    private String playerVisitsAttraction() {
        String command;

        return command;
    }

    private void playerSpeaksWithTourGuide() {
        // TOUR GUIDE GREETS PLAYER AND ASKS FOR COMMAND
        String userInput;
        String parsedUserInput;


        userInput = prompter.prompt("Hi, I am the tour guide. Where" +
                "would you like to visit?");

    }

    private void playerInteractsWithRandomNPC() {
        /*
         * As the player navigates the world, they may run into random characters
         * who give gifts or attempt to steal money
         */
        int randomNum;
    }

    private void setupGame() {
        gameView = new GameView();
        splashScreen = new SplashScreen();
        player = new Player();
        prompter = new Prompter(new Scanner(System.in));
        textParser = TextParser.getInstance();

        player.setPlayerCurrentLocation("airport");

        try {
            createWorld();
        } catch (FileNotFoundException e){
            e.printStackTrace();
            endQuest();
        }
    }

    public void endQuest(){
        setGameOver(true);
    }

    public static boolean isGameOver() {
        return gameOver;
    }

    public static void setGameOver(boolean gameOver) {
        GameController.gameOver = gameOver;
    }

    private void createWorld() throws FileNotFoundException {
        // deserialize json
        Gson gson = new Gson();
        worldMap = gson.fromJson(new FileReader("assets/json-files/worldMap.json"), WorldMap.class);

        // place country objects into array
        countries = new WorldMap.CountriesStructure[] { worldMap.getMexico(), worldMap.getJapan() };
    }

}
