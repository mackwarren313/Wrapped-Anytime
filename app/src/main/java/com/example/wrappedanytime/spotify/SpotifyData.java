package com.example.wrappedanytime.spotify;

import static com.example.wrappedanytime.spotify.Authentication.mAccessToken;
import static com.example.wrappedanytime.spotify.Authentication.mOkHttpClient;

import android.app.Activity;
import android.util.Log;
import android.view.animation.AccelerateInterpolator;
import android.widget.Toast;

import com.example.wrappedanytime.spotify.Datatypes.Album;
import com.example.wrappedanytime.spotify.Datatypes.Artist;
import com.example.wrappedanytime.spotify.Datatypes.Image;
import com.example.wrappedanytime.spotify.Datatypes.Track;
import com.example.wrappedanytime.spotify.Datatypes.User;
import com.example.wrappedanytime.spotify.Datatypes.UserData;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SpotifyData {
    private Activity activity;
    public SpotifyData(Activity activity) {
        this.activity = activity;
    }
    private class DataGetter implements Runnable {
        private String value;
        private Request request;
        private Call call;
        private OkHttpClient client;
        DataGetter(OkHttpClient client, Request request) {
            Log.d("myLog", "thread created");
            this.client = client;
            this.request = request;
        }
        @Override
        public void run() {
            Authentication.cancelCall();
            call = client.newCall(request);
            Response response;
            try {
                response = call.execute();
                value = response.body().string();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        public String getValue() {
            return value;
        }
    }
    private String retJSON(Request request) {
        DataGetter dg = new DataGetter(Authentication.mOkHttpClient, request);
        Thread thread =new Thread(dg);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return dg.getValue();
    }
    public User getUser() {
        final Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/me")
                .addHeader("Authorization", "Bearer " + mAccessToken)
                .build();
        return new User(retJSON(request));
    }

    public Track getTrack(String trackID) {
        final Request request = new Request.Builder()
            .url("https://api.spotify.com/v1/tracks/" + trackID)
            .addHeader("Authorization", "Bearer " + mAccessToken)
            .build();
        return new Track(retJSON(request));
    }
    public Album getAlbum(String albumID) {
        final Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/albums/" + albumID)
                .addHeader("Authorization", "Bearer " + mAccessToken)
                .build();
        return new Album(retJSON(request));
    }
    public Artist getArtist(String id) {
        final Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/artists/" + id)
                .addHeader("Authorization", "Bearer " + mAccessToken)
                .build();
        return new Artist(retJSON(request));
    }
}
