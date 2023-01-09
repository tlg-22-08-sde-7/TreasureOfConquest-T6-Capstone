package com.treasuresconquests.guielements.bottomrightcards;

import com.treasuresconquests.app.GUIController;
import com.treasuresconquests.guiengine.Handlers;
import com.treasuresconquests.guiengine.other.Quiz;
import com.treasuresconquests.guielements.BottomRightPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class QuizPanel extends JPanel{
    private JTextArea questionArea;
    private ButtonGroup buttonGroup;
    private JRadioButton button1;
    private JRadioButton button2;
    private JRadioButton button3;
    private JRadioButton button4;

    private JButton submitButton;
    private Quiz quiz;

    private Handlers.QuizHandler quizHandler;
    private GUIController guiController;

    private Popup popup;

    public QuizPanel(GUIController guiController){
        setLayout(new GridLayout(7,1));
        this.guiController = guiController;
        questionArea = new JTextArea();
        questionArea.setLineWrap(true);
        questionArea.setEditable(false);
        button1 = new JRadioButton();
        button2 = new JRadioButton();
        button3 = new JRadioButton();
        button4 = new JRadioButton();
        buttonGroup = new ButtonGroup();
        buttonGroup.add(button1);
        buttonGroup.add(button2);
        buttonGroup.add(button3);
        buttonGroup.add(button4);
        submitButton = new JButton("Submit");
        JPanel sizePanel = new JPanel();
        sizePanel.add(submitButton);
        quiz = new Quiz(null, null, null, 0, null);
        quizHandler
                = new Handlers.QuizHandler(buttonGroup, quiz, guiController);
        submitButton.addActionListener(quizHandler);
        add(questionArea);
        add(button1);
        add(button2);
        add(button3);
        add(button4);
        add(sizePanel);

    }



    public void init(Quiz nextQuiz){
        questionArea.setText(nextQuiz.getQuestion());
        Vector<String> options = nextQuiz.getItems();
        buttonGroup.clearSelection();

        if(options.size() > 0){
            button1.setText(options.get(0));
            button1.setActionCommand(options.get(0));
            button1.setEnabled(true);
        }
        else {
            button1.setEnabled(false);
            button1.setText("");
            button1.setActionCommand(null);
        }

        if(options.size() > 1){
            button2.setText(options.get(1));
            button2.setActionCommand(options.get(1));
            button2.setEnabled(true);
        }
        else {
            button2.setEnabled(false);
            button2.setText("");
            button2.setActionCommand(null);
        }

        if(options.size() > 2){
            button3.setText(options.get(2));
            button3.setActionCommand(options.get(2));
            button3.setEnabled(true);
        }
        else {
            button3.setEnabled(false);
            button3.setText("");
            button3.setActionCommand(null);
        }

        if(options.size() > 3){
            button4.setText(options.get(3));
            button4.setActionCommand(options.get(3));
            button4.setEnabled(true);
        }
        else {
            button4.setEnabled(false);
            button4.setText("");
            button4.setActionCommand(null);
        }
        quizHandler.setQuiz(nextQuiz);
        this.quiz = nextQuiz;

    }


    public void closeWhenDone(JDialog dialog,
                              BottomRightPanel subscriber) {
        quizHandler.setDialog(dialog);
        quizHandler.setSubscriber(subscriber);
    }
}