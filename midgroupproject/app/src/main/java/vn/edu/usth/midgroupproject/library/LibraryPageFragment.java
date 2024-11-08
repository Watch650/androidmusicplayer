package vn.edu.usth.midgroupproject.library;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;

import vn.edu.usth.midgroupproject.R;
import vn.edu.usth.midgroupproject.playlist.PlaylistFragment;
import vn.edu.usth.midgroupproject.search.search_fragments.ChristianSongsPageFragment;

public class LibraryPageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_library_page, container, false);


        // Either change all of these to navigate to LikedSongPlaylistActivity (somehow?) or make the Library an Activity :v
        LinearLayout linearLayout1 = view.findViewById(R.id.liked_playlist);

        linearLayout1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                navigateToFragment(new PlaylistFragment());
            }
        });

        MaterialButton addButton = view.findViewById(R.id.addPlaylistButton);

//        addButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                BottomSheetDialog bsdAddBtn = new BottomSheetDialog(LibraryPageFragment.this);
//                View view1 = LayoutInflater.from((LibraryPageFragment.this).inflate(R.layout))
//            }
//        });

        return view;
    }

    public void navigateToFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.library_page, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}

// TODO: create playlist func, create fragment to display playlist info inputs, edit playlist info (opt)