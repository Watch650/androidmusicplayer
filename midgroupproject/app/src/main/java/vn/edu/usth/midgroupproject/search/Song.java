package vn.edu.usth.midgroupproject.search;

public class Song {
    private String songName;
    private String artistName;
    private int image;

    public Song(String songName, String artistName, int image) {
        this.songName = songName;
        this.artistName = artistName;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
}
