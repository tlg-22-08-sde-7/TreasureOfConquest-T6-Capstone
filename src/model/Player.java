package model;

import com.apps.util.Prompter;

import java.util.Scanner;

public class Player {
    //Variables
    private String playerName;
    private String playerTown;
    private String playerCurrentLocation;
    private int playerHealth = 100;

    Prompter playerInput = new Prompter(new Scanner(System.in));

    //Methods
    public void playerSetup() {
        String name = playerInput.prompt("Enter player name: ");
        String home = playerInput.prompt("Enter your hometown: ");
        setPlayerTown(home);
        setPlayerName(name);
    }

    //Getter and Setter
    public String getPlayerCurrentLocation() {
        return playerCurrentLocation;
    }

    public void setPlayerCurrentLocation(String playerCurrentLocation) {
        this.playerCurrentLocation = playerCurrentLocation;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerTown() {
        return playerTown;
    }

    public void setPlayerTown(String playerTown) {
        this.playerTown = playerTown;
    }

    public int getPlayerHealth() {
        return playerHealth;
    }

    public void setPlayerHealth(int playerHealth) {
        this.playerHealth = getPlayerHealth() + playerHealth;
    }
}
