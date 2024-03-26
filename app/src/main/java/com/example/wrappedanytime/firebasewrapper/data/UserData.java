package com.example.wrappedanytime.firebasewrapper.data;

import java.util.UUID;

public class UserData {
    private String name;
    private UUID userId;
    private String spotifyId;

    // TODO: wrapped list

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getSpotifyId() {
        return spotifyId;
    }

    public void setSpotifyId(String spotifyId) {
        this.spotifyId = spotifyId;
    }

    @Override
    public boolean equals(Object o){
        UserData otherUser = (UserData) o;
        return ! ( this.userId.equals(otherUser.getUserId())
                && this.name.equals(otherUser.getName())
                && this.spotifyId.equals(otherUser.getSpotifyId())) ;
    }
}
