package com.example.wrappedanytime.spotify.Datatypes;

public class User {
    public static class ProfilePic {
        String url;
        int height, width;

        public ProfilePic(String url, int height, int width) {
            this.url = url;
            this.height = height;
            this.width = width;
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
    private String displayName;
    private String id;
    private ProfilePic pfp;
    private String uri;
    private String email;

    public User(String displayName, String id, ProfilePic pfp, String uri, String email) {
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

    public ProfilePic getPfp() {
        return pfp;
    }

    public void setPfp(ProfilePic pfp) {
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
