package com.example.wrappedanytime.spotify.Datatypes;

public class UserData {

    private String userID;
    private Track[] topTracks;
    private String[] topArtists;
    private String topGenre;

    public UserData (String userID, Track[] topTracks, String[] topArtists, String topGenre) {
        this.userID = userID;
        this.topTracks = topTracks;
        this.topArtists = topArtists;
        this.topGenre = topGenre;
    }

    public UserData() { this(null, null, null, null); }

    public Track[] getTopTracks() { return topTracks; }

    public String[] getTopArtists() { return topArtists; }

    public String getTopGenre() { return topGenre; }

    public String getUserID() { return userID; }

    public void setTopTracks(Track[] topTracks) { this.topTracks = topTracks; }

    public void setTopArtists(String[] topArtists) { this.topArtists = topArtists; }

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
