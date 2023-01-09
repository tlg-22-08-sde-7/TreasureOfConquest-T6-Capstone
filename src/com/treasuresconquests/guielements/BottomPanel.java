package com.treasuresconquests.guielements;

import com.treasuresconquests.guiclient.ScreenLauncher;
import com.treasuresconquests.guiengine.Handlers;
import com.treasuresconquests.guiengine.callbacks.Navigable;
import com.treasuresconquests.guiengine.callbacks.NavigbleSubscriber;
import com.treasuresconquests.guiengine.other.Music;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BottomPanel extends JPanel implements NavigbleSubscriber {
    private JButton btnBack, btnExitCurrentGame,
            btnQuit, btnHelp;
    public JButton btnMute;

    Music music = new Music();

    Handlers.QuitHandler handlerQuit = new Handlers.QuitHandler();
    Handlers.HelpHandler handlerHelp = new Handlers.HelpHandler();
    Handlers.ExitCurrentGameHandler handlerExitCurrentGame = new Handlers.ExitCurrentGameHandler();
    Handlers.BackHandler backHandler = new Handlers.BackHandler();

    public BottomPanel(){
        music.playMusic("resources/assets/songs/example.wav",-1);
        setLayout(null);
        setBackground(Color.blue);

        btnBack = new JButton("Return to Previous");
        btnBack.setBounds(200, 40, 150, 50);
        btnBack.addActionListener(backHandler);

        btnExitCurrentGame = new JButton("Exit current game");
        btnExitCurrentGame.setBounds(400, 40, 150, 50);
        btnExitCurrentGame.addActionListener(handlerExitCurrentGame);

        btnQuit = new JButton("Quit");
        btnQuit.setBounds(600, 40, 100, 50);
        btnQuit.addActionListener(handlerQuit);

        btnHelp = new JButton("Help");
        btnHelp.setBounds(25, 40, 100, 50);
        btnHelp.addActionListener(handlerHelp);

        btnMute = new JButton("MUTE");
        btnMute.setBounds(750, 40, 100, 50);
        btnMute.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (ScreenLauncher.frameState == 1){
                    if (Music.musicClip.isActive()){
                        Music.stopMusic();
                        btnMute.setText("UNMUTE");
                    }else{
                        Music.playMusic("resources/assets/songs/example.wav",-1);
                        btnMute.setText("MUTE");
                    }
                }
                else if (ScreenLauncher.frameState == 2){
                    if (Music.musicClip.isActive()){
                        Music.stopMusic();
                        btnMute.setText("UNMUTE");
                    }else{
                        Music.playMusic("resources/assets/songs/nyc.wav",-1);
                        btnMute.setText("MUTE");
                    }
                }
                else if (ScreenLauncher.frameState == 3){
                    if (Music.musicClip.isActive()){
                        Music.stopMusic();
                        btnMute.setText("UNMUTE");
                    }else{
                        Music.playMusic("resources/assets/songs/Tokyo.wav",-1);
                        btnMute.setText("MUTE");
                    }
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        add(btnHelp);
        add(btnBack);
        add(btnExitCurrentGame);
        add(btnQuit);
        add(btnMute);


        CenterPanel.subscribe(this);

    }

    @Override
    public void currentPage(Navigable screen) {
        backHandler.setCurrentPage(screen);
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Japan Landing");
        BottomPanel jlp = new BottomPanel();
        jFrame.setSize(900, 700);
        jFrame.getContentPane().add(jlp);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}