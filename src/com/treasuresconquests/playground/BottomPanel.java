package com.treasuresconquests.playground;

import com.treasuresconquests.app.GUIController;
import com.treasuresconquests.guiengine.Handlers;
import com.treasuresconquests.guiengine.callbacks.Navigable;
import com.treasuresconquests.guiengine.callbacks.NavigbleSubscriber;
import com.treasuresconquests.playground.centercards.HelpScreen;
import com.treasuresconquests.playground.centercards.JapanRestaurantScreen;

import javax.swing.*;
import java.awt.*;

public class BottomPanel extends JPanel implements NavigbleSubscriber {
    private JButton btnBack, btnExitCurrentGame,
            btnQuit, btnHelp;

    Handlers.QuitHandler handlerQuit = new Handlers.QuitHandler();
    Handlers.HelpHandler handlerHelp = new Handlers.HelpHandler();
    Handlers.ExitCurrentGameHandler handlerExitCurrentGame = new Handlers.ExitCurrentGameHandler();
    Handlers.BackHandler backHandler = new Handlers.BackHandler();

    public BottomPanel(){

        setLayout(null);
        btnBack = new JButton("Return to Previous");
        btnBack.setBounds(250, 40, 150, 50);
        btnBack.addActionListener(backHandler);

        btnExitCurrentGame = new JButton("Exit current game");
        btnExitCurrentGame.setBounds(450, 40, 150, 50);
        btnExitCurrentGame.addActionListener(handlerExitCurrentGame);

        btnQuit = new JButton("Quit");
        btnQuit.setBounds(650, 40, 150, 50);
        btnQuit.addActionListener(handlerQuit);

        btnHelp = new JButton("Help");
        btnHelp.setBounds(50, 40, 150, 50);
        btnHelp.addActionListener(handlerHelp);

        add(btnHelp);
        add(btnBack);
        add(btnExitCurrentGame);
        add(btnQuit);

        CenterPanel.subscribe(this);
    }

    @Override
    public void currentPage(Navigable screen) {
        if(screen instanceof HelpScreen) {
            return;
        }
        backHandler.setCurrentPage(screen);
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Japan Landing");
        BottomPanel jlp = new BottomPanel();
        jFrame.setSize(900, 700);
        jFrame.getContentPane().add(jlp);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}