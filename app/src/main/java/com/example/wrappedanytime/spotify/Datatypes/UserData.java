package com.example.wrappedanytime.spotify.Datatypes;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class UserData{
    public enum TimeRange {
        SHORT, MEDIUM, LONG
    }
    private List<Track> topTracks;
    private List<Artist> topArtists;
    private String topGenre;
    private UserData.TimeRange tr;
    private Date genDate;


    public UserData (String userID, Track[] topTracks, String[] topArtists, String topGenre, Date genDate) {
        this.topTracks = new ArrayList<>();
        this.topArtists = new ArrayList<>();
        this.topGenre = topGenre;
        this.genDate = genDate;
    }

    public UserData() { this(null, null, null, null, null); }
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

        Map<String, Integer> frequencyMap = new HashMap<>();
        for (Artist a : topArtists) {
            for(String genre : a.getGenres()) {
                frequencyMap.put(genre, frequencyMap.getOrDefault(genre, 0) + 1);
            }
        }
        int maxFrequency = Collections.max(frequencyMap.values());
        List<String> mostCommonGenres = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() == maxFrequency) {
                mostCommonGenres.add(entry.getKey());
            }
        }
        Random random = new Random();
        this.topGenre = mostCommonGenres.get(random.nextInt(mostCommonGenres.size()));
    }
    public List<Track> getTopTracks() { return topTracks; }

    public List<Artist> getTopArtists() { return topArtists; }

    public String getTopGenre() { return topGenre; }


    public void setTopTracks(List<Track> topTracks) { this.topTracks = topTracks; }

    public void setTopArtists(List<Artist> topArtists) { this.topArtists = topArtists; }

    public void setTopGenre(String topGenre) { this.topGenre = topGenre; }

    public TimeRange getTr() {
        return tr;
    }

    public void setTr(TimeRange tr) {
        this.tr = tr;
    }

    public Date getGenDate() {
        return genDate;
    }

    public void setGenDate(Date genDate) {
        this.genDate = genDate;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "topTracks=" + topTracks +
                "\n topArtists=" + topArtists +
                "\n topGenre='" + topGenre + '\'' +
                '}';
    }
}
