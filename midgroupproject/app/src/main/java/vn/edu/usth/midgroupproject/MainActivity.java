package vn.edu.usth.midgroupproject;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import vn.edu.usth.midgroupproject.databinding.ActivityMainBinding;
import vn.edu.usth.midgroupproject.ui.home.HomePageFragment;
import vn.edu.usth.midgroupproject.ui.library.LibraryPageFragment;
import vn.edu.usth.midgroupproject.ui.search.SearchFragment;
import vn.edu.usth.midgroupproject.R;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set the default fragment (HomePageFragment) when app opens
        if (savedInstanceState == null) {
            replaceFragment(new HomePageFragment());
        }

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.home) {
                replaceFragment(new HomePageFragment());
            } else if (itemId == R.id.search) {
                replaceFragment(new SearchFragment());
            } else if (itemId == R.id.library) {
                replaceFragment(new LibraryPageFragment());
            } else {
                return false;
            }
            return true;
        });
    }

    // Method to replace the fragment in frame_layout
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null; // Prevent memory leaks by releasing binding
    }
}
