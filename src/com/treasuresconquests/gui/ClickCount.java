package com.treasuresconquests.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickCount implements ActionListener {

    private int clickCount = 0;
    private JFrame frameClick;
    private JLabel labelClick;
    private JPanel panelClick;

    // constructor
    ClickCount() {
        frameClick = new JFrame();
        frameClick.setSize(350, 250);
        frameClick.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button = new JButton("Click me");
        button.addActionListener(this);

        labelClick = new JLabel("Number of clicks: 0");

        panelClick = new JPanel();
        panelClick.setBorder(BorderFactory.createEmptyBorder(30, 30, 20, 30));
        panelClick.setLayout(new GridLayout(0, 1));
        panelClick.add(button);
        panelClick.add(labelClick);

        frameClick.add(panelClick, BorderLayout.CENTER);
        frameClick.setTitle("Treasures of Conquests");
        frameClick.pack();

        frameClick.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        clickCount++;
        labelClick.setText("Number of clicks: " + clickCount);
    }

    // accessors
    /*
    public int getClickCount() {
        return clickCount;
    }

    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
     */

}