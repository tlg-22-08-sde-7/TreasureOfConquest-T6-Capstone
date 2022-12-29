package com.treasuresconquests.guiengine.screens;

import com.treasuresconquests.guiengine.Handlers;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {
    JButton btnStart, btnLoad, btnInstructions, btnQuit
            , btnHelp, btnExitCurrentGame;
    Handlers.QuitHandler handlerQuit = new Handlers.QuitHandler();
    JTextArea mainTextArea;
    JPanel mainTextPanel, persistPanelButtons;
    Font textFont = new Font("Times New Roman", Font.PLAIN, 24);
    Font btnFont = new Font("Times New Roman", Font.PLAIN, 18);
    Handlers.HelpHandler handlerHelp = new Handlers.HelpHandler();
    Handlers.ExitCurrentGameHandler handlerExitCurrentGame = new Handlers.ExitCurrentGameHandler();

    public GameScreen() {

        // main text panel
        mainTextPanel = new JPanel();
        setSize(900, 700);
        mainTextPanel.setBounds(100, 100, 600, 250);
        mainTextPanel.setBackground(Color.blue);

        mainTextArea = new JTextArea("This is the main text area for your reading pleasure.  This game is going to be so cool!");
        mainTextArea.setBounds(100, 100, 600, 250);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(textFont);
        mainTextArea.setLineWrap(true);

        mainTextPanel.add(mainTextArea);

        // persistent buttons panel
        persistPanelButtons = new JPanel();
        persistPanelButtons.setBounds(150, 600, 600, 100);
        persistPanelButtons.setBackground(Color.blue);
        persistPanelButtons.setLayout(new FlowLayout());

        // buttons
        btnHelp = new JButton("Help");
        btnHelp.setBackground(Color.darkGray);
        btnHelp.setForeground(Color.white);
        btnHelp.setFont(btnFont);
        btnHelp.addActionListener(handlerHelp);

        btnExitCurrentGame = new JButton("Exit Current Game");
        btnExitCurrentGame.setBackground(Color.black);
        btnExitCurrentGame.setForeground(Color.white);
        btnExitCurrentGame.setFont(btnFont);
        btnExitCurrentGame.addActionListener(handlerExitCurrentGame);

        btnQuit = new JButton("Quit ToC");
        btnQuit.setBackground(Color.black);
        btnQuit.setForeground(Color.white);
        btnQuit.setFont(btnFont);
        btnQuit.addActionListener(handlerQuit);

        // fill button panel
        persistPanelButtons.add(btnHelp);
        persistPanelButtons.add(btnExitCurrentGame);
        persistPanelButtons.add(btnQuit);

        add(mainTextPanel);
        add(persistPanelButtons);
    }

}