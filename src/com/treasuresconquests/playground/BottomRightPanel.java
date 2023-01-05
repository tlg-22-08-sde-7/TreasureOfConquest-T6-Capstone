package com.treasuresconquests.playground;

import com.treasuresconquests.app.GUIController;
import com.treasuresconquests.guiengine.callbacks.ComboCallback;
import com.treasuresconquests.guiengine.other.Combo;
import com.treasuresconquests.guiengine.other.Quiz;
import com.treasuresconquests.playground.bottomrightcards.*;

import javax.swing.*;
import java.awt.*;

public class BottomRightPanel extends JPanel {
    private static ComboMenuPanel comboMenuPanel;
    private static InformationPanel informationPanel;
    private static QuizPanel quizPanel;

    private static final String COMBOMENUPANEL = "ComboMenuPanel";
    private static final String INFORMATIONPANEL = "InformationPanel";
    private static final String QUIZPANEL = "QuizPanel";

    private static CardLayout cardLayout;

    private GUIController guiController;

    private static BottomRightPanel cards;


    public BottomRightPanel(GUIController guiController){
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        this.guiController = guiController;
        cards = this;

        comboMenuPanel = new ComboMenuPanel();
        informationPanel = new InformationPanel();
        quizPanel = new QuizPanel(guiController);

        add(informationPanel, INFORMATIONPANEL);
        add(comboMenuPanel, COMBOMENUPANEL);
        add(quizPanel, QUIZPANEL);

    }

    public static void showInformationPanel(String information) {
        informationPanel.init(information);
        cardLayout.show(cards, INFORMATIONPANEL);
    }

    public static void showComboMenuPanel(Combo data,
                                          ComboCallback callback) {
        comboMenuPanel.init(data, callback);
        cardLayout.show(cards, COMBOMENUPANEL);
    }

    public static void showQuizPanel(Quiz data) {
        quizPanel.init(data);
        cardLayout.show(cards, QUIZPANEL);
    }

}