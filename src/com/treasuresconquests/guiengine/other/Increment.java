package com.treasuresconquests.guiengine.other;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Increment implements ActionListener {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Increment inc = new Increment();
                inc.createJFrame();
            }
        });
    }

    private static NumberFormat NF = NumberFormat.getIntegerInstance();

    private int[] values;
    private int startValue;
    private int currentValue;
    private JTextField valueField;

    public Increment() {
        this.startValue = 100;
        this.currentValue = startValue;
        this.values = new int[] { 10, 25 };
    }

    private void createJFrame() {
        JFrame frame = new JFrame("Increment Value");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(createValuePanel(),
                BorderLayout.BEFORE_FIRST_LINE);
        frame.add(createButtonPanel(), BorderLayout.CENTER);

        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    private JPanel createValuePanel() {
        JPanel panel = new JPanel(new FlowLayout());

        JLabel label = new JLabel("Value: ");
        panel.add(label);

        valueField = new JTextField(10);
        valueField.setEditable(false);
        setValueField(currentValue);
        panel.add(valueField);

        return panel;
    }

    public void setValueField(int value) {
        valueField.setText(NF.format(value));
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        for (int index = 0; index < values.length; index++) {
            JButton button = new JButton("Add " + values[index]);
            button.addActionListener(this);
            button.setActionCommand(Integer.toString(values[index]));
            panel.add(button);
        }
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        int value = Integer.valueOf(event.getActionCommand());
        currentValue += value;
        setValueField(currentValue);
    }
}

