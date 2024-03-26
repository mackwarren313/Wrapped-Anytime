package com.example.wrappedanytime.firebasewrapper.data;

import java.time.DateTimeException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class WrappedData {
    private String userId;
    private UUID wrappedId;
    private Date dataTimeCreated; // enforce time stamp?

    private List<String> topArtistSpotifyURI;
    private List<String> topSongsSpotifyURI;
    private String topGenre; // TODO: use enum ?
    private long minutesListened;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UUID getWrappedId() {
        return wrappedId;
    }

    public void setWrappedId(UUID wrappedId) {
        this.wrappedId = wrappedId;
    }

    public Date getDataTimeCreated() {
        return dataTimeCreated;
    }

    public void setDataTimeCreated(Date dataTimeCreated) {
        this.dataTimeCreated = dataTimeCreated;
    }

    public List<String> getTopArtistSpotifyURI() {
        return topArtistSpotifyURI;
    }

    public void setTopArtistSpotifyURI(List<String> topArtistSpotifyURI) {
        this.topArtistSpotifyURI = topArtistSpotifyURI;
    }

    public List<String> getTopSongsSpotifyURI() {
        return topSongsSpotifyURI;
    }

    public void setTopSongsSpotifyURI(List<String> topSongsSpotifyURI) {
        this.topSongsSpotifyURI = topSongsSpotifyURI;
    }

    public String getTopGenre() {
        return topGenre;
    }

    public void setTopGenre(String topGenre) {
        this.topGenre = topGenre;
    }

    public long getMinutesListened() {
        return minutesListened;
    }

    public void setMinutesListened(long minutesListened) {
        this.minutesListened = minutesListened;
    }
}
