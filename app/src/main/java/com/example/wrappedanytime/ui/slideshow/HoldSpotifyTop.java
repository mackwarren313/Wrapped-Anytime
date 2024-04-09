package com.example.wrappedanytime.ui.slideshow;

import java.util.ArrayList;
import java.util.List;

public class HoldSpotifyTop {
    public ArrayList<String> getTopArtists() {
        ArrayList<String> topArtists = new ArrayList<>();
        topArtists.add("Artist 1");
        topArtists.add("Artist 2");
        topArtists.add("Artist 3");
        topArtists.add("Artist 4");
        topArtists.add("Artist 5");
        return topArtists;
    }


    public ArrayList<String> getTopTracks() {
        ArrayList<String> topTracks = new ArrayList<>();
        topTracks.add("Track 1");
        topTracks.add("Track 2");
        topTracks.add("Track 3");
        topTracks.add("Track 4");
        topTracks.add("Track 5");
        return topTracks;
    }

    public String getTopGenre(){
        return "genre";
    }

    public String getMinutesListened(){
        int min = 50;
        return String.valueOf(min);
    }

    public String getUsername(){
        return "name";
    }

    public ArrayList<String> accounts(){
        ArrayList<String> accounts = new ArrayList<>();
        return accounts;
    }

}
