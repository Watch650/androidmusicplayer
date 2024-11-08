package vn.edu.usth.midgroupproject.songs;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import vn.edu.usth.midgroupproject.R;

public class SongActivity extends AppCompatActivity {

    private boolean isLiked = false;
    private boolean isPlaying = false;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        String title = getIntent().getStringExtra("TITLE");
        String artist = getIntent().getStringExtra("ARTIST");
        int image = getIntent().getIntExtra("IMAGE", 0);

        TextView tvTitle = findViewById(R.id.song_title_fragment);
        TextView tvArtist = findViewById(R.id.song_artist_fragment);
        ImageView imageView = findViewById(R.id.song_image_fragment);

        tvTitle.setText(title);
        tvArtist.setText(artist);
        imageView.setImageResource(image);

        //        Like button behaviour
        ImageView likeButton = findViewById(R.id.like_button);

        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLiked) {
                    likeButton.setImageResource(R.drawable.like_off);
                } else {
                    likeButton.setImageResource(R.drawable.like_on);
                }
                isLiked = !isLiked;
            }
        });

//        Play button behaviour
        ImageView playButton = findViewById(R.id.play_button);
        mediaPlayer = MediaPlayer.create(this, R.raw.dangerously_charlie_puth);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    mediaPlayer.pause();
                    playButton.setImageResource(R.drawable.playbutton);
                } else {
                    mediaPlayer.start();
                    playButton.setImageResource(R.drawable.pausebutton);
                }
                isPlaying = !isPlaying; // Toggle the state
            }
        });
    }

}