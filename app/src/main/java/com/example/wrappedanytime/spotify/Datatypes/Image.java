package com.example.wrappedanytime.spotify.Datatypes;

import com.google.gson.JsonObject;

public class Image {
    String url;
    int height, width;

    public Image(String url, int height, int width) {
        this.url = url;
        this.height = height;
        this.width = width;
    }
    public Image(JsonObject object) {
        this.url = object.get("url").getAsString();
        this.height = object.get("height").getAsInt();
        this.width = object.get("width").getAsInt();
    }

    public String getUrl(){
        return url;
    }

    @Override
    public String toString() {
        return "ProfilePic{" +
                "url='" + url + '\'' +
                "\n height=" + height +
                "\n width=" + width +
                '}';
    }
}
