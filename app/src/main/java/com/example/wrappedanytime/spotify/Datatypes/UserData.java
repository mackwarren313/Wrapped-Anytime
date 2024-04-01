package com.example.wrappedanytime.spotify.Datatypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserData {
    public enum TimeRange {
        SHORT, MEDIUM, LONG
    }
    private Map<TimeRange, String>  timeRangeMap = new HashMap<>();
    private String userID;
    private List<Track> topTracks;
    private List<String> topArtists;
    private String topGenre;

    public UserData (String userID, Track[] topTracks, String[] topArtists, String topGenre) {
        this.userID = userID;
        this.topTracks = new ArrayList<>();
        this.topArtists = new ArrayList<>();
        this.topGenre = topGenre;
        timeRangeMap.put(TimeRange.SHORT, "short_term");
        timeRangeMap.put(TimeRange.MEDIUM, "medium_term");
        timeRangeMap.put(TimeRange.LONG, "long_term");
    }

    public UserData() { this(null, null, null, null); }

    public List<Track> getTopTracks() { return topTracks; }

    public List<String> getTopArtists() { return topArtists; }

    public String getTopGenre() { return topGenre; }

    public String getUserID() { return userID; }

    public void setTopTracks(List<Track> topTracks) { this.topTracks = topTracks; }

    public void setTopArtists(List<String> topArtists) { this.topArtists = topArtists; }

    public void setTopGenre(String topGenre) { this.topGenre = topGenre; }

    public void setUserID(String userID) { this.userID = userID; }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("UserData{" + "userID='" + userID + '\'' + "\n topTracks='");
        for (Track track : topTracks) { output.append(track.getName()); }
        output.append('\'' + "\n topArtists='");
        for (String artist : topArtists) { output.append(artist); }
        output.append('\'' + "\n topGenre='" + topGenre);
        return (output.toString());
    }
}
