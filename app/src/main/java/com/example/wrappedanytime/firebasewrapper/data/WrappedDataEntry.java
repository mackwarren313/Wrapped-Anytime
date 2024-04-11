package com.example.wrappedanytime.firebasewrapper.data;

import com.example.wrappedanytime.spotify.Datatypes.UserData;
import com.example.wrappedanytime.spotify.SpotifyData;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class WrappedDataEntry extends FirebaseEntry {
    private String userId;
    private UUID id;
    private UserData.TimeRange timeRange;
    private List<String> topArtistsSpotifyURI;
    private List<String> topSongsSpotifyURI;
    private String topGenre;
    public WrappedDataEntry(){}

    public WrappedDataEntry(UserData userData){
        this.topGenre = userData.getTopGenre();
        userData.getTopTracks().forEach(
                track -> this.topSongsSpotifyURI.add( track.getId() )
        );
        userData.getTopArtists().forEach(
                artist -> this.topArtistsSpotifyURI.add( artist.getId() )
        );
    }

    // TODO : ideally we should use a static spotifyData ref rather than passing it to method
    public UserData toUserData(SpotifyData spotifyData){
        UserData userData = new UserData();
        userData.setTopGenre(this.topGenre);
        topArtistsSpotifyURI.forEach(
                artistUri -> userData.getTopArtists().add(
                      spotifyData.getArtist(artistUri)
                )
        );
        topSongsSpotifyURI.forEach(
                trackUri -> userData.getTopTracks().add(
                        spotifyData.getTrack(trackUri)
                )
        );
        return userData;
    }

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

    public List<String> getTopArtistsSpotifyURI() {
        return topArtistsSpotifyURI;
    }

    public void setTopArtistsSpotifyURI(List<String> topArtistsSpotifyURI) {
        this.topArtistsSpotifyURI = topArtistsSpotifyURI;
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

    public UserData.TimeRange getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(UserData.TimeRange timeRange) {
        this.timeRange = timeRange;
    }

    @Override
    public Map<String, Object> toMap() {
        return new HashMap<String, Object>() {{
            put("userId", getUserId());
            put("topArtistsSpotifyURI", getTopArtistsSpotifyURI());
            put("topSongsSpotifyURI", getTopSongsSpotifyURI());
            put("topGenre", getTopGenre());
            put("timeRange", getTimeRange());
        }};
    }
}
