package vn.edu.usth.midgroupproject.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import vn.edu.usth.midgroupproject.R;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {

    private List<Song> songs;

    public SongAdapter(List<Song> songs) {
        this.songs = songs;
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.songs_list, parent, false);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        Song song = songs.get(position);
        holder.songName.setText(song.getSongName());
        holder.songArtist.setText(song.getArtistName());
        holder.songImage.setImageResource(song.getImage());
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public void updateData(List<Song> newSongs) {
        songs.clear();
        songs.addAll(newSongs);
        notifyDataSetChanged();
    }

    public void startListening() {
        // If needed, add logic to start observing data changes (e.g., from a database or API)
        notifyDataSetChanged();
    }

    public void stopListening() {
        // Logic to stop observing data changes, if applicable
    }

    public static class SongViewHolder extends RecyclerView.ViewHolder {
        TextView songName;
        TextView songArtist;
        ImageView songImage;

        public SongViewHolder(@NonNull View itemView) {
            super(itemView);
            songName = itemView.findViewById(R.id.song_name);
            songArtist = itemView.findViewById(R.id.artist_name);
            songImage = itemView.findViewById(R.id.Songicon);
        }
    }
}