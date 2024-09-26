package vn.edu.usth.midgroupproject.search;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.core.view.MenuProvider;
import androidx.lifecycle.Lifecycle;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.List;
import vn.edu.usth.midgroupproject.R;

public class SearchFragment extends Fragment {

    private RecyclerView rvSongs;
    private SongAdapter songAdapter;
    private SearchView searchView;
    private LinearLayout linearLayout;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        // Initialize RecyclerView and set adapter
        rvSongs = view.findViewById(R.id.rv_songs);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        rvSongs.setLayoutManager(linearLayoutManager);

        songAdapter = new SongAdapter(getListSongs());
        rvSongs.setAdapter(songAdapter);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(requireContext(), LinearLayout.VERTICAL);
        rvSongs.addItemDecoration(itemDecoration);

        linearLayout = view.findViewById(R.id.content_wrapper);

        return view;
    }

    private List<Song> getListSongs() {
        List<Song> list = new ArrayList<>();
        list.add(new Song("Dangerously", "Charlie Puth", R.drawable.img_1));
        list.add(new Song("Black in Black", "AC/DC", R.drawable.img_2));
        list.add(new Song("You Give Love a Bad Name", "Jon Davi", R.drawable.img_5));
        list.add(new Song("Song 4", "Artist 4", R.drawable.img_8));
        list.add(new Song("Song 5", "Artist 5", R.drawable.img_10));
        // Add more songs as needed
        return list;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Adding the MenuProvider for handling menu creation and interactions
        requireActivity().addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(Menu menu, MenuInflater menuInflater) {
                // Inflate the menu
                menuInflater.inflate(R.menu.searchbar, menu);

                // Set up SearchManager and SearchView
                SearchManager searchManager = (SearchManager) requireActivity().getSystemService(Context.SEARCH_SERVICE);
                searchView = (SearchView) menu.findItem(R.id.search).getActionView();

                // Configure the search information
                searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().getComponentName()));


                // Implement search functionality
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        songAdapter.getFilter().filter(query);  // Assuming the adapter has a filter method
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        songAdapter.getFilter().filter(newText);  // Filter the results in real-time
                        return false;
                    }
                });

                searchView.setOnSearchClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Show RecyclerView and hide content layout on search click
                        rvSongs.setVisibility(View.VISIBLE);
                        linearLayout.setVisibility(View.INVISIBLE);
                    }
                });

                searchView.setOnCloseListener(new SearchView.OnCloseListener() {
                    @Override
                    public boolean onClose() {
                        // Hide RecyclerView and show content layout when search view is closed
                        rvSongs.setVisibility(View.INVISIBLE);
                        linearLayout.setVisibility(View.VISIBLE);
                        return true;
                    }
                });
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                // Handle menu item selection if needed
                return false;
            }
        }, getViewLifecycleOwner(), Lifecycle.State.RESUMED);
    }
}
