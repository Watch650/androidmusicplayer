package vn.edu.usth.midgroupproject.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import vn.edu.usth.midgroupproject.models.SongModel;

public interface SongApi {

    // GET request to fetch all songs
    @GET("songs")
    Call<List<SongModel>> getSongs();

    // POST request to add a new song
    @POST("songs")
    Call<SongModel> addSong(@Body SongModel newSong);
}