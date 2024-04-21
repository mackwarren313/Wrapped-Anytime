package com.example.wrappedanytime.spotify;

import android.media.MediaPlayer;
import android.util.Log;

import java.io.IOException;

public class Audio {
    String url;
    static MediaPlayer mp = new MediaPlayer();
    public Audio(String url) {
        this.url = url;
    }
    public static boolean playAudio(String url) {
        if (url == null) {
            return false;
        }
        mp = new MediaPlayer();
        try {
            mp.setDataSource(url);
            mp.prepare();
            mp.start();
        } catch (IOException e) {
            Log.e("myApp", "prepare() failed");
            return false;
        }
        return true;
    }
    public static void stopAudio() {
        mp.release();
        mp = null;
    }
}
