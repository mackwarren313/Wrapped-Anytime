package com.example.wrappedanytime.spotify.Datatypes;

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
