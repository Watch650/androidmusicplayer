package vn.edu.usth.midgroupproject;

import android.os.Bundle;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import vn.edu.usth.midgroupproject.databinding.ActivityMainBinding;
import vn.edu.usth.midgroupproject.home.HomePageFragment;
import vn.edu.usth.midgroupproject.library.LibraryPageFragment;
import vn.edu.usth.midgroupproject.playlist.PlaylistFragment;
import vn.edu.usth.midgroupproject.search.SearchFragment;

public class MainActivity extends AppCompatActivity {
    //    Page navigation
    ActivityMainBinding binding;

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new HomePageFragment());
        // TODO: make navigation from a fragment to another
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.home) {
                replaceFragment(new HomePageFragment());
            }
            else if (itemId == R.id.search) {
                replaceFragment(new SearchFragment());
            }
            else if (itemId == R.id.library){
//                Intent intent = new Intent(MainActivity.this, LibraryActivity.class);
//                startActivity(intent);
                replaceFragment(new LibraryPageFragment());
            }
            else {
                return false;
            }
            return true;
        });

    }

}