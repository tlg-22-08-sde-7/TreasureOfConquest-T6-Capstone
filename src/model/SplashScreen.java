package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SplashScreen {
    String splashScreenArt = loadSplashScreen();
    String gameInst = loadGameInstructions();

    private static String loadSplashScreen(){

        String splashScreenArt = null;
        try {
            splashScreenArt = Files.readString(Path.of("assets/assciiart/splashscreen.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return splashScreenArt ;
    }

    private static String loadGameInstructions(){

        String gameInst = null;
        try {
            gameInst = Files.readString(Path.of("assets/instructions.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gameInst ;
    }

    public String getGameInst() {
        return gameInst;
    }


    public String getSplashScreenArt() {
        return splashScreenArt;
    }
}