package view;

import com.apps.util.Prompter;
import model.SplashScreen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static controller.GameController.ANSI_CYAN;
import static controller.GameController.ANSI_RESET;

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
