package vn.edu.usth.midgroupproject.playlist.RecyclerView;

public class SongModel {
    String songTitle;
    String songArtist;
    int songImage;


    public SongModel(String songTitle, String songArtist, int songImage) {
        this.songTitle = songTitle;
        this.songArtist = songArtist;
        this.songImage = songImage;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public String getSongArtist() {
        return songArtist;
    }

    public int getSongImage() {
        return songImage;
    }
}
