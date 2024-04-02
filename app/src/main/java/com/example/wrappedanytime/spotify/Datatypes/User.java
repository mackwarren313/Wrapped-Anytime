package com.example.wrappedanytime.spotify.Datatypes;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

enum TimeRange {
    SHORT, MEDIUM, LONG
}

public class User {
    private String displayName;
    private String id;
    private Image pfp;
    private String uri;
    private String email;
    private List<Track> topTracks;
    private List<String> topArtists;
    private String topGenre;

    private TimeRange timeRange;

    public User(String displayName, String id, Image pfp, String uri, String email, List<Track> topTracks, List<String> topArtists, String topGenre, TimeRange timeRange) {
        this.displayName = displayName;
        this.id = id;
        this.pfp = pfp;
        this.uri = uri;
        this.email = email;
        this.topTracks = new ArrayList<Track>();
        this.topArtists = new ArrayList<String>();
        this.topGenre = topGenre;
        this.timeRange = timeRange;

    }

    public User() {
        this(null, null, null, null, null, null, null, null, null);
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Image getPfp() {
        return pfp;
    }

    public void setPfp(Image pfp) {
        this.pfp = pfp;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setTopTracks(ArrayList<Track> topTracks) { this.topTracks = topTracks; }

    public void setTopArtists(ArrayList<String> topArtists) { this.topArtists = topArtists; }

    public void setTopGenre(String topGenre) { this.topGenre = topGenre; }

    public List<Track> getTopTracks() { return topTracks; }
    public List<String> getTopArtists() { return topArtists; }

    public String getTopGenre() { return topGenre; }

    public TimeRange getTimeRange() { return timeRange; }

    public void setTimeRange(TimeRange timeRange) { this.timeRange = timeRange; }

    @Override
    public String toString() {
        return "User{" +
                "displayName='" + displayName + '\'' +
                "\n id='" + id + '\'' +
                "\n pfp=" + pfp +
                "\n uri='" + uri + '\'' +
                "\n email='" + email + '\'' +
                '}';
    }
}
