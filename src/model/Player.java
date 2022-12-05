package model;

import com.apps.util.Prompter;

import java.util.Scanner;

public class Player {
    private String playerName;
    private String playerTown;

    public void playerSetup(String name, String home) {
        setPlayerName(name);
        setPlayerTown(home);

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


}
