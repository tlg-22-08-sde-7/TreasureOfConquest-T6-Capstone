package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SplashScreen {
    String splashScreenArt = loadSplashScreen();

    private static String loadSplashScreen(){

        String splashScreenArt = null;
        try {
            splashScreenArt = Files.readString(Path.of("assets/assciiart/splashscreen.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return splashScreenArt ;
    }

    public String getSplashScreenArt() {
        return splashScreenArt;
    }
}