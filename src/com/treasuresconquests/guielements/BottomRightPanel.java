package com.treasuresconquests.guielements;

import com.treasuresconquests.app.GUIController;
import com.treasuresconquests.guiengine.callbacks.ComboCallback;
import com.treasuresconquests.guiengine.other.Combo;
import com.treasuresconquests.guiengine.other.DialogSubscriber;
import com.treasuresconquests.guiengine.other.Quiz;
import com.treasuresconquests.guiengine.other.RiddlesTreasures;
import com.treasuresconquests.guielements.bottomrightcards.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BottomRightPanel extends JPanel implements DialogSubscriber {
    private static ComboMenuPanel comboMenuPanel;
    private static InformationPanel informationPanel;
    private static QuizPanel quizPanel;

    private static final String COMBOMENUPANEL = "ComboMenuPanel";
    private static final String INFORMATIONPANEL = "InformationPanel";
    private static final String QUIZPANEL = "QuizPanel";

    private static CardLayout cardLayout;
    private static List<RiddlesTreasures> globalRiddles;
    private static int numberOfRiddlesShown;

    private GUIController guiController;

    private static BottomRightPanel cards;

    private static PopupFactory popupFactory;


    public BottomRightPanel(GUIController guiController){
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        this.guiController = guiController;
        cards = this;

        popupFactory = new PopupFactory();
        comboMenuPanel = new ComboMenuPanel();
        informationPanel = new InformationPanel();
        quizPanel = new QuizPanel(guiController);

        add(informationPanel, INFORMATIONPANEL);
        add(comboMenuPanel, COMBOMENUPANEL);
        //add(quizPanel, QUIZPANEL);

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
        JFrame frame = new JFrame("Quiz");

        frame.setSize(400,400);

        JDialog dialog = new JDialog(frame, "Quiz");
        dialog.add(quizPanel);
        dialog.setSize(400,400);
        center(dialog);
        dialog.setVisible(true);
        quizPanel.closeWhenDone(dialog, cards);
    }

    private static void center(JDialog frame) {

        // get the size of the screen, and on systems with multiple displays,
        // the primary display is used
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        // calculate the new location of the window
        int w = frame.getSize().width;
        int h = frame.getSize().height;

        int x = (dim.width - w) / 2;
        int y = (dim.height - h) / 2;

        // moves this component to a new location, the top-left corner of
        // the new location is specified by the x and y
        // parameters in the coordinate space of this component's parent
        frame.setLocation(x, 15);
    }

    public static void showPopupQuiz(Quiz quiz) {
        //.. doing some research
    }

    public static void showQuizzesPanel(List<RiddlesTreasures> riddles) {
        globalRiddles = riddles;
        numberOfRiddlesShown = 0;
        if(riddles.size() > numberOfRiddlesShown) {
            Quiz quiz = new Quiz(riddles.get(numberOfRiddlesShown++));
            showQuizPanel(quiz);
        }
    }

    @Override
    public void dialogClosed() {
        if(globalRiddles.size() > numberOfRiddlesShown) {
            Quiz quiz = new Quiz(globalRiddles.get(numberOfRiddlesShown++));
            showQuizPanel(quiz);
        }
    }
}