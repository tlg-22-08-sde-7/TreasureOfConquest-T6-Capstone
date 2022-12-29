package com.treasuresconquests.guiengine.screens;

import com.treasuresconquests.guiengine.Handlers;

import javax.swing.*;
import java.awt.*;

public class StartScreen extends JPanel {

    // initializing objects of the Event Handlers
    Handlers.StartHandler handlerStart = new Handlers.StartHandler();
    Handlers.LoadHandler handlerLoad = new Handlers.LoadHandler();
    Handlers.InstructionsHandler handlerInstructions = new Handlers.InstructionsHandler();
    Handlers.QuitHandler handlerQuit = new Handlers.QuitHandler();

    JPanel titlePanelTreasures, titlePanelConquests, titlePanelButtons
            , mainTextPanel, persistPanelButtons;

    JLabel titleLabelTreasures, titleLabelConquests;
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 76);

    JButton btnStart, btnLoad, btnInstructions, btnQuit
            , btnHelp, btnExitCurrentGame;
    Font btnFont = new Font("Times New Roman", Font.PLAIN, 18);

    JTextArea mainTextArea;
    Font textFont = new Font("Times New Roman", Font.PLAIN, 24);

    public StartScreen(){
        setLayout(null);
        // setSize(900, 700);

        // treasures panel
        titlePanelTreasures = new JPanel();

        titlePanelTreasures.setBackground(Color.black);
        titlePanelTreasures.setLayout(new FlowLayout());

        titleLabelTreasures = new JLabel("TREASURES of...");
        titleLabelTreasures.setForeground(Color.yellow);
        titleLabelTreasures.setFont(titleFont);

        titlePanelTreasures.add(titleLabelTreasures);
        titlePanelTreasures.setBounds(100, 100, 700, 100);
        // conquests panel
        titlePanelConquests = new JPanel();
        titlePanelConquests.setBounds(100, 250, 700, 100);
        titlePanelConquests.setBackground(Color.black);
        titlePanelConquests.setLayout(new FlowLayout());

        titleLabelConquests = new JLabel("CONQUESTS");
        titleLabelConquests.setForeground(Color.red);
        titleLabelConquests.setFont(titleFont);

        titlePanelConquests.add(titleLabelConquests);

        //buttons panel
        titlePanelButtons = new JPanel();
        titlePanelButtons.setBounds(150, 450, 600, 100);
        titlePanelButtons.setBackground(Color.black);
        titlePanelButtons.setLayout(new FlowLayout());

        // buttons
        btnStart = new JButton("START");
        btnStart.setBackground(Color.black);
        btnStart.setForeground(Color.white);
        btnStart.setFont(btnFont);
        btnStart.addActionListener(handlerStart);

        btnLoad = new JButton("Load Game");
        btnLoad.setBackground(Color.darkGray);
        btnLoad.setForeground(Color.white);
        btnLoad.setFont(btnFont);
        btnLoad.addActionListener(handlerLoad);

        btnInstructions = new JButton("Read Instructions");
        btnInstructions.setBackground(Color.darkGray);
        btnInstructions.setForeground(Color.white);
        btnInstructions.setFont(btnFont);
        btnInstructions.addActionListener(handlerInstructions);

        btnQuit = new JButton("Quit ToC");
        btnQuit.setBackground(Color.black);
        btnQuit.setForeground(Color.white);
        btnQuit.setFont(btnFont);
        btnQuit.addActionListener(handlerQuit);

        // fill button panel
        titlePanelButtons.add(btnStart);
        titlePanelButtons.add(btnLoad);
        titlePanelButtons.add(btnInstructions);
        titlePanelButtons.add(btnQuit);

//        pane.add(b1);
//        pane.add(b2);
//        pane.add(b3);
        add(titlePanelTreasures);
        add(titlePanelConquests);
        add(titlePanelButtons);
        setBackground(Color.black);
    }

}