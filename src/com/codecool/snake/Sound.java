package com.codecool.snake;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound {

    Clip music;

    public Clip getHorrorMusic() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("resources/sound/horrormusic.wav"));
            music = AudioSystem.getClip();
            music.open(audioInputStream);

        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
        return music;
    }

    public void startMusic(){
        music = getHorrorMusic();
        music.start();
        music.loop(-1);
    }

    public void stopMusic(){
        music.stop();
    }


    public static void playScream() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("resources/sound/scream.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
}
