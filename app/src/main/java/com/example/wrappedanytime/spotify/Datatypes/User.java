package com.example.wrappedanytime.spotify.Datatypes;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class User {
    private String displayName;
    private String id;
    private Image pfp;
    private String uri;
    private String email;

    public User(String displayName, String id, Image pfp, String uri, String email) {
        this.displayName = displayName;
        this.id = id;
        this.pfp = pfp;
        this.uri = uri;
        this.email = email;

    }
    public User() {
        this(null, null, null, null, null);
    }
    public User(String json) {
        JsonElement jsonElement = JsonParser.parseString(json);
        if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            if (jsonObject.has("display_name")) {
                String name = jsonObject.get("display_name").getAsString();
                this.setDisplayName(name);
            }
            if (jsonObject.has("id")) {
                String id = jsonObject.get("id").getAsString();
                this.setId(id);
            }
            if (jsonObject.has("uri")) {
                String uri = jsonObject.get("uri").getAsString();
                this.setUri(uri);
            }
            if (jsonObject.has("images")) {
                JsonArray jsonArray = jsonObject.getAsJsonArray("images");
                JsonObject largestImage = jsonArray.get(jsonArray.size()-1).getAsJsonObject();
                this.setPfp(new Image(largestImage));
            }
            if (jsonObject.has("email")) {
                String email = jsonObject.get("email").getAsString();
                this.setEmail(email);
            }
        }
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Image getPfp() {
        return pfp;
    }

    public void setPfp(Image pfp) {
        this.pfp = pfp;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "User{" +
                "displayName='" + displayName + '\'' +
                "\n id='" + id + '\'' +
                "\n pfp=" + pfp +
                "\n uri='" + uri + '\'' +
                "\n email='" + email + '\'' +
                '}';
    }
}
