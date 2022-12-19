package com.treasuresconquests.engine;

import com.apps.util.Prompter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    // Variables
    private String name;
    private String homeCountry;
    private String currentCountry;
    private String currentAttraction;
    private final int maxHealth = 100;
    private int health = maxHealth;
    private List<WorldMap.Countries.Attraction.Treasures> treasures = new ArrayList<>();

    Prompter playerInput = new Prompter(new Scanner(System.in));
    private PlayerArmory playerArmory;
    private PlayerWallet playerWallet;
    private PlayerHealthBank playerHealthBank;

    public void Player() {

    }

    //Methods
    public void playerSetup() {
        String name = playerInput.prompt("Enter player name: ");
        setHomeCountry("united states");
        setCurrentCountry("united states");
        setName(name);

        // Player starts the game with a slingshot, knife, and gun
        playerArmory = new PlayerArmory();

        // Initialize Player's money
        playerWallet = new PlayerWallet();

        // Initialize Player's health
        playerHealthBank = new PlayerHealthBank();
    }

    public void eat(WorldMap.Countries.Restaurant.Items dish) {
        // Increase health
        increaseHealth(dish.getValue());
        decreaseCashBalance(dish.getCost());
    }

    // not Setter / Getter.  Consider remove
    // Consider putting in Wallet class.
    public void gainMoney(int money) {
        playerWallet.gainMoney(money);
    }

    // not Setter / Getter.  Consider remove
    // Consider putting in armory class.
    public void addWeapon(WorldMap.Countries.WeaponStore.Weapons weapon) {
        playerArmory.addWeapon(weapon);
    }

    // not Setter / Getter.  Consider remove
    // Consider putting in armory class.
    public void removeWeapon(WorldMap.Countries.WeaponStore.Weapons weapon) {
        playerArmory.removeWeapon(weapon);
    }

    // not Setter / Getter.  Consider remove.
    // Put in health class ?
    public void receiveDamage(int damage) {
        setHealth(getHealth() - damage);
    }

    // not Setter / Getter.  Consider remove
    public void addTreasure(WorldMap.Countries.Attraction.Treasures treasure) {
        setTreasures(treasure);
    }

    // not Setter / Getter.  Consider remove
    public void makePurchase(int amountOfCash) {
        playerWallet.makePurchase(amountOfCash);
    }

    // not Setter / Getter.  Consider remove
    // Helper Methods
    private void increaseHealth(int addedHealth) {
        playerHealthBank.increaseHealth(addedHealth);
    }

    // not Setter / Getter.  Consider remove
    private void decreaseCashBalance(int cost) {
        playerWallet.decreaseCashBalance(cost);
    }

    //Getter and Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHomeCountry() {
        return homeCountry;
    }

    public void setHomeCountry(String homeCountry) {
        this.homeCountry = homeCountry;
    }

    public String getCurrentCountry() {
        return currentCountry;
    }

    public void setCurrentCountry(String currentCountry) {
        this.currentCountry = currentCountry;
    }

    public String getCurrentAttraction() {
        return currentAttraction;
    }

    public void setCurrentAttraction(String currentAttraction) {
        this.currentAttraction = currentAttraction;
    }

    // not Setter / Getter
    public List<WorldMap.Countries.WeaponStore.Weapons> getWeaponInventory() {
        return playerArmory.getWeaponInventory();
    }

    public void addWeaponToInventory(WorldMap.Countries.WeaponStore.Weapons weapon) {
        playerArmory.addWeaponToInventory(weapon);
    }

    public int getHealth() {
        return playerHealthBank.getHealth();
    }

    public void setHealth(int health) {
        playerHealthBank.setHealth(health);
    }

    public int getAmountOfCash() {
        return playerWallet.getAmountOfCash();
    }

    public void setAmountOfCash(int amountOfCash) {
        playerWallet.setAmountOfCash(amountOfCash);
    }

    // not Setter / Getter
    public List<WorldMap.Countries.Attraction.Treasures> getTreasures() {
        return treasures;
    }

    private void setTreasures(WorldMap.Countries.Attraction.Treasures treasure) {
        treasures.add(treasure);
    }
}
