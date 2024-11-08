package vn.edu.usth.midgroupproject.library;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import vn.edu.usth.midgroupproject.R;
import vn.edu.usth.midgroupproject.playlist.PlaylistFragment;

public class LibraryPageFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_library, container, false);

        // Handle click to navigate to PlaylistFragment
        LinearLayout likedPlaylist = view.findViewById(R.id.liked_playlist);
        likedPlaylist.setOnClickListener(v -> navigateToFragment(new PlaylistFragment()));

        // Set up button to open BottomSheetDialog for creating a playlist
        AppCompatImageButton addButton = view.findViewById(R.id.addPlaylistButton);
        addButton.setOnClickListener(v -> showAddPlaylistDialog());

        return view;
    }

    //    public void navigateToFragment(Fragment fragment) {
//        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.library_page, fragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
//    }
    // Method to navigate to different fragments
    private void navigateToFragment(Fragment fragment) {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.library_page, fragment);  // Ensure fragment_container exists in the activity
        fragmentTransaction.addToBackStack(null);  // Add to back stack for navigation
        fragmentTransaction.commit();
    }

    // Method to show BottomSheetDialog for adding a new playlist
    private void showAddPlaylistDialog() {
        BottomSheetDialog addPlaylistDialog = new BottomSheetDialog(requireContext());
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.layout_add_playlist, null);

        // TODO: Initialize and handle input fields in layout_add_playlist
        // Example: TextInputEditText playlistNameInput = dialogView.findViewById(R.id.input_add_playlist);

        addPlaylistDialog.setContentView(dialogView);
        addPlaylistDialog.show();

    }
}

// TODO: create playlist func, create fragment to display playlist info inputs, edit playlist info (opt)