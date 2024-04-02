package com.example.wrappedanytime.spotify.Datatypes;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class Album {
    private String id;
    private String name;
    private int total_tracks;
    private Image coverArt;
    private List<String> artistIDs;
    private List<String> trackIDs;
    private List<String> genres;

    public Album(String id, String name, int total_tracks, Image coverArt, List<String> artistIDs, List<String> trackIDs, List<String> genres) {
        this.id = id;
        this.name = name;
        this.total_tracks = total_tracks;
        this.coverArt = coverArt;
        this.artistIDs = artistIDs;
        this.trackIDs = trackIDs;
        this.genres = genres;
    }
    public Album() {
        this(null, null, 0, null, null, null, null);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotal_tracks() {
        return total_tracks;
    }

    public void setTotal_tracks(int total_tracks) {
        this.total_tracks = total_tracks;
    }

    public Image getCoverArt() {
        return coverArt;
    }

    public void setCoverArt(Image coverArt) {
        this.coverArt = coverArt;
    }

    public List<String> getArtistIDs() {
        return artistIDs;
    }

    public void setArtistIDs(List<String> artistIDs) {
        this.artistIDs = artistIDs;
    }

    public List<String> getTrackIDs() {
        return trackIDs;
    }

    public void setTrackIDs(List<String> trackIDs) {
        this.trackIDs = trackIDs;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id='" + id + '\'' +
                "\n name='" + name + '\'' +
                "\n total_tracks=" + total_tracks +
                "\n coverArt=" + coverArt +
                "\n artistIDs=" + artistIDs +
                "\n trackIDs=" + trackIDs +
                "\n genres=" + genres +
                '}';
    }
}
