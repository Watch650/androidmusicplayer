package vn.edu.usth.midgroupproject.ui.song_player;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.io.IOException;

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
        String image = getIntent().getStringExtra("IMAGE");
        String mp3Url = getIntent().getStringExtra("MP3_URL");

        TextView tvTitle = findViewById(R.id.song_title_fragment);
        TextView tvArtist = findViewById(R.id.song_artist_fragment);
        ImageView imageView = findViewById(R.id.song_image_fragment);

        tvTitle.setText(title);
        tvArtist.setText(artist);

        // Load image using Glide
        Glide.with(this)
                .load("https://androidmusicplayer-be.vercel.app" + image)  // Add base URL here
                .into(imageView);

        // Setup Like button
        ImageView likeButton = findViewById(R.id.like_button);
        likeButton.setOnClickListener(v -> {
            if (isLiked) {
                likeButton.setImageResource(R.drawable.like_off);
            } else {
                likeButton.setImageResource(R.drawable.like_on);
            }
            isLiked = !isLiked;
        });

        // Initialize MediaPlayer for the mp3Url
        ImageView playButton = findViewById(R.id.play_button);
        setupMediaPlayer("https://androidmusicplayer-be.vercel.app" + mp3Url); // Add base URL here

        // Play/Pause button behavior
        playButton.setOnClickListener(v -> {
            if (isPlaying) {
                mediaPlayer.pause();
                playButton.setImageResource(R.drawable.playbutton);
            } else {
                mediaPlayer.start();
                playButton.setImageResource(R.drawable.pausebutton);
            }
            isPlaying = !isPlaying;
        });
    }

    private void setupMediaPlayer(String mp3Url) {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build());

        try {
            mediaPlayer.setDataSource(mp3Url);
            mediaPlayer.prepareAsync();  // Use prepareAsync to load the data in the background
            mediaPlayer.setOnPreparedListener(mp -> {
                // MediaPlayer is ready, we can start playing if needed
                Toast.makeText(SongActivity.this, "Ready to play", Toast.LENGTH_SHORT).show();
            });
            mediaPlayer.setOnCompletionListener(mp -> {
                // Reset the play button icon when the song ends
                ImageView playButton = findViewById(R.id.play_button);
                playButton.setImageResource(R.drawable.playbutton);
                isPlaying = false;
            });
        } catch (IOException e) {
            Toast.makeText(this, "Error loading song", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
