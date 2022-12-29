package com.treasuresconquests.guiengine.screens;

import com.treasuresconquests.guiengine.Handlers;

import javax.swing.*;
import java.awt.*;
import java.io.*;

// extends and has all methods of JPanel
public class HelpScreen extends JPanel {

    private JTextArea instructionArea;
    private JButton backButton;
    private JButton otherUse;
    private JScrollPane scrollPane;
    private final String instructionsFileName = "instructions.txt";
    private Font txtFont = new Font("Times New Roman", Font.PLAIN, 30);
    Handlers.StartHandler handlerStart = new Handlers.StartHandler();

    // ctor
    public HelpScreen(){
        setLayout(null);    // set to 'null' because we need full control of the Swing layout.
        String fileContents = readFileContents();      // method returns a String, and has contents of instructions.txt
        instructionArea = new JTextArea(fileContents); // creating a new JText area with a String inside it.
        instructionArea.setLineWrap(true);
        instructionArea.setFont(txtFont);
        scrollPane = new JScrollPane(instructionArea); // enables JText area to be scrollable
        scrollPane.setBounds(100, 100, 600, 400); // x is horizontal alignment, y is vertical.
        backButton = new JButton("Back to Current Game");
        backButton.setBounds(200, 550, 300, 50);
        backButton.addActionListener(handlerStart);
        otherUse = new JButton("Other Use");
        otherUse.setBounds(550, 550, 100, 50);

        //  JPanel has a 'add' method.  HelpScreen ctor components were initialized and added to JPanel.
        add(scrollPane);
        add(backButton);
        add(otherUse);
    }

    private String readFileContents() {
        String contents = "";
        try {
            FileReader fileReader = new FileReader(new File("resources/assets/instructions.txt"));
            BufferedReader reader = new BufferedReader(fileReader);
            String currentLine = null;
            while((currentLine = reader.readLine()) != null){
                contents += currentLine;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contents;
    }
}