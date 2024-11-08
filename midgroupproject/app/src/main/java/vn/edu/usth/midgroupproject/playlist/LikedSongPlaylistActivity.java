//package vn.edu.usth.midgroupproject.playlist;
//
//import android.content.Intent;
//import android.os.Bundle;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//
//import vn.edu.usth.midgroupproject.R;
//import vn.edu.usth.midgroupproject.playlist.RecyclerView.RecyclerViewInterface;
//import vn.edu.usth.midgroupproject.playlist.RecyclerView.SongModel;
//import vn.edu.usth.midgroupproject.playlist.RecyclerView.Song_RecyclerViewAdapter;
//import vn.edu.usth.midgroupproject.songs.SongActivity;
//
//public class LikedSongPlaylistActivity extends AppCompatActivity implements RecyclerViewInterface {
//
//    ArrayList<SongModel> songModels = new ArrayList<>();
//
//    // Song images array
//    int[] songImages = {R.drawable.song1, R.drawable.song3, R.drawable.song2};
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_playlist);
//
//        RecyclerView recyclerView = findViewById(R.id.mRecycleView);
//
//        SetupSongModels();
//        Song_RecyclerViewAdapter adapter = new Song_RecyclerViewAdapter(this,
//                songModels, this);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//    }
//
//    private void SetupSongModels(){
//        String[] songTitles = getResources().getStringArray(R.array.song_titles);
//        String[] songArtists = getResources().getStringArray(R.array.song_artists);
//
//        for (int i = 0; i < songTitles.length; i++){
//            songModels.add(new SongModel(songTitles[i],
//                    songArtists[i],
//                    songImages[i]));
//        }
//    }
//
//    @Override
//    public void onItemClick(int position) {
//        Intent intent = new Intent(LikedSongPlaylistActivity.this, SongActivity.class);
//
//        intent.putExtra("TITLE", songModels.get(position).getSongTitle());
//        intent.putExtra("ARTIST", songModels.get(position).getSongArtist());
//        intent.putExtra("IMAGE", songModels.get(position).getSongImage());
//
//        startActivity(intent);
//    }
//}