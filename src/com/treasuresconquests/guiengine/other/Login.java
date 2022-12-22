package com.treasuresconquests.guiengine.other;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login implements ActionListener {

    int count = 0;
    private static JFrame loginFrame;
    private static JPanel loginPanel;
    private static JLabel userTextLabel;
    private static JLabel passwordLabel;
    private static JTextField userText;
    private static JPasswordField passwordField;
    private static JButton loginButton;
    private static JLabel successLabel;

    // constructor
    public Login() {
        loginFrame = new JFrame();
        loginFrame.setSize(450, 300);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loginPanel = new JPanel();
        loginPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 20, 30));
        loginPanel.setLayout(null);

        loginFrame.add(loginPanel);

        userTextLabel = new JLabel("User");
        userTextLabel.setBounds(20, 20, 80, 30);
        loginPanel.add(userTextLabel);

        userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        loginPanel.add(userText);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(20, 70, 80, 25);
        loginPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(100, 70, 165, 25);
        loginPanel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(100, 120, 80, 30);
        loginButton.addActionListener(this);
        loginPanel.add(loginButton);

        successLabel = new JLabel("");
        successLabel.setBounds(20, 110, 300, 25);
        loginPanel.add(successLabel);


        //successLabel.setText();

        loginFrame.setTitle("Login to Treasures of Conquests");
        loginFrame.pack();
        loginFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        count++;
        System.out.println("Hi! Login button clicked: " + count);

        String user = userText.getText();
        String password = passwordField.getText();
        System.out.println(user + ", " + password);

        if(user.equals("imauser") && password.equals("furrydino123")) {
            successLabel.setText("Login success!");
        }
    }
}