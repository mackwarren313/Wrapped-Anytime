package com.example.wrappedanytime.spotify;

import static com.example.wrappedanytime.spotify.Authentication.mAccessToken;
import static com.example.wrappedanytime.spotify.Authentication.mOkHttpClient;

import android.app.Activity;
import android.util.Log;
import android.view.animation.AccelerateInterpolator;
import android.widget.Toast;

import com.example.wrappedanytime.spotify.Datatypes.Album;
import com.example.wrappedanytime.spotify.Datatypes.Image;
import com.example.wrappedanytime.spotify.Datatypes.Track;
import com.example.wrappedanytime.spotify.Datatypes.User;
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
        User ret = new User();
        final Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/me")
                .addHeader("Authorization", "Bearer " + mAccessToken)
                .build();
        JsonElement jsonElement = JsonParser.parseString(retJSON(request));
        if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            if (jsonObject.has("display_name")) {
                String name = jsonObject.get("display_name").getAsString();
                ret.setDisplayName(name);
            }
            if (jsonObject.has("id")) {
                String id = jsonObject.get("id").getAsString();
                ret.setId(id);
            }
            if (jsonObject.has("uri")) {
                String uri = jsonObject.get("uri").getAsString();
                ret.setUri(uri);
            }
            if (jsonObject.has("images")) {
                JsonArray jsonArray = jsonObject.getAsJsonArray("images");
                JsonObject largestImage = jsonArray.get(jsonArray.size()-1).getAsJsonObject();
                ret.setPfp(new Image(largestImage));
            }
            if (jsonObject.has("email")) {
                String email = jsonObject.get("email").getAsString();
                ret.setEmail(email);
            }
        }

        return ret;
    }

    public Track getTrack(String trackID) {
        Track ret = new Track();
        final Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/tracks/" + trackID)
                .addHeader("Authorization", "Bearer " + mAccessToken)
                .build();
        JsonObject jsonObject = JsonParser.parseString(retJSON(request)).getAsJsonObject();
        ret.setName(jsonObject.get("name").getAsString());
        ret.setAlbumID(jsonObject.getAsJsonObject("album").get("id").getAsString());
        ArrayList<String> artistIDs = new ArrayList<>();
        for (JsonElement artist : jsonObject.getAsJsonArray("artists")) {
            artistIDs.add(artist.getAsJsonObject().get("id").getAsString());
        }
        ret.setArtistsIDs(artistIDs);
        ret.setLength(jsonObject.get("duration_ms").getAsInt());
        ret.setId(jsonObject.get("id").getAsString());
        ret.setPreviewUrl(jsonObject.get("preview_url").getAsString());
        return ret;
    }
    public Album getAlbum(String albumID) {
        Album ret = new Album();
        final Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/albums/" + albumID)
                .addHeader("Authorization", "Bearer " + mAccessToken)
                .build();
        JsonObject jsonObject = JsonParser.parseString(retJSON(request)).getAsJsonObject();
        ret.setId(jsonObject.get("id").getAsString());
        ret.setName(jsonObject.get("name").getAsString());
        ret.setTotal_tracks(jsonObject.get("total_tracks").getAsInt());

        JsonArray images = jsonObject.getAsJsonArray("images");
        JsonObject largestImage = images.get(0).getAsJsonObject();
        ret.setCoverArt(new Image(largestImage));

        ArrayList<String> artistIDs = new ArrayList<>();
        for (JsonElement artist : jsonObject.getAsJsonArray("artists")) {
            artistIDs.add(artist.getAsJsonObject().get("id").getAsString());
        }
        ret.setArtistIDs(artistIDs);

        ArrayList<String> trackIds = new ArrayList<>();
        for (JsonElement track : jsonObject.getAsJsonObject("tracks").getAsJsonArray("items")) {
            trackIds.add(track.getAsJsonObject().get("id").getAsString());
        }
        ret.setTrackIDs(trackIds);

        ArrayList<String> genres = new ArrayList<>();
        for (JsonElement genre : jsonObject.getAsJsonArray("genres")) {
            genres.add(genre.getAsString());
        }
        ret.setGenres(genres);

        return ret;
    }
}
