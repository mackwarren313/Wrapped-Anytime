package com.example.wrappedanytime.spotify.Datatypes;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class Track {
    private String name;

    private String albumID;
    private List<String> artistsIDs;
    private int length; //in milliseconds
    private String id;
    private String previewUrl;

    public Track(String name, String albumID, List<String> artistsIDs, int length, String id, String previewUrl) {
        this.name = name;
        this.albumID = albumID;
        this.artistsIDs = artistsIDs;
        this.length = length;
        this.id = id;
        this.previewUrl = previewUrl;
    }

    public Track() {
        this(null, null, null, 0, null, null);
    }
    public Track(String json) {
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        this.setName(jsonObject.get("name").getAsString());
        this.setAlbumID(jsonObject.getAsJsonObject("album").get("id").getAsString());
        ArrayList<String> artistIDs = new ArrayList<>();
        for (JsonElement artist : jsonObject.getAsJsonArray("artists")) {
            artistIDs.add(artist.getAsJsonObject().get("id").getAsString());
        }
        this.setArtistsIDs(artistIDs);
        this.setLength(jsonObject.get("duration_ms").getAsInt());
        this.setId(jsonObject.get("id").getAsString());
        //this.setPreviewUrl(jsonObject.get("preview_url").getAsString());
        if(!(jsonObject.get("preview_url") instanceof JsonNull)) {
            this.setPreviewUrl(jsonObject.get("preview_url").getAsString());
        }
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlbumID() {
        return albumID;
    }

    public void setAlbumID(String albumID) {
        this.albumID = albumID;
    }

    public List<String> getArtistsIDs() {
        return artistsIDs;
    }

    public void setArtistsIDs(List<String> artistsIDs) {
        this.artistsIDs = artistsIDs;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    @Override
    public String toString() {
        return "Track{" +
                "name='" + name + '\'' +
                "\n albumID='" + albumID + '\'' +
                "\n artistsIDs=" + artistsIDs +
                "\n length=" + length +
                "\n id='" + id + '\'' +
                "\n previewUrl='" + previewUrl + '\'' +
                '}';
    }
}
