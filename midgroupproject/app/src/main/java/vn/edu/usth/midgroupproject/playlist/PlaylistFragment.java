package vn.edu.usth.midgroupproject.playlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.edu.usth.midgroupproject.R;
import vn.edu.usth.midgroupproject.playlist.RecyclerView.RecyclerViewInterface;
import vn.edu.usth.midgroupproject.playlist.RecyclerView.SongModel;
import vn.edu.usth.midgroupproject.playlist.RecyclerView.Song_RecyclerViewAdapter;
import vn.edu.usth.midgroupproject.songs.SongActivity;

public class PlaylistFragment extends Fragment implements RecyclerViewInterface {

    ArrayList<SongModel> songModels = new ArrayList<>();

    // Song images array
    int[] songImages = {R.drawable.song1, R.drawable.song3, R.drawable.song2};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_playlist, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.mRecycleView);

        SetupSongModels();
        Song_RecyclerViewAdapter adapter = new Song_RecyclerViewAdapter(requireContext(),
                songModels, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        return view;
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
        Intent intent = new Intent(requireContext(), SongActivity.class);

        intent.putExtra("TITLE", songModels.get(position).getSongTitle());
        intent.putExtra("ARTIST", songModels.get(position).getSongArtist());
        intent.putExtra("IMAGE", songModels.get(position).getSongImage());

        startActivity(intent);
    }
}

