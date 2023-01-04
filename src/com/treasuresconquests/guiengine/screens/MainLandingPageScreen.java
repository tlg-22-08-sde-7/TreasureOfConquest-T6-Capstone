package com.treasuresconquests.guiengine.screens;

import com.treasuresconquests.app.GUIController;
import com.treasuresconquests.guiengine.Handlers;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainLandingPageScreen extends JPanel {

    private JLabel lblAccountBalance, lblCurrentCountry;
    private JButton btnTreasures, btnXP;
    private JButton btnFrance, btnJapan, btnMexico, btnNigeria;
    private JButton btnBack, btnExitCurrentGame,  btnQuit, btnHelp;
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
        question.setBounds(300, 150, 400, 50);
        question.setForeground(Color.white);

        btnFrance = new JButton("France");
        btnFrance.setBounds(150, 250, 175, 50);
        btnJapan = new JButton("Japan");
        btnJapan.setBounds(425, 250, 175, 50);
        btnJapan.addActionListener(japanVisitHandler);

        btnMexico = new JButton("Mexico");
        btnMexico.setBounds(150, 375, 175, 50);
        btnNigeria = new JButton("Nigeria");
        btnNigeria.setBounds(425, 375, 175, 50);

        btnBack = new JButton("Back");
        btnBack.setBounds(350, 550, 150, 50);

        btnExitCurrentGame = new JButton("Exit current game");
        btnExitCurrentGame.setBounds(525, 550, 150, 50);
        btnExitCurrentGame.addActionListener(handlerExitCurrentGame);

        btnQuit = new JButton("Quit");
        btnQuit.setBounds(700, 550, 150, 50);
        btnQuit.addActionListener(handlerQuit);

        btnHelp = new JButton("Help");
        btnHelp.setBounds(50, 550, 150, 50);
        btnHelp.addActionListener(handlerHelp);

add(lblAccountBalance); add(btnTreasures); add(btnXP); add(question);
add(btnFrance); add(btnJapan); add(btnMexico); add(btnNigeria);
//add(btnBack);
add(btnExitCurrentGame); add(btnQuit); add(btnHelp);
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

    public void setController(GUIController controller) {
        this.guiController = controller;

        japanVisitHandler = new Handlers.JapanVisitHandler(controller); // reinitializing Japan handler
    }
}