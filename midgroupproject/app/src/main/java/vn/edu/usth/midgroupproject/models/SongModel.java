package vn.edu.usth.midgroupproject.models;

public class SongModel {
    String songTitle;
    String songArtist;
    String songImage; // Change to String for URL
    String mp3Url;

    public SongModel(String songTitle, String songArtist, String songImage, String mp3Url) {
        this.songTitle = songTitle;
        this.songArtist = songArtist;
        this.songImage = songImage;
        this.mp3Url = mp3Url;
    }

    // Getters
    public String getSongTitle() { return songTitle; }
    public String getSongArtist() { return songArtist; }
    public String getSongImage() { return songImage; }
    public String getMp3Url() { return mp3Url; }
}