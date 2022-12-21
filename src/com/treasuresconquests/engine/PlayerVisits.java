package com.treasuresconquests.engine;

import com.apps.util.Prompter;
import com.treasuresconquests.app.TreasuresConApp;

import java.util.*;

public class PlayerVisits {

    // create instance variables
    private final Map<String, WorldMap.Countries> countries;
    private final Map<String, NPC> npc;
    private Player player;
    private Prompter prompter;
    private WorldMap worldMap;
    private TextParser textParser;
    private PlayerDialog playerDialog;

    String userInput;
    String parsedUserInput = null;

    // ctor
    public PlayerVisits(Map<String, WorldMap.Countries> countries,
                        Map<String, NPC> npc, Player player,
                        Prompter prompter, WorldMap worldMap,
                        TextParser textParser, PlayerDialog playerDialog) {

        this.countries = countries;
        this.npc = npc;
        this.player = player;
        this.prompter = prompter;
        this.worldMap = worldMap;
        this.textParser = textParser;
        this.playerDialog = playerDialog;
    }

    public void playerVisitsAirport() {
        List<String> availableFlights = List.of(countries.keySet().toArray(new String[0]));
        List<String> acceptableCommands = npc.get("airportAgent").getCommands();
        Session.setCurrentNPC("airportAgent");
        int flightCost = 0;

        boolean instructionsPrinted = false;

        while (parsedUserInput == null) {

            // will prevent instructions from being printed twice (and in "country")
            if(!instructionsPrinted) {
                // Print available commands
                System.out.println(TreasuresConApp.ANSI_CYAN + "~~~~~~ LIST OF ACCEPTABLE COMMANDS ~~~~~~" + TreasuresConApp.ANSI_RESET);
                for (int i = 0; i < npc.get("airportAgent").getCommands().size(); i++) {
                    System.out.print(npc.get("airportAgent").getCommands().get(i));
                    if (i < npc.get("airportAgent").getCommands().size() - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println();
                System.out.println(TreasuresConApp.ANSI_CYAN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + TreasuresConApp.ANSI_RESET);
                System.out.println();
            }
            // Was cut and now placed in line 60-62
//            System.out.println();
//            System.out.println(TreasuresConApp.ANSI_CYAN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + TreasuresConApp.ANSI_RESET);
//            System.out.println();

            System.out.println(TreasuresConApp.ANSI_PURPLE + "Hi! Welcome to Single Airport of " + player.getCurrentCountry().toUpperCase() +
                    "! Where would you like to visit?" + TreasuresConApp.ANSI_RESET);

            System.out.println();

            // Print list of available countries
            for (WorldMap.Countries country : worldMap.getCountries()) {
                if (!country.getName().equalsIgnoreCase(player.getCurrentCountry())) {
                    System.out.print("Country: " + country.getName());
                    System.out.println(TreasuresConApp.ANSI_GREEN + " Price of ticket: " + calculateFlightCost(country) + TreasuresConApp.ANSI_RESET);
                    System.out.println();
                }
            }
            userInput = prompter.prompt("Where would you like to go? (Enter 'help' for instructions " +
                    "or 'quit' to end game)");
            parsedUserInput = textParser.parse(userInput, acceptableCommands, availableFlights).toLowerCase();

            // Check for error or call for help
            if(parsedUserInput.toLowerCase().contains("instructions")) {
                parsedUserInput = null;
                instructionsPrinted = true;
                continue;
            }
            if (parsedUserInput.toLowerCase().contains("error")) {
                System.out.println(parsedUserInput);
                parsedUserInput = null;
                continue;
            }

            // Attempt to purchase flight
            flightCost = calculateFlightCost(countries.get(parsedUserInput));

            if (player.getAmountOfCash() < flightCost) {
                System.out.println(TreasuresConApp.ANSI_RED + "Sorry, you do not have enough money to purchase this flight" + TreasuresConApp.ANSI_RESET);
                System.out.println(TreasuresConApp.ANSI_GREEN + "Your current balance is " + player.getAmountOfCash() + TreasuresConApp.ANSI_RESET);
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

    public void playerVisitsRestaurant() {
        Map<String, WorldMap.Countries.Restaurant> restaurantsMap = new HashMap<>();
        Map<String, WorldMap.Countries.Restaurant.Items> dishesMap = new HashMap<>();
        List<String> restaurants = new ArrayList<>();
        List<String> dishes = new ArrayList<>();
        String restaurantChoice = null;
        String dishChoice = null;
        Session.setCurrentNPC("waiter");

        // Print available commands
        System.out.println(TreasuresConApp.ANSI_CYAN + "~~~~~~ LIST OF ACCEPTABLE COMMANDS ~~~~~~" + TreasuresConApp.ANSI_RESET);
        for (int i = 0; i < npc.get("waiter").getCommands().size(); i++) {
            System.out.print(npc.get("waiter").getCommands().get(i));
            if (i < npc.get("waiter").getCommands().size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
        System.out.println(TreasuresConApp.ANSI_CYAN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + TreasuresConApp.ANSI_RESET);
        System.out.println();

        // Choose Restaurant
        while (null == restaurantChoice) {
            System.out.println(TreasuresConApp.ANSI_PURPLE + "Here are a list of restaurants. Which would you like to visit?" + TreasuresConApp.ANSI_RESET);
            for (WorldMap.Countries.Restaurant restaurant : countries.get(player.getCurrentCountry()).getRestaurants()) {
                restaurantsMap.put(restaurant.getName().toLowerCase(), restaurant);
                restaurants.add(restaurant.getName());
                System.out.println(restaurant.getName());
            }
            System.out.println();

            userInput = prompter.prompt("Which restaurant would you like to visit? (Enter 'help' for instructions)" +
                    " or 'quit' to end game");
            parsedUserInput = textParser.parse(userInput, restaurants).toLowerCase();

            if (!parsedUserInput.contains("error") && !parsedUserInput.toLowerCase().contains("instructions")) {
                restaurantChoice = parsedUserInput;
            }
        }

        // Choose Dish
        while (null == dishChoice) {
            String npcResponse = playerDialog.getRandomNPCResponse(npc.get("waiter").getResponses());
            System.out.println(npcResponse);
            System.out.println();

            System.out.println(TreasuresConApp.ANSI_CYAN + "~~~~~~~ HERE IS THE MENU ~~~~~~~" + TreasuresConApp.ANSI_RESET);
            for (WorldMap.Countries.Restaurant.Items dish : restaurantsMap.get(restaurantChoice).getItems()) {
                System.out.println("Item: " + dish.getName() + " | " + "Added health: " + dish.getValue() +
                        " | " + "Cost: " + dish.getCost());
                dishes.add(dish.getName());
                dishesMap.put(dish.getName().toLowerCase(), dish);
            }
            System.out.println(TreasuresConApp.ANSI_CYAN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + TreasuresConApp.ANSI_RESET);

            System.out.println();
            userInput = prompter.prompt("Which dish would you like to order? (Enter 'help' for instructions" +
                    " or 'quit' to end game)");
            parsedUserInput = textParser.parse(userInput, npc.get("waiter").getCommands(), dishes).toLowerCase();

            if (parsedUserInput.contains("error") && !parsedUserInput.toLowerCase().contains("instructions")) {
                System.out.println(parsedUserInput);
            }
            else if (!parsedUserInput.contains("error") && !parsedUserInput.toLowerCase().contains("instructions")) {
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

    public void playerVisitsWeaponStore() {
        String npcResponse = playerDialog.getRandomNPCResponse(npc.get("weaponSalesRep").getResponses());
        Map<String, WorldMap.Countries.WeaponStore> weaponStoreMap = new HashMap<>();
        Map<String, WorldMap.Countries.WeaponStore.Weapons> weaponsMap = new HashMap<>();
        String weaponStoreChoice = null;
        String weaponChoice = null;
        Session.setCurrentNPC("weaponSalesRep");

        // Print available commands
        System.out.println(TreasuresConApp.ANSI_CYAN + "~~~~~~ LIST OF ACCEPTABLE COMMANDS ~~~~~~" + TreasuresConApp.ANSI_RESET);
        for (int i = 0; i < npc.get("weaponSalesRep").getCommands().size(); i++) {
            System.out.print(npc.get("weaponSalesRep").getCommands().get(i));
            if (i < npc.get("weaponSalesRep").getCommands().size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
        System.out.println(TreasuresConApp.ANSI_CYAN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + TreasuresConApp.ANSI_RESET);
        System.out.println();

        // Choose weapon store
        while (null == weaponStoreChoice) {
            System.out.println(TreasuresConApp.ANSI_YELLOW + "Here is a list of available weapon stores:" + TreasuresConApp.ANSI_RESET);
            for (WorldMap.Countries.WeaponStore weaponStore : countries.get(player.getCurrentCountry()).getWeaponStores()) {
                System.out.println(weaponStore.getName());
                weaponStoreMap.put(weaponStore.getName(), weaponStore);
            }
            System.out.println();

            userInput = prompter.prompt("Which weapons store would you like to visit? (Enter 'help' for instructions)" +
                    " or 'quit' to end game");
            parsedUserInput = textParser.parse(userInput, List.of(weaponStoreMap.keySet().toArray(new String[0])));

            if (!parsedUserInput.toLowerCase().contains("error") && !parsedUserInput.toLowerCase().contains("instructions")) {
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

            System.out.println(TreasuresConApp.ANSI_YELLOW + "~~~~~~ WEAPON'S MENU ~~~~~~" + TreasuresConApp.ANSI_RESET);
            for (WorldMap.Countries.WeaponStore.Weapons weapon : weaponStoreMap.get(parsedUserInput).getWeapons()) {
                System.out.println("This is the " + weapon.getName() + "! It costs " + weapon.getCost() +
                        " and does a total damage of " + weapon.getDamage());
                weaponsMap.put(weapon.getName(), weapon);
            }
            System.out.println();

            userInput = prompter.prompt("Which weapon would you like to purchase? (Enter 'help' for instructions)" +
                    " or 'quit' to end game");
            parsedUserInput = textParser.parse(userInput, npc.get("weaponSalesRep").getCommands(),
                    List.of(weaponsMap.keySet().toArray(new String[0])));

            if (!parsedUserInput.toLowerCase().contains("error") && !parsedUserInput.toLowerCase().contains("instructions")) {
                weaponChoice = parsedUserInput;
            } else if (player.getAmountOfCash() < weaponsMap.get(parsedUserInput).getCost()) {
                System.out.println(TreasuresConApp.ANSI_RED + "Sorry! You do not have enough money to purchase the " + parsedUserInput + TreasuresConApp.ANSI_RESET);
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

    public void playerVisitsAttraction() {
        String attractionChoice = null;
        Map<String, WorldMap.Countries.Attraction> attractionMap = new HashMap<>();
        WorldMap.Countries.Attraction.Treasures treasure;
        int cashPrize = 500;
        Session.setCurrentNPC("tourGuide");

        String userInput = "";
        String parsedUserInput = "";

        // Print available commands
        System.out.println(TreasuresConApp.ANSI_CYAN + "~~~~~~ LIST OF ACCEPTABLE COMMANDS ~~~~~~" + TreasuresConApp.ANSI_RESET);
        for (int i = 0; i < npc.get("tourGuide").getCommands().size(); i++) {
            System.out.print(npc.get("tourGuide").getCommands().get(i));
            if (i < npc.get("tourGuide").getCommands().size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
        System.out.println(TreasuresConApp.ANSI_CYAN + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + TreasuresConApp.ANSI_RESET);
        System.out.println();

        // Choose Attraction
        while (attractionChoice == null) {
            System.out.println(TreasuresConApp.ANSI_PURPLE + "~~~~~~~ ATTRACTIONS ~~~~~~~" + TreasuresConApp.ANSI_RESET);
            for (WorldMap.Countries.Attraction attraction : countries.get(player.getCurrentCountry()).getAttractions()) {
                System.out.println(attraction.getName());
                attractionMap.put(attraction.getName().toLowerCase(), attraction);
            }
            System.out.println(TreasuresConApp.ANSI_PURPLE + "~~~~~~~~~~~~~~~~~~~~~~~~~~~" + TreasuresConApp.ANSI_RESET);
            System.out.println();

            userInput = prompter.prompt("Which attraction would you like to visit? (Enter 'help' for instructions" +
                    " or 'quit' to end game)");
            parsedUserInput = textParser.parse(userInput, npc.get("tourGuide").getCommands(),
                    List.of(attractionMap.keySet().toArray(new String[0])));

            System.out.println();

            if (!parsedUserInput.toLowerCase().contains("error") && !parsedUserInput.toLowerCase().contains("instructions")) {
                attractionChoice = parsedUserInput;
                System.out.println("You have chosen: " + attractionChoice);
            } else {
                System.out.println(parsedUserInput);
            }
        }

        // Solve random riddle
        if (solvedRandomAttractionRiddle(attractionMap.get(attractionChoice))) {
            // Give player treasure
            int treasuresSize = attractionMap.get(attractionChoice).getTreasures().size();
            treasure = attractionMap.get(attractionChoice).getTreasures().get( (int) (Math.random() * treasuresSize) );

            // Inform player they answered correctly
            System.out.println(TreasuresConApp.ANSI_GREEN + "~~~~~~ CORRECT! ~~~~~~" + TreasuresConApp.ANSI_RESET);
            System.out.println(TreasuresConApp.ANSI_BLUE + "You have been rewarded: " + treasure.getName() + " and $" + cashPrize + TreasuresConApp.ANSI_RESET);

            // Pay out winnings
            player.addTreasure(treasure);
            player.gainMoney(cashPrize);
        } else {
            System.out.println(TreasuresConApp.ANSI_RED + "~~~~~~ Incorrect! ~~~~~~" + TreasuresConApp.ANSI_RESET);
            System.out.println(TreasuresConApp.ANSI_YELLOW + "Sorry, your answer " + attractionChoice + " is incorrect." + TreasuresConApp.ANSI_RESET);
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();
    }

    // Only 4 lines of code, dones't make sense to refactor.
    private int calculateFlightCost(WorldMap.Countries destinationCountry) {
        int cost;
        int currentZone = countries.get(player.getCurrentCountry()).getZone();

        cost = destinationCountry.getCost() + ( Math.abs(currentZone - destinationCountry.getZone()) * 100 ) ;

        return cost;
    }

    private boolean solvedRandomAttractionRiddle(WorldMap.Countries.Attraction attraction) {
        boolean solvedRiddle;
        int randomNumber = (int) (Math.random() * attraction.getRiddles().size());
        WorldMap.Countries.Attraction.Riddles riddle = attraction.getRiddles().get(randomNumber);

        System.out.println(TreasuresConApp.ANSI_BLUE + "Question:" + TreasuresConApp.ANSI_RESET);
        System.out.println(riddle.getText());

        System.out.println();

        System.out.println(TreasuresConApp.ANSI_CYAN + "Here are your options:" + TreasuresConApp.ANSI_RESET);
        for (String option : riddle.getOptions()) {
            System.out.println(option);
        }

        System.out.println();

        userInput = prompter.prompt("What is the answer to the question?");
        parsedUserInput = textParser.parse(userInput, riddle.getOptions());

        System.out.println();

        solvedRiddle = riddle.getAnswer().equalsIgnoreCase(parsedUserInput);

        System.out.println("The correct answer is: " + riddle.getAnswer());

        return solvedRiddle;
    }

}