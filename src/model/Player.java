package model;

import com.apps.util.Prompter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    //Variables
    private String name;
    private String homeCountry;
    private String currentCountry;
    private String currentAttraction;
    private final List<WorldMap.Countries.WeaponStore.Weapons> weaponInventory = new ArrayList<>();
    private int health = 100;
    private int amountOfCash = 2000;


    Prompter playerInput = new Prompter(new Scanner(System.in));

    //Methods
    public void playerSetup() {
        String name = playerInput.prompt("Enter player name: ");
        setHomeCountry("united states");
        setCurrentCountry("united states");
        setName(name);
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

    public List<WorldMap.Countries.WeaponStore.Weapons> getWeaponInventory() {
        return weaponInventory;
    }

    public void addWeaponToInventory(WorldMap.Countries.WeaponStore.Weapons weapon) {
        weaponInventory.add(weapon);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = getHealth() + health;
    }

    public int getAmountOfCash() {
        return amountOfCash;
    }

    public void makePurchase(int amountOfCash) {
        this.amountOfCash -= amountOfCash;
    }

    public void receiveMoney(int amountOfCash) { this.amountOfCash += amountOfCash; }
}
