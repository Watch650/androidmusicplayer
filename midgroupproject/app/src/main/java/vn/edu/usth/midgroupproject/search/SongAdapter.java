package vn.edu.usth.midgroupproject.search;

import vn.edu.usth.midgroupproject.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> implements Filterable{

    private List<Song> songList;
    private List<Song> songListOld;

    public SongAdapter(List<Song> songList){
        this.songList = songList;
        this.songListOld = songList;
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.songs_list, parent, false);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        Song song = songList.get(position);
        if (song == null) {
            return;
        }
        holder.ImgSong.setImageResource(song.getImage());
        holder.SongName.setText(song.getSongName());
        holder.ArtistName.setText(song.getArtistName());
    }

    @Override
    public int getItemCount() {
        if (songList != null) {
            return songList.size();
        }
        return 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if (strSearch.isEmpty()) {
                    songList = songListOld;
                } else {
                    List<Song> list = new ArrayList<>();
                    for (Song song : songListOld) {
                        if (song.getSongName().toLowerCase().contains(strSearch.toLowerCase())) {
                            list.add(song);
                        }
                    }

                    songList = list;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = songList;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                songList = (List<Song>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class SongViewHolder extends RecyclerView.ViewHolder{

        private CircleImageView ImgSong;
        private TextView SongName;
        private TextView ArtistName;

        public SongViewHolder(@NonNull View itemView) {
            super(itemView);
            ImgSong = itemView.findViewById(R.id.Songicon);
            SongName = itemView.findViewById(R.id.song_name);
            ArtistName = itemView.findViewById(R.id.artist_name);
        }
    }
}
