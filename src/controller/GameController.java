package controller;


import com.apps.util.Prompter;

import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.*;

import com.google.gson.Gson;
import model.NPC;
import model.Player;
import model.SplashScreen;
import model.WorldMap;
import test.TestGSON;
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
    private List<NPC> villainsAndAllies;


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
            System.out.println("Welcome back " + player.getName());
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

        // Print out information about current game
        printGameStatus();

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
            default:
                break;
        }
        // Print out information about current game
        printGameStatus();

        playerSpeaksWithTourGuide();

        if (Math.random() * 100 % 2 != 0) {
            playerInteractsWithRandomNPC();
        }
    }

    private void playerSpeaksWithTourGuide() {
        // TOUR GUIDE GREETS PLAYER AND ASKS FOR COMMAND
        String userInput;
        String parsedUserInput = null;
        String newLocation = null;
        ArrayList<String> options = new ArrayList<>(Arrays.asList("attraction", "restaurant", "weapon store"));
        List<String> attractionChoices = new ArrayList<String>();

        // Find what type of location user would like to visit
        while (null == parsedUserInput) {
            userInput = prompter.prompt("Hi, I am the tour guide. Would" +
                    "you like to visit an attraction, restaurant, or weapon store");

            //parsedUserInput = textParser.parse(userInput, npc.get("tourGuide").getCommands(), options);
            parsedUserInput = textParser.parse(userInput, options);

            if (parsedUserInput.toLowerCase().contains("error")) {
                // TODO: add better error message providing specific instructions to user
                System.out.println(parsedUserInput);
                parsedUserInput = null;
            }
        }
        // Set player attraction
        player.setCurrentAttraction(parsedUserInput);
    }

    private void playerVisitsAirport() {
        String userInput = null;
        String parsedUserInput = null;
        List<String> availableFlights = List.of(countries.keySet().toArray(new String[0]));
        List<String> acceptableCommands = npc.get("airportAgent").getCommands();
        int flightCost = 0;

        System.out.println("Hi! Welcome to Single Airport of " + player.getCurrentCountry() +
                 " Where would you like to visit?");
        System.out.println();

        while (parsedUserInput == null) {
            // Print list of available countries
            for (WorldMap.Countries country : worldMap.getCountries()) {
                if (!country.getName().equals(player.getCurrentCountry())) {
                    System.out.print("Country: " + country.getName());
                    System.out.println(" Price of ticket: " + calculateFlightCost(country));
                    System.out.println();
                }
            }
            userInput = prompter.prompt("Where would you like to go?");
            parsedUserInput = textParser.parse(userInput, acceptableCommands, availableFlights).toLowerCase();

            // Quit App
            // TODO: Modify quit logic to match design. Currently it just abruptly ends app
            if (userInput.toLowerCase().contains("quit")) {
                System.exit(0);
            }
            // Check for error
            if (parsedUserInput.contains("error")) {
                System.out.println(parsedUserInput);
                parsedUserInput = null;
                continue;
            }

            // Attempt to purchase flight
            flightCost = calculateFlightCost(countries.get(parsedUserInput));

            if (player.getAmountOfCash() < flightCost) {
                System.out.println("Sorry, you do not have enough money to purchase this flight");
                parsedUserInput = null;
            }
        }
        player.makePurchase(flightCost);
        player.setCurrentCountry(parsedUserInput);
    }

    private void playerVisitsRestaurant() {
        String npcResponse = getRandomNPCResponse(npc.get("waiter").getResponses());

        System.out.println(npcResponse);
    }

    private void playerVisitsWeaponStore() {
        String npcResponse = getRandomNPCResponse(npc.get("weaponSalesRep").getResponses());

        System.out.println(npcResponse);
    }

    private void playerVisitsAttraction() {
        // find specific attraction

        // have player solve random riddle

    }

    private void playerInteractsWithRandomNPC() {
        //TODO: Incomplete method
        /*
         * As the player navigates the world, they may run into random characters
         * who give gifts or attempt to steal money
         */
        if (Math.random() * 100 % 2 == 0) {
            player.setHealth(-15);
        }
    }

    private void setupGame() {
        gameView = new GameView();
        splashScreen = new SplashScreen();
        player = new Player();
        prompter = new Prompter(new Scanner(System.in));
        textParser = TextParser.getInstance();
        player.setCurrentAttraction("airport");


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

    private void printGameStatus() {
        // Current Country
        System.out.println();
        System.out.println("---------- Current Country ----------");
        System.out.println(player.getCurrentCountry());

        // Weapons
        System.out.println();
        System.out.println("---------- WEAPON INVENTORY ----------");
        for (WorldMap.Countries.WeaponStore.Weapons weapon : player.getWeaponInventory()) {
            System.out.println(weapon.getName());
        }

        // Cash
        System.out.println();
        System.out.println("---------- ACCOUNT BALANCE ----------");
        System.out.println("$" + player.getAmountOfCash());

        // Health
        System.out.println();
        System.out.println("---------- REMAINING XP----------");
        System.out.println(player.getHealth());

        System.out.println();
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

    private String getRandomNPCResponse(List<String> responses) {
        int randomNum = (int) (Math.random() * responses.size());

        return responses.get(randomNum);
    }

    private int calculateFlightCost(WorldMap.Countries destinationCountry) {
        int cost;
        int currentZone = countries.get(player.getCurrentCountry()).getZone();

        cost = destinationCountry.getCost() + ( Math.abs(currentZone - destinationCountry.getZone()) * 100 ) ;

        return cost;
    }
}
