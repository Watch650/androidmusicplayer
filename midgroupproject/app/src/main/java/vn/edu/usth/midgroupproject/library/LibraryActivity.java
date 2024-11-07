package vn.edu.usth.midgroupproject.library;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;

import vn.edu.usth.midgroupproject.R;
import vn.edu.usth.midgroupproject.playlist.LikedSongPlaylistActivity;

public class LibraryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        MaterialButton addButton = findViewById(R.id.addPlaylistButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    BottomSheetDialog bsdAddBtn = new BottomSheetDialog(LibraryActivity.this);
                    View view1 = LayoutInflater.from(LibraryActivity.this).inflate(R.layout.layout_add_playlist, null);
                    bsdAddBtn.setContentView(view1);
                    bsdAddBtn.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

//                TextInputLayout textInputLayout = view1.findViewById(R.id.textFieldLayout);
//                TextInputEditText editTextAdd = view1.findViewById(R.id.input_add_playlist);
            }
        });

        LinearLayout toLikedPlaylist = findViewById(R.id.liked_playlist);

        toLikedPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LibraryActivity.this, LikedSongPlaylistActivity.class);
                startActivity(intent);
            }
        });
    }
}