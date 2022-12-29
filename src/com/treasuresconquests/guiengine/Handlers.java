package com.treasuresconquests.guiengine;

import com.treasuresconquests.guiclient.ScreenLauncher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Handlers {


    // Static method that can be called from outside
    public static class StartHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ScreenLauncher.showGameScreen();
        }
    }

    public static class LoadHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public static class InstructionsHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ScreenLauncher.showHelpScreen();
        }
    }

    public static class QuitHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            System.exit(0);
        }
    }

    public static class HelpHandler implements ActionListener {

        // prints help with commands / option for instructions(?)
        @Override
        public void actionPerformed(ActionEvent e) {
            ScreenLauncher.showHelpScreen();
        }
    }

    public static class ExitCurrentGameHandler implements ActionListener {

        // exits to the title/start screen
        @Override
        public void actionPerformed(ActionEvent e) {
            //launchTitleScreen();
            ScreenLauncher.showStartScreen();
        }
    }

}