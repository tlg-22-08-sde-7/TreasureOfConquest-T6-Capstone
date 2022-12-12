package model;

import com.apps.util.Prompter;

import java.util.Scanner;

public class Player {
    //Variables
    private String name;
    private String hometown;
    private String currentCountry;
    private String currentAttraction;
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = getHealth() + health;
    }
}
