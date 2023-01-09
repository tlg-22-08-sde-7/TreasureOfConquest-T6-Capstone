package com.treasuresconquests.engine;

import com.apps.util.Prompter;
import com.treasuresconquests.app.TreasuresConApp;

import java.util.*;

public class PlayerDialog {

    private final Map<String, NPC> npc;
    private TextParser textParser;
    private Player player;
    private Prompter prompter;

    // ctor
    public PlayerDialog (Map<String, NPC> npc,
                         TextParser textParser, Player player,
                         Prompter prompter) {

        this.npc = npc;
        this.textParser = textParser;
        this.player = player;
        this.prompter = prompter;
    }

    // Helper Methods
    public void playerInteractsWithRandomNPC() {
        NPC currNPC = (( (int) (Math.random() * 100) ) % 2 == 0) ? npc.get("allies") : npc.get("villains");
        int responsesSize = currNPC.getResponses().size();

        System.out.println(currNPC.getResponses().get((int) (Math.random() * responsesSize)));

        if (currNPC.getNpcType().equalsIgnoreCase("allies")) {
            playerInteractsWithAlly(currNPC);
        }
        else {
            playerBattlesEnemy(currNPC);
        }
    }

    public void playerInteractsWithAlly(NPC ally) {
        int valuesSize = ally.getValues().size();
        int cashGift = ally.getValues().get((int) (Math.random() * valuesSize));

        System.out.println(TreasuresConApp.ANSI_GREEN + "Hey friend here's $" + cashGift + TreasuresConApp.ANSI_RESET);
        player.gainMoney(cashGift);
    }

    public void playerBattlesEnemy(NPC enemyNPC) {
        int maxNumber = 15;
        int minNumber = 0;
        int randomNumber = (int) (Math.random() * maxNumber);
        int enemyChoice;
        int enemyChoiceDifference;
        int playerChoice;
        int playerChoiceDifference;
        int cashPrize = 150;

        // Randomly decide how strong enemyNPC is
        int npcHealth =  npc.get("villains").getValues().get((int) (Math.random() * npc.get("villains").getValues().size()));
        int npcStrength = enemyNPC.getValues().get( (int) (Math.random() * enemyNPC.getValues().size()) );

        // Begin Battle
        System.out.println(TreasuresConApp.ANSI_YELLOW + "~~~~~~~~ BEGIN BATTLE ~~~~~~~~" + TreasuresConApp.ANSI_RESET);
        System.out.println(TreasuresConApp.ANSI_RED + "You have run into an enemy who looks to harm you! Use your weapon to " +
                "defeat them!" + TreasuresConApp.ANSI_RESET);

        while (player.getHealth() > 0 && npcHealth > 0) {
            System.out.println("A random number has been chosen. You and the enemy will guess a number between "
                    + minNumber + " and " + maxNumber + "(non-inclusive). \n" +
                    "Whoever guesses closest to the random " + "number takes a turn attacking the other person.");
            System.out.println();

            // Player Chooses Number
            // TODO: Add error catching here for when user enters non-number
            playerChoice = Integer.parseInt(prompter.prompt("Enter a number"));
            playerChoiceDifference = Math.abs(randomNumber - playerChoice);

            // Enemy Chooses Number
            enemyChoice = (int) (Math.random() * maxNumber);
            enemyChoiceDifference = Math.abs(randomNumber - enemyChoice);

            System.out.println(TreasuresConApp.ANSI_RED + "Enemy chose " + enemyChoice + TreasuresConApp.ANSI_RESET);
            System.out.println();
            System.out.println(TreasuresConApp.ANSI_GREEN + "The correct number is " + randomNumber + TreasuresConApp.ANSI_RESET);
            System.out.println();



            if (playerChoiceDifference < enemyChoiceDifference) {
                System.out.println(TreasuresConApp.ANSI_GREEN + "Player wins and gets to attacks enemy!" + TreasuresConApp.ANSI_RESET);
                npcHealth -= playerAttacksEnemy(npcHealth);
            } else if (enemyChoiceDifference < playerChoiceDifference) {
                player.receiveDamage(npcStrength);
                System.out.println(TreasuresConApp.ANSI_RED + "Enemy wins and gets to attacks player!" + TreasuresConApp.ANSI_RESET);
                System.out.println(TreasuresConApp.ANSI_RED + "You have lost " + npcStrength + " health. " +
                        "Your new health is " + player.getHealth() + TreasuresConApp.ANSI_RESET);
                System.out.println();

            } else {
                System.out.println(TreasuresConApp.ANSI_BLUE + "Draw! You both guessed equally!" + TreasuresConApp.ANSI_RESET);
                System.out.println("No damage will be caused anywhere");
            }

            // Battle
            System.out.println(TreasuresConApp.ANSI_YELLOW + "Your current health: " + player.getHealth() + TreasuresConApp.ANSI_RESET);
            System.out.println(TreasuresConApp.ANSI_RED + "The enemy's current health:" + npcHealth + TreasuresConApp.ANSI_RESET);
            System.out.println(TreasuresConApp.ANSI_RED + "The enemy can cause " + npcStrength + " damage." + TreasuresConApp.ANSI_RESET);
            System.out.println();
        }

        // Announce Results
        if (player.getHealth() == 0) {
            System.out.println(TreasuresConApp.ANSI_RED + "~~~~~~~ THE ENEMY DEFEATED YOU ~~~~~~~" + TreasuresConApp.ANSI_RESET);
            TreasuresConApp.setGameOver(true);
        } else {
            System.out.println(TreasuresConApp.ANSI_GREEN + "~~~~~~~ YOU DEFEATED THE ENEMY ~~~~~~~" + TreasuresConApp.ANSI_RESET);
            System.out.println("You earned $" + cashPrize);
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public int playerAttacksEnemy(int npcHealth) {
        Map<String, WorldMap.Countries.WeaponStore.Weapons> weaponsMap = new HashMap<>();
        int damage = 0;

        String userInput;
        String parsedUserInput;

        parsedUserInput = null;

        // Print list of available weapons
        System.out.println(TreasuresConApp.ANSI_YELLOW + "~~~~~~~~ YOUR WEAPON INVENTORY ~~~~~~~~" + TreasuresConApp.ANSI_RESET);
        for (WorldMap.Countries.WeaponStore.Weapons weapon : player.getWeaponInventory()) {
            weaponsMap.put(weapon.getName(), weapon);
            System.out.println("Name: " + weapon.getName() + " | Power: " + weapon.getDamage());
        }
        System.out.println(TreasuresConApp.ANSI_YELLOW + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + TreasuresConApp.ANSI_RESET);
        System.out.println();

        // Player chooses weapon if they have inventory
        while (null == parsedUserInput) {
            if (weaponsMap.keySet().size() != 0) {
                // Print acceptable commands
                System.out.println(TreasuresConApp.ANSI_PURPLE + "~~~~~~~~~ ACCEPTABLE COMMANDS ~~~~~~~~~" + TreasuresConApp.ANSI_RESET);
                for (String command : npc.get("villains").getCommands()) {
                    System.out.println(command);
                }
                System.out.println(TreasuresConApp.ANSI_PURPLE + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + TreasuresConApp.ANSI_RESET);
                System.out.println();

                // Attempt to get user input
                System.out.println(TreasuresConApp.ANSI_RED + "The enemy's remaining health is " + npcHealth + TreasuresConApp.ANSI_RESET);
                userInput = prompter.prompt("Enter command and weapon (Enter 'quit' to quit game - 'help' for instruction)");
                parsedUserInput =
                        textParser.parse(userInput, npc.get("villains").getCommands(), List.of(weaponsMap.keySet().toArray(new String[0])));
                System.out.println();
                // Validate
                if (parsedUserInput.toLowerCase().contains("error") || parsedUserInput.toLowerCase().contains("instructions")) {
                    System.out.println(parsedUserInput);
                    parsedUserInput = null;
                }
            }
        }

        // Remove weapon from inventory
        player.removeWeapon(weaponsMap.get(parsedUserInput));

        damage = weaponsMap.get(parsedUserInput).getDamage();

        return damage;
    }

    public void playerSpeaksWithTourGuide() {
        String userInput;
        String parsedUserInput = null;
        ArrayList<String> options = new ArrayList<>(Arrays.asList("attraction", "restaurant", "weapon store", "airport"));

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

        // Find what type of location user would like to visit
        while (null == parsedUserInput) {
            userInput = prompter.prompt("Hi, I am the tour guide. Would " +
                    "you like to visit an attraction, explore airport, restaurant, or weapon store? \n" +
                    "(Enter 'help' for instructions or 'quit' to end game)");

            parsedUserInput = textParser.parse(userInput, npc.get("tourGuide").getCommands(), options);

            if (parsedUserInput.toLowerCase().contains("error") || parsedUserInput.toLowerCase().contains("instructions")) {
                // TODO: add better error message providing specific instructions to user
                System.out.println(parsedUserInput);
                parsedUserInput = null;
            }
        }

        // Set player attraction
        player.setCurrentAttraction(parsedUserInput);
    }

    public String getRandomNPCResponse(List<String> responses) {
        int randomNum = (int) (Math.random() * responses.size());

        return responses.get(randomNum);
    }

}