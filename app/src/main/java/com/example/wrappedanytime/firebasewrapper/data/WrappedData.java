package com.example.wrappedanytime.firebasewrapper.data;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class WrappedData extends FirebaseItem{
    private String userId;
    private UUID id;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    @Override
    public Map<String, Object> toMap() {
        return new HashMap<String, Object>() {{
            put("userId", getUserId());
            put("dataTimeCreated", getDataTimeCreated());
            put("topArtistSpotifyURI", getTopArtistSpotifyURI());
            put("topSongsSpotifyURI", getTopSongsSpotifyURI());
            put("topGenre", getTopGenre());
            put("minutesListened", getMinutesListened());
        }};
    }
}
