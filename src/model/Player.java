package model;

import com.apps.util.Prompter;

import java.util.Scanner;

public class Player {
    //Variables
    private String playerName;
    private String playerTown;
    private static final int playerHealth = 100;

    Prompter playerInput = new Prompter(new Scanner(System.in));

    //Methods
    public void playerSetup() {
        String name = playerInput.prompt("Enter player name: ");
        String home = playerInput.prompt("Enter your hometown: ");
        setPlayerTown(home);
        setPlayerName(name);

    }



    //Getter and Setter


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


}
