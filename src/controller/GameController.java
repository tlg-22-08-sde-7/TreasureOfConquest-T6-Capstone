package controller;


import com.apps.util.Console;
import com.apps.util.Prompter;

import java.util.Scanner;
import com.apps.util.Prompter;
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

    public GameController() {
        gameView = new GameView();
    }

    public void run() {
        playerSetup();
    }

    private void playerSetup() {

        Prompter prompter = new Prompter(new Scanner(System.in));
        display("instructions.txt");
        Console.pause(2000L);

        String name = prompter.prompt("Enter player name: ");
        gameView.playerSetup(name);

        String home = prompter.prompt("Enter your hometown: ");
        gameView.playerSetup(home);
    }
}
