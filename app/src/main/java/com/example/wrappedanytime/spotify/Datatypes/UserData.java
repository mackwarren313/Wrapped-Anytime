package com.example.wrappedanytime.spotify.Datatypes;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserData {
    public enum TimeRange {
        SHORT, MEDIUM, LONG
    }
    private List<Track> topTracks;
    private List<Artist> topArtists;
    private String topGenre;

    public UserData (String userID, Track[] topTracks, String[] topArtists, String topGenre) {
        this.topTracks = new ArrayList<>();
        this.topArtists = new ArrayList<>();
        this.topGenre = topGenre;
    }

    public UserData() { this(null, null, null, null); }
    public UserData(String artistJson, String trackJson) {
        JsonObject jsonObject = JsonParser.parseString(artistJson).getAsJsonObject();
        ArrayList<Track> topTracks = new ArrayList<>();
        ArrayList<Artist> topArtists = new ArrayList<>();
        JsonArray jsonArtists = jsonObject.getAsJsonArray("items");
        for(JsonElement object : jsonArtists) {
            topArtists.add(new Artist(object.toString()));
        }
        this.topArtists = topArtists;

        jsonObject = JsonParser.parseString(trackJson).getAsJsonObject();
        JsonArray jsonTracks = jsonObject.getAsJsonArray("items");
        for(JsonElement object:jsonTracks) {
            try {
                topTracks.add(new Track(object.toString()));
            } catch (Exception e) {
                Log.e("myApp", e.toString());
            }
        }
        this.topTracks = topTracks;
    }
    public List<Track> getTopTracks() { return topTracks; }

    public List<Artist> getTopArtists() { return topArtists; }

    public String getTopGenre() { return topGenre; }


    public void setTopTracks(List<Track> topTracks) { this.topTracks = topTracks; }

    public void setTopArtists(List<Artist> topArtists) { this.topArtists = topArtists; }

    public void setTopGenre(String topGenre) { this.topGenre = topGenre; }


    @Override
    public String toString() {
        return "UserData{" +
                "topTracks=" + topTracks +
                "\n topArtists=" + topArtists +
                "\n topGenre='" + topGenre + '\'' +
                '}';
    }
}
