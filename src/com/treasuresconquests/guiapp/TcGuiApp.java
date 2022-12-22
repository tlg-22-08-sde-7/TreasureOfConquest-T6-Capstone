package com.treasuresconquests.guiapp;

import com.treasuresconquests.guiengine.Screen;

public class TcGuiApp {

    public void start() {
        runStartScreen();
        //runGameScreen();
    }

    private void runStartScreen() {
        Screen startScreen = new Screen();
        startScreen.launchTitleScreen();
    }

    private void runGameScreen() {
        Screen gameScreen = new Screen();
        gameScreen.launchGameScreen();
    }

    private void runExitScreen() {
        //ExitScreen exitScreen = new ExitScreen();
    }

}