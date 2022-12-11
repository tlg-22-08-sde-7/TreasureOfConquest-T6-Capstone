package controller;

import com.apps.util.Console;
import com.apps.util.Prompter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameController game = new GameController();

        game.welcomeScreen();

        Console.pause(2000L);
        Console.clear();

        game.run();
    }

}