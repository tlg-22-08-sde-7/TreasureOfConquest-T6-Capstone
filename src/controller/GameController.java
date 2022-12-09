package controller;


import com.apps.util.Prompter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import com.google.gson.Gson;
import model.NPC;
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
    private Map<String, WorldMap.Countries> countries = new HashMap<>();
    private TextParser textParser;
    private final Map<String, NPC> npc = new HashMap<String, NPC>();
    private List<NPC> randomNPCs;


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
                playerVisitsAirport();
                break;
            case "restaurant":
                playerVisitsRestaurant();
                break;
            case "weapon store":
                playerVisitsWeaponStore();
                break;
            case "attraction":
                playerVisitsAttraction();
            default:
                break;
    }

        playerSpeaksWithTourGuide();

        if (Math.random() * 100 % 2 != 0) {
            playerInteractsWithRandomNPC();
        }
    }

    private void playerVisitsAirport() {
        String userInput = null;
        String parsedUserInput = null;
        String[] availableFlights = countries.keySet().toArray(new String[0]);
        String[] acceptableCommands = npc.get("airportAgent").getCommands();

        System.out.println("You are at the airport in "+ player.getPlayerTown() + ". " +
                "In front of you is the desk with airline agent" );

        while (parsedUserInput == null) {
            userInput = prompter.prompt("Where would you like to go?");
            parsedUserInput = textParser.parse(userInput, acceptableCommands, availableFlights).toLowerCase();

            if (parsedUserInput.contains("error")) {
                System.out.println(parsedUserInput);
                parsedUserInput = null;
            }
        }

        player.setPlayerCurrentLocation(parsedUserInput);
        // take money from player
    }

    private void playerVisitsRestaurant() {

    }

    private void playerVisitsWeaponStore() {

    }

    private void playerVisitsAttraction() {

    }

    private void playerSpeaksWithTourGuide() {
        // TOUR GUIDE GREETS PLAYER AND ASKS FOR COMMAND
        String userInput;
        String parsedUserInput;


        userInput = prompter.prompt("Hi, I am the tour guide. Where" +
                "would you like to visit?");

        //parsedUserInput = textParser.parse(userInput, tourGuide.getCommands(),)
    }

    private void playerInteractsWithRandomNPC() {
        //TODO: Incomplete method
        /*
         * As the player navigates the world, they may run into random characters
         * who give gifts or attempt to steal money
         */
        if (Math.random() * 100 % 2 == 0) {
            player.setPlayerHealth(-15);
        }
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

        try {
            fillCountryMap();
            fillNPCMap();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            // end game
        }

    }

    private void fillCountryMap() throws FileNotFoundException {
        // deserialize json
        Gson gson = new Gson();
        worldMap = gson.fromJson(new FileReader("assets/json-files/worldMap.json"), WorldMap.class);

        for (WorldMap.Countries country : worldMap.getCountries()) {
            countries.put(country.getName().toLowerCase(), country);
        }

    }

    private void fillNPCMap() {
        List<NPC> npcList = new NPC().getNpcList();

        for (NPC item : npcList) {
            npc.put(item.getNpcType(), item);
        }
    }

}
