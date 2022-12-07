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
    private SplashScreen splashScreen;
    private Player player;
    private Prompter prompter;
    private static boolean gameOver = false;

    public GameController() {
        gameView = new GameView();
        splashScreen = new SplashScreen();
        player =new Player();

        prompter = new Prompter(new Scanner(System.in));

    }

    public void welcomeScreen(){
        gameView.showSplashScreen();
        gameView.showInstructions();
    }

    public void run() {
        String newGamePrompt = prompter.prompt("Would you like to start a new game?");
        if(newGamePrompt.equals("yes")){
            player.playerSetup();
        }
        else if(newGamePrompt.equals("no")){
            System.out.println("Welcome back " + player.getPlayerName());
        }
        else {
            System.out.println("Please enter yes or no!");
        }

        while (!isGameOver()){
            startQuest();
        }

        if (isGameOver()){
            System.out.println("Game Over");
        }

    }

    public void startQuest(){
        String command;

        System.out.println("You are at the airport in "+ player.getPlayerTown() + ". " +
                "In front of you is the desk with airline agent" );
        command = prompter.prompt("What would you like to do?");

        if(command.equals("quit")){
            endQuest();
        }
    }

    public void endQuest(){
        setGameOver(true);
    }

    public static boolean isGameOver() {
        return gameOver;
    }

    public static void setGameOver(boolean gameOver) {
        GameController.gameOver = gameOver;
    }


}
