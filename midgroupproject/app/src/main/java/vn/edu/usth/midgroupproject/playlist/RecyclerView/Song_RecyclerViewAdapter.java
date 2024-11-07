package vn.edu.usth.midgroupproject.playlist.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import vn.edu.usth.midgroupproject.R;

public class Song_RecyclerViewAdapter extends RecyclerView.Adapter<Song_RecyclerViewAdapter.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;

    Context context;
    ArrayList<SongModel> songModels;

    public Song_RecyclerViewAdapter (Context context, ArrayList<SongModel> songModels,
                                     RecyclerViewInterface recyclerViewInterface){
        this.context = context;
        this.songModels = songModels;
        this.recyclerViewInterface = recyclerViewInterface;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout (Giving a look to the rows)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_song_list_view, parent, false);
        return new MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Assigning values to the views created in the recycler_view_row layout
        // based on the position of recycler view

        holder.imageView.setImageResource(songModels.get(position).getSongImage());
        holder.tvTitle.setText(songModels.get(position).getSongTitle());
        holder.tvArtist.setText(songModels.get(position).getSongArtist());
    }

    @Override
    public int getItemCount() {
        // Show the recycler view the number of items being displayed
        return songModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        // Grabbing the views from recycler_view_row layout
        // Act similarly to onCreate method

        ImageView imageView;
        TextView tvTitle, tvArtist;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            imageView = itemView.findViewById(R.id.song_image);
            tvTitle = itemView.findViewById(R.id.song_title);
            tvArtist = itemView.findViewById(R.id.song_artist);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null){
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}

