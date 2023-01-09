package com.treasuresconquests.guielements.bottomrightcards;

import javax.swing.*;
import java.awt.*;

public class InformationPanel extends JPanel {
    private JTextArea info;


    public InformationPanel(){
        setLayout(new BorderLayout());

        info = new JTextArea();
        info.setLineWrap(true);
        info.setEditable(false);
        // info.setBackground(Color.gray);

        add(info, BorderLayout.CENTER);
    }

    public void init(String nextInfo){
        info.setText(nextInfo);
        appendDialogue();
    }

    private void appendDialogue() {
        info.append("\n\n\n\n\n\tDIALOGUE");
    }

}