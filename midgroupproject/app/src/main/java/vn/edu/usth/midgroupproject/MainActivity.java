package vn.edu.usth.midgroupproject;

import android.os.Bundle;
import android.util.Log;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import vn.edu.usth.midgroupproject.databinding.ActivityMainBinding;
import vn.edu.usth.midgroupproject.home.HomePageFragment;
import vn.edu.usth.midgroupproject.library.LibraryPageFragment;
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
        // TODO: make navigation from an activity to another
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.home) {
                replaceFragment(new HomePageFragment());
            }
            else if (itemId == R.id.search) {
                replaceFragment(new SearchFragment());
            }
            else if (itemId == R.id.library){
                replaceFragment(new LibraryPageFragment());
            }
            else {
                return false;
            }
            return true;
        });


    }

    // These are all unnecessary (I think?)
//    @Override
//    public void onStart(){
//        super.onStart();
//        Log.i("SpotifyLib", "onStart called");
//    }
//    @Override
//    public void onResume(){
//        super.onResume();
//        Log.i("SpotifyLib", "onResume called");
//    }
//    @Override
//    public void onPause(){
//        super.onPause();
//        Log.i("SpotifyLib", "onPause called");
//    }
//    @Override
//    public void onStop(){
//        super.onStop();
//        Log.i("SpotifyLib", "onStop called");
//    }
//    @Override
//    public void onDestroy(){
//        super.onDestroy();
//        Log.i("SpotifyLib", "onDestroy called");
//    }


}