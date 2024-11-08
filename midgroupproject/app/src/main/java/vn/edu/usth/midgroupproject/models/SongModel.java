package vn.edu.usth.midgroupproject.models;

import com.google.gson.annotations.SerializedName;

public class SongModel {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String songTitle;

    @SerializedName("artist")
    private String songArtist;

    @SerializedName("image")
    private String songImage;

    @SerializedName("mp3Url")
    private String mp3Url;

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getSongArtist() {
        return songArtist;
    }

    public void setSongArtist(String songArtist) {
        this.songArtist = songArtist;
    }

    public String getSongImage() {
        return songImage;
    }

    public void setSongImage(String songImage) {
        this.songImage = songImage;
    }

    public String getMp3Url() {
        return mp3Url;
    }

    public void setMp3Url(String mp3Url) {
        this.mp3Url = mp3Url;
    }
}
