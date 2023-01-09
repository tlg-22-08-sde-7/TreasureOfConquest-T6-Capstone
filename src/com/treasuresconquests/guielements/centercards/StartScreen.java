package com.treasuresconquests.guielements.centercards;

import com.treasuresconquests.guiclient.ScreenLauncher;
import com.treasuresconquests.app.GUIController;
import com.treasuresconquests.guiengine.Handlers;
import com.treasuresconquests.guiengine.callbacks.Navigable;

import javax.swing.*;
import java.awt.*;

public class StartScreen extends JPanel implements Navigable {

    // initializing objects of the Event Handlers
    Handlers.StartHandler handlerStart ;
    Handlers.QuitHandler handlerQuit = new Handlers.QuitHandler();
    Handlers.HelpHandler helpHandler = new Handlers.HelpHandler();

    JPanel titlePanelTreasures, titlePanelConquests, titlePanelButtons
            , mainTextPanel, persistPanelButtons, mutePanel;

    JLabel titleLabelTreasures, titleLabelConquests;
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 76);

    JButton btnStart, btnInstructions, btnQuit
            , btnHelp, btnExitCurrentGame, btnMute;
    Font btnFont = new Font("Times New Roman", Font.PLAIN, 18);

    JTextArea mainTextArea;
    Font textFont = new Font("Times New Roman", Font.PLAIN, 24);
    private GUIController guiController;
    public StartScreen(GUIController guiController){
        setLayout(null);
        // setSize(900, 700);
        this.guiController = guiController;
        handlerStart = new Handlers.StartHandler(guiController);

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
        ScreenLauncher.frameState = 1;



//        btnLoad = new JButton("Load Game");
//        btnLoad.setBackground(Color.darkGray);
//        btnLoad.setForeground(Color.white);
//        btnLoad.setFont(btnFont);
//        btnLoad.addActionListener(handlerLoad);

        btnInstructions = new JButton("Read Instructions");
        btnInstructions.setBackground(Color.darkGray);
        btnInstructions.setForeground(Color.white);
        btnInstructions.setFont(btnFont);
        btnInstructions.addActionListener(helpHandler);


        btnQuit = new JButton("Quit ToC");
        btnQuit.setBackground(Color.black);
        btnQuit.setForeground(Color.white);
        btnQuit.setFont(btnFont);
        btnQuit.addActionListener(handlerQuit);

        // fill button panel
        titlePanelButtons.add(btnStart);
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

    @Override
    public void navigateBack() {

    }

    @Override
    public void printSelf() {
        System.out.println("Start screen");
    }
}