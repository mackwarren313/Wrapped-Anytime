package com.example.wrappedanytime.firebasewrapper.data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserData extends FirebaseItem {
    private String name;
    private UUID id;
    private String spotifyId;

    // TODO: wrapped list

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
        return ! ( this.id.equals(otherUser.getId())
                && this.name.equals(otherUser.getName())
                && this.spotifyId.equals(otherUser.getSpotifyId())) ;
    }

    @Override
    public Map<String, Object> toMap() {
        return new HashMap<String, Object>() {{
            put("name", getName());
            put("spotifyId", getSpotifyId());
        }};
    }

    public UserData(){}

    public UserData(String name, UUID id, String spotifyId) {
        this.name = name;
        this.id = id;
        this.spotifyId = spotifyId;
    }
}
