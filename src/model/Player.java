package model;

import com.apps.util.Prompter;

import java.util.Scanner;

public class Player {
    //Variables
    private String name;
    private String hometown;
    private String currentLocation;
    private int health = 100;

    Prompter playerInput = new Prompter(new Scanner(System.in));

    //Methods
    public void playerSetup() {
        String name = playerInput.prompt("Enter player name: ");
        String home = playerInput.prompt("Enter your hometown: ");
        setHometown(home);
        setName(name);
    }

    //Getter and Setter
    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = getHealth() + health;
    }
}
