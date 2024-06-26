package com.example.wrappedanytime.spotify.Datatypes;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class Artist {
    private String id;
    private List<String> genres = new ArrayList<>();
    private Image image;
    private String name;

    public Artist(String id, ArrayList<String> genres, Image image, String name) {
        this.id = id;
        this.genres = genres;
        this.image = image;
        this.name = name;
    }
    public Artist() {
        this(null, null, null, null);
    }
    public Artist(String json) {
        JsonElement jsonElement = JsonParser.parseString(json);
        if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            if (jsonObject.has("name")) {
                String name = jsonObject.get("name").getAsString();
                this.setName(name);
            }
            if (jsonObject.has("id")) {
                String id = jsonObject.get("id").getAsString();
                this.setId(id);
            }
            if (jsonObject.has("images")) {
                JsonArray jsonArray = jsonObject.getAsJsonArray("images");
                JsonObject largestImage = jsonArray.get(1).getAsJsonObject();
                this.setImage(new Image(largestImage));
            }
            if (jsonObject.has("genres")) {
                JsonArray jsonArray = jsonObject.getAsJsonArray("genres");
                for (JsonElement je : jsonArray) {
                    this.genres.add(je.getAsString());
                }
            }
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id='" + id + '\'' +
                "\n genres=" + genres +
                "\n image=" + image +
                "\n name='" + name + '\'' +
                '}';
    }
}
