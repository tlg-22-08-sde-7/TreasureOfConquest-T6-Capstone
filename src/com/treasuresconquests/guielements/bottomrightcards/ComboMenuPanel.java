package com.treasuresconquests.guielements.bottomrightcards;

import com.treasuresconquests.guiengine.Handlers;
import com.treasuresconquests.guiengine.callbacks.ComboCallback;
import com.treasuresconquests.guiengine.other.Combo;

import javax.swing.*;
import java.awt.*;

public class ComboMenuPanel extends JPanel {

    private JComboBox<String> comboBox;
    private JButton submitButton;
    private JTextArea question;
    private Combo data;
    private Handlers.ComboHandler comboHandler;

    public ComboMenuPanel (){
        setLayout(new GridLayout(3,1));

        comboBox = new JComboBox<>();
        question = new JTextArea();
        question.setLineWrap(true);
        question.setEditable(false);
        submitButton = new JButton("Submit");
        comboHandler = new Handlers.ComboHandler(comboBox);
        submitButton.addActionListener(comboHandler);
        add(question);
        add(comboBox);
        add(submitButton);
    }

    public void init(Combo nextData, ComboCallback callback){
        question.setText(nextData.getQuestion());
        comboBox.setModel(new DefaultComboBoxModel<>(nextData.getItems()));
        comboBox.setSelectedIndex(0);

        comboHandler.setCallback(callback);
        repaint();
    }

}