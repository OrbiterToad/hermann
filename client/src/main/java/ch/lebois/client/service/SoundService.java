package ch.lebois.client.service;

import ch.lebois.client.handler.ResponseSender;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class SoundService {


    public void playSound(String command) {
        play(command.substring(5));
    }


    private void play(String path) {
        InputStream in;
        try {
            in = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            new ResponseSender("error", "Sound File not found");
            return;
        }

        AudioStream audioStream;
        try {
            audioStream = new AudioStream(in);
        } catch (IOException e) {
            new ResponseSender("error", "Cant Play Sound file");
            return;
        }
        new ResponseSender("response", "Playing Soundfile " + path);
        AudioPlayer.player.start(audioStream);
        new ResponseSender("response", "Stopped Soundfile");
    }

}
