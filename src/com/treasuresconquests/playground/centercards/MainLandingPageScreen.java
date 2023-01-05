package com.treasuresconquests.playground.centercards;

import com.treasuresconquests.app.GUIController;
import com.treasuresconquests.guiclient.ScreenLauncher;
import com.treasuresconquests.guiengine.Handlers;
import com.treasuresconquests.guiengine.callbacks.Navigable;
import com.treasuresconquests.playground.BottomRightPanel;
import com.treasuresconquests.playground.CenterPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainLandingPageScreen extends JPanel implements Navigable {

    private JLabel lblAccountBalance, lblCurrentCountry;
    private JButton btnTreasures, btnXP;
    private JButton btnFrance, btnJapan, btnMexico, btnNigeria;
    private JLabel question;

    Handlers.QuitHandler handlerQuit = new Handlers.QuitHandler();
    Handlers.HelpHandler handlerHelp = new Handlers.HelpHandler();
    Handlers.ExitCurrentGameHandler handlerExitCurrentGame = new Handlers.ExitCurrentGameHandler();
    Handlers.JapanVisitHandler japanVisitHandler;
    private GUIController guiController;

    public MainLandingPageScreen(GUIController guiController) {
        setLayout(null);    // set to 'null' because we need full control of the Swing layout.
        this.guiController = guiController;     // this Screen now has data, and can access methods.

        japanVisitHandler = new Handlers.JapanVisitHandler(guiController);
        lblCurrentCountry = new JLabel("Current Country: " + guiController.getPlayer().getCurrentCountry());
        lblCurrentCountry.setBounds(25, 45, 200, 50);
        lblCurrentCountry.setForeground(Color.white);

        lblAccountBalance = new JLabel("Money $" + guiController.getPlayer().getAmountOfCash());
        lblAccountBalance.setBounds(350, 45, 150, 50);
        lblAccountBalance.setForeground(Color.white);

        btnTreasures = new JButton("Treasures");
        btnTreasures.setBounds(525, 45, 150, 50);

        btnXP = new JButton("XP");
        btnXP.setBounds(700, 45, 150, 50);

        question = new JLabel("Where would you like to visit ?");
        question.setBounds(300, 550, 400, 50);
        question.setForeground(Color.white);

        btnFrance = new JButton("France");
        btnFrance.setBounds(60, 625, 125, 50);

        btnJapan = new JButton("Japan");
        btnJapan.setBounds(460, 625, 125, 50);
        btnJapan.addActionListener(japanVisitHandler);

        btnMexico = new JButton("Mexico");
        btnMexico.setBounds(660, 625, 125, 50);

        btnNigeria = new JButton("Nigeria");
        btnNigeria.setBounds(260, 625, 125, 50);


add(lblAccountBalance); add(btnTreasures); add(btnXP); add(question);
add(btnFrance); add(btnJapan); add(btnMexico); add(btnNigeria);

add(lblCurrentCountry);



    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Image image = null;
        try {
            image = ImageIO.read(new File("resources/assets/images/statusOfLiberty_3.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (image != null) {
            g.drawImage(image, 0, 0, null);
        }
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("My test");
        GUIController guiController = new GUIController();
        MainLandingPageScreen mlps = new MainLandingPageScreen(guiController);
        jFrame.setSize(900, 700);
        jFrame.getContentPane().add(mlps);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

    public void init(){
        BottomRightPanel.showInformationPanel("Hi! Welcome to Single Airport of UNITED STATES! \n" +
                "Where would you like to visit?\n" +
                "Country: Mexico Price of ticket: $2,500\n" +
                "Country: Japan Price of ticket: $1,000\n" +
                "Country: Nigeria Price of ticket: $2,700\n" +
                "Country: France Price of ticket: $2,850");
//        guiController.getPlayer().setCurrentAttraction("JFK Airport");
//        guiController.getPlayer().setCurrentFeature("Airport");
        ScreenLauncher.updateTopRightPanel();
    }

    public void setController(GUIController controller) {
        this.guiController = controller;

        japanVisitHandler = new Handlers.JapanVisitHandler(controller); // reinitializing Japan handler
    }

    @Override
    public void navigateBack() {
        CenterPanel.showStartScreen();
    }

    @Override
    public void printSelf() {
        System.out.println("Main landing screen");
    }
}