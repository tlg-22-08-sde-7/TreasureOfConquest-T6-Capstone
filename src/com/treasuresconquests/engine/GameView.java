package com.treasuresconquests.engine;

//import com.apps.util.Prompter;

import static com.treasuresconquests.app.TreasuresConApp.ANSI_CYAN;
import static com.treasuresconquests.app.TreasuresConApp.ANSI_RESET;

public class GameView {

    private SplashScreen splash = null;

    public void showInstructions(){
        splash = new SplashScreen();
        String result = splash.getGameInst();
        System.out.println(result);
    }

    public void showSplashScreen(){
        splash = new SplashScreen();
        String result = splash.getSplashScreenArt();
        System.out.println(ANSI_CYAN + result + ANSI_RESET);
    }
}
