package com.treasuresconquests.app;


import com.apps.util.Console;
import com.apps.util.Prompter;

import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.*;

import com.google.gson.Gson;
import com.treasuresconquests.engine.*;

public class TreasuresConApp {
    private GameView gameView;
    private SplashScreen splashScreen;
    private Player player;
    private Prompter prompter;
    private static boolean gameOver = false;
    private WorldMap worldMap;
    private final Map<String, WorldMap.Countries> countries = new HashMap<>();
    private TextParser textParser;
    private final Map<String, NPC> npc = new HashMap<>();
    private String userInput;
    private String parsedUserInput;

    private PlayerVisits playerVisits;

    private PlayerDialog playerDialog;

    // Colors
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    // Constructor
    public TreasuresConApp() {
        setupGame();
    }

    // Business Methods
    public void start() {
        // Introduction
        welcomeScreen();
        Console.pause(2000L);
        Console.clear();

        // Choose whether to start new game
        String newGamePrompt = prompter.prompt("Would you like to start a new game?");

        if(newGamePrompt.toLowerCase().contains("y")){
            player.playerSetup();
        }
        else if(newGamePrompt.toLowerCase().contains("n")){
            endQuest();
            System.exit(0);
        }
        else {
            System.out.println("Please enter yes or no!");
            start();
        }

        // Play game
        while (!isGameOver()){
            play();
        }

        // Game is over
        if (player.getHealth() <= 0) {
            System.out.println(ANSI_RED + "Sorry! Your health is equal to or below 0! You lose :(" + ANSI_RESET);
        } else {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println(ANSI_BLUE + "YOU MADE IT BACK HOME CONGRATS!" + ANSI_RESET);
            System.out.println();

            System.out.println(ANSI_PURPLE + "~~~~~~~ HERE ARE YOUR STATS ~~~~~~~ " + ANSI_RESET);
            System.out.println(ANSI_RED + "Remaining Health: " + player.getHealth() + ANSI_RESET);
            System.out.println(ANSI_GREEN + "Remaining Cash " + player.getAmountOfCash() + ANSI_RESET);
            System.out.print(ANSI_CYAN + "Collected Treasures: " + ANSI_RESET);
            for (int i = 0; i < player.getTreasures().size(); i++) {
                System.out.print(player.getTreasures().get(i).getName());
                if (i < player.getTreasures().size() - 1) {
                    System.out.println(" - ");
                }
            }

            for (WorldMap.Countries.Attraction.Treasures treasure : player.getTreasures()) {
                System.out.print(treasure.getName() + " ");
            }
        }

        // Ask if user wants to play new game
        String newGame = prompter.prompt("Would you like to play again? (Enter 'yes' to play again)");

        if ("yes".equalsIgnoreCase(newGame)) {
            start();
        }
    }

    private void play(){
        // Print out information about current game
        printGameStatus();

        // Explore world
        switch (player.getCurrentAttraction()) {
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
                break;
            default: // error has occurred
                System.out.println(ANSI_RED + "An application error has occurred. The player is set at an attraction" +
                        " that is not detected by the system. Investigate Player.play() for debugging" + ANSI_RESET);
                System.out.println(player.getCurrentAttraction());
                System.exit(0);
        }

        // Check if user has returned home
        if (player.getHomeCountry().equalsIgnoreCase(player.getCurrentCountry())) {
            endQuest();
        } else {
            // Print out information about current game
            printGameStatus();

            // Choose where to go next
            playerSpeaksWithTourGuide();

            // Game decides if player encounters an ally/villain
            if (( (int) (Math.random() * 100) ) % 2 != 0) {

                System.out.println(ANSI_RED + "A random stranger has stopped us! They may be friendly" +
                        " or may be looking to cause harm! Should we talk to them?" + ANSI_RESET);
                userInput = prompter.prompt("Enter 'talk' to engage. All other responses will be ignored.");
                System.out.println();

                if (userInput.toLowerCase().contains("talk")) {
                    playerInteractsWithRandomNPC();
                }
            }
        }
    }

    // Left these reference to the below until we know
    // which section(s) need these methods.
    private void playerSpeaksWithTourGuide() {
        playerDialog.playerSpeaksWithTourGuide();
    }

    private void playerVisitsAirport() {
        playerVisits.playerVisitsAirport();
    }

    private void playerVisitsRestaurant() {
        playerVisits.playerVisitsRestaurant();
    }

    private void playerVisitsWeaponStore() {
        playerVisits.playerVisitsWeaponStore();
    }

    private void playerVisitsAttraction() {
        playerVisits.playerVisitsAttraction();
    }

    // Helper Methods
    private void playerInteractsWithRandomNPC() {
        playerDialog.playerInteractsWithRandomNPC();
    }

    private void playerInteractsWithAlly(NPC ally) {
        playerDialog.playerInteractsWithAlly(ally);
    }

    private void playerBattlesEnemy(NPC enemyNPC) {
        playerDialog.playerBattlesEnemy(enemyNPC);
    }

    private int playerAttacksEnemy(int npcHealth) {
        return playerDialog.playerAttacksEnemy(npcHealth);
    }

    private String getRandomNPCResponse(List<String> responses) {
        return playerDialog.getRandomNPCResponse(responses);
    }

    // Removed solvedRandomAttractionRiddle from here.
    // solvedRandomAttractionRiddle now resides in PlayerVisits()

    // Removed calculateFlightCost from here.
    // calculateFlightCost now resides in PlayerVisits()

    private void setupGame() {
        gameView = new GameView();
        splashScreen = new SplashScreen();
        player = new Player();
        prompter = new Prompter(new Scanner(System.in));
        textParser = TextParser.getInstance();
        player.setCurrentAttraction("airport");

        try {
            createWorld();
            playerDialog = new PlayerDialog(npc, textParser, player, prompter);
            playerVisits = new PlayerVisits(countries, npc, player, prompter, worldMap, textParser, playerDialog);
        } catch (FileNotFoundException e){
            e.printStackTrace();
            System.exit(0);
        }
    }

    private void endQuest(){
        setGameOver(true);
    }

    private void createWorld() throws FileNotFoundException {
        try {
            fillCountryMap();
            fillNPCMap();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    // Countries being initialized here
    private void fillCountryMap() throws FileNotFoundException {
        // deserialize json
        Gson gson = new Gson();
        worldMap = gson.fromJson(new InputStreamReader(TestGSON.getFileFromResourceAsStream("assets/json-files/worldMap.json")), WorldMap.class);

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

    private void printGameStatus() {
        // Current Country
        System.out.println();
        System.out.println(ANSI_CYAN + "---------- CURRENT COUNTRY ----------" + ANSI_RESET);
        System.out.println(player.getCurrentCountry());

        // Weapons
        System.out.println();
        System.out.println(ANSI_YELLOW + "---------- WEAPON INVENTORY ----------" + ANSI_RESET);
        for (WorldMap.Countries.WeaponStore.Weapons weapon : player.getWeaponInventory()) {
            System.out.println("Name: " + weapon.getName() + " | Damage: " + weapon.getDamage());
        }

        // Cash
        System.out.println();
        System.out.println(ANSI_GREEN + "---------- ACCOUNT BALANCE ----------" + ANSI_RESET);
        System.out.println("$" + player.getAmountOfCash());

        // Health
        System.out.println();
        System.out.println(ANSI_RED + "---------- REMAINING XP ----------" + ANSI_RESET);
        System.out.println(player.getHealth());

        //Treasures
        System.out.println();
        System.out.println(ANSI_BLUE + "---------- TREASURES ----------" + ANSI_RESET);
        for (WorldMap.Countries.Attraction.Treasures treasure : player.getTreasures()) {
            System.out.println("Name: " + treasure.getName() + " | " + "Value: " + treasure.getValue());
        }
        System.out.println();
    }

    public void welcomeScreen() {
        gameView.showSplashScreen();
        gameView.showInstructions();
    }

    // Getters and Setters
    private static boolean isGameOver() {
        return gameOver;
    }

    public static void setGameOver(boolean status) {
        gameOver = status;
    }
}
