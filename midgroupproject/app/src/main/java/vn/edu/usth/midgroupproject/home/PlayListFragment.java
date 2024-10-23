package vn.edu.usth.midgroupproject.home;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import vn.edu.usth.midgroupproject.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlayListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlayListFragment extends Fragment {
    private boolean isLiked = false;
    private boolean isPlaying = false;
    MediaPlayer mediaPlayer1, mediaPlayer2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_play_list, container, false);

        return view;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView likeButton1 = view.findViewById(R.id.home_like_button_1);
        ImageView likeButton2 = view.findViewById(R.id.home_like_button_2);

        likeButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLiked) {
                    likeButton1.setImageResource(R.drawable.unlike);
                } else {
                    likeButton1.setImageResource(R.drawable.like);
                }
                isLiked = !isLiked;
            }
        });

        likeButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLiked) {
                    likeButton2.setImageResource(R.drawable.unlike);
                } else {
                    likeButton2.setImageResource(R.drawable.like);
                }
                isLiked = !isLiked;
            }
        });

        ImageView playButton1 = view.findViewById(R.id.home_play_button_1);
        ImageView playButton2 = view.findViewById(R.id.home_play_button_2);

        mediaPlayer1 = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.as_it_was__harry_styles);
        mediaPlayer2 = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.space_cadet__metro_boomin);

        playButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    mediaPlayer1.pause();
                    playButton1.setImageResource(R.drawable.playbutton);
                } else {
                    mediaPlayer1.start();
                    playButton1.setImageResource(R.drawable.pausebutton);
                }
                isPlaying = !isPlaying;
            }
        });

        playButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    mediaPlayer2.pause();
                    playButton2.setImageResource(R.drawable.playbutton);
                } else {
                    mediaPlayer2.start();
                    playButton2.setImageResource(R.drawable.pausebutton);
                }
                isPlaying = !isPlaying;
            }
        });


    }
}