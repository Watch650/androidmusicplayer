package vn.edu.usth.midgroupproject.liked;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.edu.usth.midgroupproject.R;
import vn.edu.usth.midgroupproject.songs.SongActivity;

public class LikedSongLibraryActivity extends AppCompatActivity implements RecyclerViewInterface{

    ArrayList<SongModel> songModels = new ArrayList<>();

    int[] songImages = {R.drawable.song1, R.drawable.song3, R.drawable.song2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked_songs_page);

        RecyclerView recyclerView = findViewById(R.id.mRecycleView);

        SetupSongModels();
        Song_RecyclerViewAdapter adapter = new Song_RecyclerViewAdapter(this,
                songModels, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void SetupSongModels(){
        String[] songTitles = getResources().getStringArray(R.array.song_titles);
        String[] songArtists = getResources().getStringArray(R.array.song_artists);

        for (int i = 0; i < songTitles.length; i++){
            songModels.add(new SongModel(songTitles[i],
                    songArtists[i],
                    songImages[i]));
        }
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(LikedSongLibraryActivity.this, SongActivity.class);

        intent.putExtra("TITLE", songModels.get(position).getSongTitle());
        intent.putExtra("ARTIST", songModels.get(position).getSongArtist());
        intent.putExtra("IMAGE", songModels.get(position).getSongImage());

        startActivity(intent);
    }
}