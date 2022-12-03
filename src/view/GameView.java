package view;

import com.apps.util.Prompter;
import model.SplashScreen;

public class GameView {
    private Prompter prompter;
    private SplashScreen splash = null;

    public void playerSetup(String name) {

    }

    public void showSplashScreen(){
        splash = new SplashScreen();
        String result = splash.getSplashScreenArt();
        System.out.println(result);
    }
}
