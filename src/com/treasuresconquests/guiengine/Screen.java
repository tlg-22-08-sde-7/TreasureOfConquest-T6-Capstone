package com.treasuresconquests.guiengine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen {

    int count = 0;
    JFrame appWindow;
    Container appContainer;
    JPanel titlePanelTreasures, titlePanelConquests, titlePanelButtons
            , mainTextPanel, persistPanelButtons;

    JLabel titleLabelTreasures, titleLabelConquests;
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 76);

    JButton btnStart, btnLoad, btnInstructions, btnQuit
            , btnHelp, btnExitCurrentGame;
    Font btnFont = new Font("Times New Roman", Font.PLAIN, 18);

    JTextArea mainTextArea;
    Font textFont = new Font("Times New Roman", Font.PLAIN, 24);

    // handlers - TitleScreen
    StartHandler handlerStart = new StartHandler();
    LoadHandler handlerLoad = new LoadHandler();
    InstructionsHandler handlerInstructions = new InstructionsHandler();
    QuitHandler handlerQuit = new QuitHandler();

    // handlers - PlayScreen
    HelpHandler handlerHelp = new HelpHandler();
    ExitCurrentGameHandler handlerExitCurrentGame = new ExitCurrentGameHandler();

    // empty constructor
    public Screen() {
    }

    public void launchTitleScreen() {

        // window
        appWindow = new JFrame();
        appWindow.setSize(900, 700);
        appWindow.setTitle("Treasures of Conquests!");
        appWindow.setBackground(Color.blue);
        appWindow.setAlwaysOnTop(true);
        appWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appWindow.getContentPane().setBackground(Color.black);
        appWindow.setLayout(null);
        appWindow.setVisible(true);
        appContainer = appWindow.getContentPane();

        // treasures panel
        titlePanelTreasures = new JPanel();
        titlePanelTreasures.setBounds(100, 100, 700, 100);
        titlePanelTreasures.setBackground(Color.black);
        titlePanelTreasures.setLayout(new FlowLayout());

        titleLabelTreasures = new JLabel("TREASURES of...");
        titleLabelTreasures.setForeground(Color.yellow);
        titleLabelTreasures.setFont(titleFont);

        titlePanelTreasures.add(titleLabelTreasures);

        // conquests panel
        titlePanelConquests = new JPanel();
        titlePanelConquests.setBounds(100, 250, 700, 100);
        titlePanelConquests.setBackground(Color.black);
        titlePanelConquests.setLayout(new FlowLayout());

        titleLabelConquests = new JLabel("CONQUESTS");
        titleLabelConquests.setForeground(Color.red);
        titleLabelConquests.setFont(titleFont);

        titlePanelConquests.add(titleLabelConquests);

        // buttons panel
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

        // container
        appContainer.add(titlePanelTreasures);
        appContainer.add(titlePanelConquests);
        appContainer.add(titlePanelButtons);
    }

    // handlers - TitleScreen
    public class StartHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            launchGameScreen();
        }
    }

    public class LoadHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public class InstructionsHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public class QuitHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            System.exit(0);
        }
    }

    public void launchGameScreen() {

        titlePanelTreasures.setVisible(false);
        titlePanelConquests.setVisible(false);
        titlePanelButtons.setVisible(false);

        // main text panel
        mainTextPanel = new JPanel();
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

        appContainer.add(mainTextPanel);
        appContainer.add(persistPanelButtons);
    }

    public class HelpHandler implements ActionListener {

        // prints help with commands / option for instructions(?)
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public class ExitCurrentGameHandler implements ActionListener {

        // exits to the title/start screen
        @Override
        public void actionPerformed(ActionEvent e) {
            //launchTitleScreen();

        }
    }


}
