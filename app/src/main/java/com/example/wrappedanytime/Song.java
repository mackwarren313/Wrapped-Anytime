package com.example.wrappedanytime;

public class Song {
    private String name;
    private String artist;
    private String color;
    private int length;

    public Song(String name, String artist, String color, int length) {
        this.name = name;
        this.artist = artist;
        this.color = color;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Song{" +
                "name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", color='" + color + '\'' +
                ", length=" + length +
                '}';
    }
}
