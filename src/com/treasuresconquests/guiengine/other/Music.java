package com.treasuresconquests.guiengine.other;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Music {
    public static Clip musicClip;
    public static boolean musicOn = true;

    public Music() {

    }

    public static void playMusic(String musicLocation, int count) {
        try {
            URL url = Music.class.getClassLoader().getResource(musicLocation);
            assert url != null;
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(url);
            musicClip = AudioSystem.getClip();
            musicClip.open(audioInput);
            musicClip.start();
            musicClip.loop(count);
            musicOn = true;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void stopMusic() {
        musicClip.stop();
        musicClip.close();
        musicOn = false;
    }

    public boolean isMusicOn() {
        return musicOn;
    }


    public void setMusicOn(boolean musicOn) {
        this.musicOn = musicOn;
    }

}