package vn.edu.usth.midgroupproject.ui.playlist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vn.edu.usth.midgroupproject.R;
import vn.edu.usth.midgroupproject.ui.playlist.RecyclerView.RecyclerViewInterface;
import vn.edu.usth.midgroupproject.models.SongModel;
import vn.edu.usth.midgroupproject.api.SongApiService;
import vn.edu.usth.midgroupproject.ui.playlist.RecyclerView.Song_RecyclerViewAdapter;
import vn.edu.usth.midgroupproject.ui.song_player.SongActivity;

public class PlaylistFragment extends Fragment implements RecyclerViewInterface {

    ArrayList<SongModel> songModels = new ArrayList<>();
    Song_RecyclerViewAdapter adapter;
    SongApiService songApiService;

    // Song images array
//    int[] songImages = {R.drawable.song1, R.drawable.song3, R.drawable.song2};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_playlist, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.mRecycleView);
        adapter = new Song_RecyclerViewAdapter(requireContext(), songModels, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Initialize Retrofit and SongApiService
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://androidmusicplayer-be.vercel.app")  // Replace with base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        songApiService = retrofit.create(SongApiService.class);

        // Fetch songs from the API
        fetchSongsFromApi();

//        ImageButton addSongButton = view.findViewById(R.id.add_button);
//        addSongButton.setOnClickListener(v -> addSong("New Song", "New Artist", "https://example.com/image.jpg", "https://example.com/song.mp3"));

        return view;
    }


    private void fetchSongsFromApi() {
        Call<List<SongModel>> call = songApiService.getSongs();
        call.enqueue(new Callback<List<SongModel>>() {
            @Override
            public void onResponse(Call<List<SongModel>> call, Response<List<SongModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Log the response
                    Log.d("API Response", "Songs fetched: " + response.body().size());
                    songModels.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(requireContext(), "Failed to load songs", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<SongModel>> call, Throwable t) {
                Toast.makeText(requireContext(), "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }



    // Method to add a new song
    public void addSong(String title, String artist, String imageUrl , String mp3Url) {
        // Create a new SongModel object, now passing mp3Url as well
        SongModel newSong = new SongModel(title, artist, imageUrl, mp3Url);

        // Add it to the song list
        songModels.add(newSong);

        // Notify the adapter about the new item
        adapter.notifyItemInserted(songModels.size() - 1);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(requireContext(), SongActivity.class);
        SongModel song = songModels.get(position);
        intent.putExtra("TITLE", song.getSongTitle());
        intent.putExtra("ARTIST", song.getSongArtist());
        intent.putExtra("IMAGE", song.getSongImage());
        intent.putExtra("MP3_URL", song.getMp3Url());
        startActivity(intent);
    }
}

