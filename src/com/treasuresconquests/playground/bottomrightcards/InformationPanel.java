package com.treasuresconquests.playground.bottomrightcards;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class InformationPanel extends JPanel {
    private JTextArea info;


    public InformationPanel(){
        setLayout(new BorderLayout());

        info = new JTextArea();
        info.setLineWrap(true);
        info.setEditable(false);

        add(info, BorderLayout.CENTER);
    }

    public void init(String nextInfo){
        info.setText(nextInfo);
    }

}