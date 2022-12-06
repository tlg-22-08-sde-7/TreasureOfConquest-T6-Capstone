package controller;


import com.apps.util.Console;
import com.apps.util.Prompter;

import java.util.Scanner;
import com.apps.util.Prompter;
import com.apps.util.SplashApp;
import model.Player;
import model.SplashScreen;
import view.GameView;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static jdk.internal.org.jline.keymap.KeyMap.display;


public class GameController {
    private GameView gameView;
    SplashScreen splashScreen = new SplashScreen();
    Player player= new Player();
    Prompter prompter = new Prompter(new Scanner(System.in));

    public GameController() {
        gameView = new GameView();

    }

    public void welcomeScreen(){
        gameView.showSplashScreen();
        gameView.showInstructions();
    }

    public void run() {
        String newGamePrompt = prompter.prompt("Would you like to start a new game?");
        if(newGamePrompt.equals("yes")){
            playerSetup();
        }
        else if(newGamePrompt.equals("no")){
            System.out.println("Welcome back " + player.getPlayerName());
        }
        else {
            System.out.println("Please enter yes or no!");
        }

    }



    private void playerSetup() {

        String name = prompter.prompt("Enter player name: ");
        String home = prompter.prompt("Enter your hometown: ");
        player.playerSetup(name,home);
    }


}
