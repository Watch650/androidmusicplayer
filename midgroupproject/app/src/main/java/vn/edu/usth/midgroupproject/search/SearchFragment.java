package vn.edu.usth.midgroupproject.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import vn.edu.usth.midgroupproject.R;
import vn.edu.usth.midgroupproject.liked.ChristianSongsPageFragment;
import vn.edu.usth.midgroupproject.liked.IndieSongsPageFragment;
import vn.edu.usth.midgroupproject.liked.PopSongsPageFragment;
import vn.edu.usth.midgroupproject.liked.RockSongsPageFragment;

public class SearchFragment extends Fragment {

    private RecyclerView rvSongs;
    private SongAdapter adapter;
    private LinearLayout linearLayout;
    private EditText searchView;
    private Button searchButton;
    private List<Map<String, String>> searchResults;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchView = view.findViewById(R.id.searchbar);
        searchButton = view.findViewById(R.id.search_button);

        rvSongs = view.findViewById(R.id.rv_songs);

        rvSongs.setAdapter(adapter);

        linearLayout = view.findViewById(R.id.content_wrapper);

        searchResults = new ArrayList<>();
        adapter = new SongAdapter(searchResults, result -> {
            String songName = result.get("song");
            String url = result.get("url");
        });
        rvSongs.setAdapter(adapter);
        rvSongs.setLayoutManager(new LinearLayoutManager(getContext()));

        searchButton.setOnClickListener(v -> performSearch());

        ImageView imageView1 = view.findViewById(R.id.Pop);
        ImageView imageView2 = view.findViewById(R.id.Christian);
        ImageView imageView3 = view.findViewById(R.id.Indie);
        ImageView imageView4 = view.findViewById(R.id.Rock);

        imageView1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {navigateToFragment(new PopSongsPageFragment());}
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {navigateToFragment(new ChristianSongsPageFragment());}
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {navigateToFragment(new IndieSongsPageFragment());}
        });
        imageView4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {navigateToFragment(new RockSongsPageFragment());}
        });
    }

    private void performSearch() {
        String query = searchView.getText().toString().trim();
        if (query.isEmpty()) {
            Toast.makeText(getContext(), "Please enter a search term", Toast.LENGTH_SHORT).show();
            return;
        }

        String url;
        try {
            // Encode the query to handle spaces and special characters
            String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8.toString());
            url = "api spotify search"
                    + encodedQuery;
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Encoding error", Toast.LENGTH_SHORT).show();
            return;
        }

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                requireActivity().runOnUiThread(() ->
                        Toast.makeText(getContext(), "Search failed", Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                String responseData = response.body().string();
                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(responseData, JsonObject.class);
                JsonObject queryObject = jsonObject.getAsJsonObject("query");
                JsonArray searchResultsArray = queryObject.getAsJsonArray("search");

                List<Map<String, String>> results = new ArrayList<>();
                for (int i = 0; i < searchResultsArray.size(); i++) {
                    JsonObject item = searchResultsArray.get(i).getAsJsonObject();
                    Map<String, String> result = new HashMap<>();
                    result.put("song", item.get("song").getAsString());
                    result.put("artist", item.get("artist").getAsString());

                    // Construct the full URL for the article
                    String articleTitle = item.get("song").getAsString();
                    String encodedTitle = URLEncoder.encode(articleTitle, StandardCharsets.UTF_8.toString());

                    // Replace '+' with '_'
                    String articleUrl = "api spotify search"
                            + encodedTitle.replace("+", "_");

                    result.put("url", articleUrl);
                    results.add(result);
                }

                requireActivity().runOnUiThread(() -> {
                    searchResults.clear();
                    searchResults.addAll(results);
                    adapter.notifyDataSetChanged();
                });
            }
        });
    }

    public void navigateToFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
