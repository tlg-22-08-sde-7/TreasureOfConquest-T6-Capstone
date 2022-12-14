package controller;


import com.apps.util.Console;
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
    private final Map<String, WorldMap.Countries> countries = new HashMap<>();
    private TextParser textParser;
    private final Map<String, NPC> npc = new HashMap<>();
    private String userInput;
    private String parsedUserInput;
    // Colors
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public GameController() {
        setupGame();
    }

    public void welcomeScreen(){
        gameView.showSplashScreen();
        gameView.showInstructions();
    }

    public void run() {
        // Introduction
        welcomeScreen();
        Console.pause(2000L);
        Console.clear();

        // Choose whether to start new game
        String newGamePrompt = prompter.prompt("Would you like to start a new game?");

        if(newGamePrompt.equalsIgnoreCase("yes")){
            player.playerSetup();
        }
        else if(newGamePrompt.equalsIgnoreCase("no")){
            System.out.println("Welcome back " + player.getName());
        }
        else {
            System.out.println("Please enter yes or no!");
            run();
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
            for (WorldMap.Countries.Attraction.Treasures treasure : player.getTreasures()) {
                System.out.print(treasure.getName() + " ");
            }
        }
    }

    private void play(){
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

        // Check if user has returned home
        if (player.getHomeCountry().equals(player.getCurrentCountry())) {
            endQuest();
        } else {
            // Print out information about current game
            printGameStatus();

            playerSpeaksWithTourGuide();

            // Game decides if player encounters an ally/villain
            if (( (int) (Math.random() * 100) ) % 2 != 0) {
                System.out.println(ANSI_RED + "A random stranger has stopped us! They may be friendly" +
                        " or may be looking to cause harm! Should we talk to them?" + ANSI_RESET);
                userInput = prompter.prompt("Enter 'talk' to engage. All other responses will be ignored.");
                if (userInput.toLowerCase().contains("talk")) {
                    playerInteractsWithRandomNPC();
                }
            }
        }
    }

    private void playerSpeaksWithTourGuide() {
        // TOUR GUIDE GREETS PLAYER AND ASKS FOR COMMAND
        String userInput;
        String parsedUserInput = null;
        ArrayList<String> options = new ArrayList<>(Arrays.asList("attraction", "restaurant", "weapon store", "airport"));

        // Find what type of location user would like to visit
        while (null == parsedUserInput) {
            userInput = prompter.prompt("Hi, I am the tour guide. Would " +
                    "you like to visit an attraction, airport, restaurant, or weapon store?");

            parsedUserInput = textParser.parse(userInput, npc.get("tourGuide").getCommands(), options);

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
        String userInput;
        String parsedUserInput = null;
        List<String> availableFlights = List.of(countries.keySet().toArray(new String[0]));
        List<String> acceptableCommands = npc.get("airportAgent").getCommands();
        int flightCost = 0;

        while (parsedUserInput == null) {
            System.out.println(ANSI_PURPLE + "Hi! Welcome to the Single Airport of " + player.getCurrentCountry() +
                    "! Where would you like to visit?" + ANSI_RESET);
            System.out.println();

            // Print list of available countries
            for (WorldMap.Countries country : worldMap.getCountries()) {
                if (!country.getName().equals(player.getCurrentCountry())) {
                    System.out.print("Country: " + country.getName());
                    System.out.println(ANSI_GREEN + " Price of ticket: " + calculateFlightCost(country) + ANSI_RESET);
                    System.out.println();
                }
            }
            userInput = prompter.prompt("Where would you like to go?");
            parsedUserInput = textParser.parse(userInput, acceptableCommands, availableFlights).toLowerCase();

            // Check for error
            if (parsedUserInput.toLowerCase().contains("error")) {
                System.out.println(parsedUserInput);
                parsedUserInput = null;
                continue;
            }

            // Attempt to purchase flight
            flightCost = calculateFlightCost(countries.get(parsedUserInput));

            if (player.getAmountOfCash() < flightCost) {
                System.out.println(ANSI_RED + "Sorry, you do not have enough money to purchase this flight" + ANSI_RESET);
                System.out.println(ANSI_GREEN + "Your current balance is " + player.getAmountOfCash() + ANSI_RESET);
                System.out.println();
                parsedUserInput = null;
            }
        }
        // Player is not charged for choosing a flight destination that they're currently visiting
        if (!player.getCurrentCountry().equalsIgnoreCase(parsedUserInput)) {
            player.makePurchase(flightCost);
            player.setCurrentCountry(parsedUserInput);
        }
    }

    private void playerVisitsRestaurant() {
        Map<String, WorldMap.Countries.Restaurant> restaurantsMap = new HashMap<>();
        Map<String, WorldMap.Countries.Restaurant.Items> dishesMap = new HashMap<>();
        List<String> restaurants = new ArrayList<>();
        List<String> dishes = new ArrayList<>();
        String restaurantChoice = null;
        String dishChoice = null;
        String userInput;
        String parsedUserInput;

        // Choose Restaurant
        while (null == restaurantChoice) {
            System.out.println("Here are a list of restaurants. Which would you like to visit?");
            for (WorldMap.Countries.Restaurant restaurant : countries.get(player.getCurrentCountry()).getRestaurants()) {
                restaurantsMap.put(restaurant.getName().toLowerCase(), restaurant);
                restaurants.add(restaurant.getName());
                System.out.println(restaurant.getName());
            }
            System.out.println();

            userInput = prompter.prompt("Which restaurant would you like to visit?");
            parsedUserInput = textParser.parse(userInput, restaurants).toLowerCase();

            if (!parsedUserInput.contains("error")) {
                restaurantChoice = parsedUserInput;
            }
        }

        // Choose Dish
        while (null == dishChoice) {
            String npcResponse = getRandomNPCResponse(npc.get("waiter").getResponses());
            System.out.println(npcResponse);
            System.out.println();

            System.out.println(ANSI_CYAN + "~~~~~~~ HERE IS THE MENU ~~~~~~~" + ANSI_RESET);
            for (WorldMap.Countries.Restaurant.Items dish : restaurantsMap.get(restaurantChoice).getItems()) {
                    System.out.println("Item: " + dish.getName() + " | " + "Added health: " + dish.getValue() +
                            " | " + "Cost: " + dish.getCost());
                    dishes.add(dish.getName());
                    dishesMap.put(dish.getName().toLowerCase(), dish);
            }
            System.out.println(ANSI_CYAN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);

            System.out.println();
            userInput = prompter.prompt("Which dish would you like to order?");
            parsedUserInput = textParser.parse(userInput, npc.get("waiter").getCommands(), dishes).toLowerCase();

            if (!parsedUserInput.contains("error")) {
                System.out.println("You have selected: " + parsedUserInput + "! Your total cost is: " +
                        dishesMap.get(parsedUserInput).getCost());
                System.out.println("This dish will give up to " + dishesMap.get(parsedUserInput).getValue() +
                        " in value!");
                dishChoice = parsedUserInput;
            } else if (dishesMap.get(parsedUserInput).getCost() > player.getAmountOfCash()) {
                System.out.println("Sorry! You only have $" + player.getAmountOfCash() + ". This item costs $" +
                        dishesMap.get(parsedUserInput) + ". Please select a dish that's equal to or below " +
                        player.getAmountOfCash());
            }
        }

        // Player eats dish
        player.eat(dishesMap.get(dishChoice));
    }

    private void playerVisitsWeaponStore() {
        String npcResponse = getRandomNPCResponse(npc.get("weaponSalesRep").getResponses());
        Map<String, WorldMap.Countries.WeaponStore> weaponStoreMap = new HashMap<>();
        Map<String, WorldMap.Countries.WeaponStore.Weapons> weaponsMap = new HashMap<>();
        String weaponStoreChoice = null;
        String weaponChoice = null;
        String userInput;
        String parsedUserInput = null;

        // Choose weapon store
        while (null == weaponStoreChoice) {
            System.out.println("Here is a list of available weapon stores:");
            for (WorldMap.Countries.WeaponStore weaponStore : countries.get(player.getCurrentCountry()).getWeaponStores()) {
                System.out.println(weaponStore.getName());
                weaponStoreMap.put(weaponStore.getName(), weaponStore);
            }
            System.out.println();

            userInput = prompter.prompt("Which weapons store would you like to visit?");
            parsedUserInput = textParser.parse(userInput, List.of(weaponStoreMap.keySet().toArray(new String[0])));

            if (!parsedUserInput.toLowerCase().contains("error")) {
                weaponStoreChoice = parsedUserInput;
            } else {
                System.out.println(parsedUserInput);
                System.out.println();
            }
        }

        // Choose weapon
        while (null == weaponChoice) {
            System.out.println(npcResponse);
            System.out.println();

            System.out.println(ANSI_YELLOW + "~~~~~~ WEAPON'S MENU ~~~~~~" + ANSI_RESET);
            for (WorldMap.Countries.WeaponStore.Weapons weapon : weaponStoreMap.get(parsedUserInput).getWeapons()) {
                System.out.println("This is the " + weapon.getName() + "! It costs " + weapon.getCost() +
                        " and does a total damage of " + weapon.getDamage());
                weaponsMap.put(weapon.getName(), weapon);
            }
            System.out.println();

            userInput = prompter.prompt("Which weapon would you like to purchase?");
            parsedUserInput = textParser.parse(userInput, npc.get("weaponSalesRep").getCommands(),
                    List.of(weaponsMap.keySet().toArray(new String[0])));

            if (!parsedUserInput.toLowerCase().contains("error")) {
                weaponChoice = parsedUserInput;
            } else if (player.getAmountOfCash() < weaponsMap.get(parsedUserInput).getCost()) {
                System.out.println("Sorry! You do not have enough money to purchase the " + parsedUserInput);
                System.out.println();
            } else { // Error occurred
                System.out.println(parsedUserInput); // prints out error message from textparser.parse()
                System.out.println();
            }
        }

        // Purchase weapon
        player.addWeapon(weaponsMap.get(weaponChoice));
        player.makePurchase(weaponsMap.get(weaponChoice).getCost());
    }

    private void playerVisitsAttraction() {
        String attractionChoice = null;
        Map<String, WorldMap.Countries.Attraction> attractionMap = new HashMap<>();
        WorldMap.Countries.Attraction.Treasures treasure;

        // Choose Attraction
        while (attractionChoice == null) {
            System.out.println(ANSI_PURPLE + "~~~~~~~ ATTRACTIONS ~~~~~~~" + ANSI_RESET);
            for (WorldMap.Countries.Attraction attraction : countries.get(player.getCurrentCountry()).getAttractions()) {
                System.out.println(attraction.getName());
                attractionMap.put(attraction.getName().toLowerCase(), attraction);
            }
            System.out.println(ANSI_PURPLE + "~~~~~~~~~~~~~~~~~~~~~~~~~~~" + ANSI_RESET);
            System.out.println();

            userInput = prompter.prompt("Which attraction would you like to visit?");
            parsedUserInput = textParser.parse(userInput, npc.get("tourGuide").getCommands(),
                    List.of(attractionMap.keySet().toArray(new String[0])));

            System.out.println();

            if (!parsedUserInput.toLowerCase().contains("error")) {
                attractionChoice = parsedUserInput;
            } else {
                System.out.println(parsedUserInput);
            }
        }

        // Solve random riddle
        if (solvedRandomAttractionRiddle(attractionMap.get(attractionChoice))) {
            // Give player treasure
            int treasuresSize = attractionMap.get(attractionChoice).getTreasures().size();
            treasure = attractionMap.get(attractionChoice).getTreasures().get( (int) (Math.random() * treasuresSize) );

            player.addTreasure(treasure);
        }
    }

    private void playerInteractsWithRandomNPC() {
        NPC currNPC = (( (int) (Math.random() * 100) ) % 2 == 0) ? npc.get("allies") : npc.get("villains");
        int responsesSize = currNPC.getResponses().size();

        System.out.println(currNPC.getResponses().get((int) (Math.random() * responsesSize)));

        if (currNPC.getNpcType().equals("allies")) {
            playerInteractsWithAlly(currNPC);
        }
        else {
            playerBattlesEnemy(currNPC);
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

    private void endQuest(){
        setGameOver(true);
    }

    private static boolean isGameOver() {
        return gameOver;
    }

    private static void setGameOver(boolean status) {
        gameOver = status;
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

    private void playerInteractsWithAlly(NPC ally) {
        int valuesSize = ally.getValues().size();
        int cashGift = ally.getValues().get((int) (Math.random() * valuesSize));

        System.out.println(ANSI_GREEN + "Hey friend here's $" + cashGift + ANSI_RESET);
        player.gainMoney(cashGift);
    }

    private void playerBattlesEnemy(NPC enemyNPC) {
        int maxNumber = 15;
        int minNumber = 0;
        int randomNumber = (int) (Math.random() * maxNumber);
        int enemyChoice;
        int enemyChoiceDifference;
        int playerChoice;
        int playerChoiceDifference;

        System.out.println(ANSI_RED + "You have run into an enemy who looks to harm you! Use your weapon to " +
                "defeat them!" + ANSI_RESET);
        System.out.println("You and the enemy will guess a number between " + minNumber +
                " and " + maxNumber + "(non-inclusive)");

        // Player Chooses
        // TODO: Add error catching here for when user enters non-number
        playerChoice = Integer.parseInt(prompter.prompt("Enter a number"));
        playerChoiceDifference = Math.abs(randomNumber - playerChoice);

        // Enemy Chooses
        enemyChoice = (int) (Math.random() * maxNumber);
        enemyChoiceDifference = Math.abs(randomNumber - enemyChoice);

        System.out.println(ANSI_RED + "Enemy chose " + enemyChoice + ANSI_RESET);
        System.out.println();
        System.out.println(ANSI_GREEN + "The correct number is " + randomNumber + ANSI_RESET);

        // TODO: Add battle functionality
        if (playerChoiceDifference < enemyChoiceDifference) {
            System.out.println(ANSI_GREEN + "Player attacks enemy!" + ANSI_RESET);
        } else if (enemyChoiceDifference < playerChoiceDifference) {
            System.out.println(ANSI_RED + "Enemy attacks player!" + ANSI_RESET);
        } else {
            System.out.println(ANSI_BLUE + "Draw! You both guessed equally!" + ANSI_RESET);
        }
    }

    private String getRandomNPCResponse(List<String> responses) {
        int randomNum = (int) (Math.random() * responses.size());

        return responses.get(randomNum);
    }

    private boolean solvedRandomAttractionRiddle(WorldMap.Countries.Attraction attraction) {
        boolean solvedRiddle;
        System.out.println(attraction);
        System.out.println(attraction.getRiddles());
        int randomNumber = (int) (Math.random() * attraction.getRiddles().size());
        WorldMap.Countries.Attraction.Riddles riddle = attraction.getRiddles().get(randomNumber);

        System.out.println("Question:");
        System.out.println(riddle.getText());

        System.out.println();

        System.out.println("Here are your options:");
        for (String option : riddle.getOptions()) {
            System.out.println(option);
        }

        System.out.println();

        userInput = prompter.prompt("What is the answer to the question?");
        parsedUserInput = textParser.parse(userInput, riddle.getOptions());

        solvedRiddle = riddle.getAnswer().equals(parsedUserInput);

        return solvedRiddle;
    }

    private int calculateFlightCost(WorldMap.Countries destinationCountry) {
        int cost;
        int currentZone = countries.get(player.getCurrentCountry()).getZone();

        cost = destinationCountry.getCost() + ( Math.abs(currentZone - destinationCountry.getZone()) * 100 ) ;

        return cost;
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
            System.out.println("Name:" + weapon.getName() + " | Damage: " + weapon.getDamage());
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
}
