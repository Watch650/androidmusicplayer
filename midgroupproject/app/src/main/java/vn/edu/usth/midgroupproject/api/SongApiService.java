package vn.edu.usth.midgroupproject.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import vn.edu.usth.midgroupproject.models.SongModel;

public interface SongApiService {
    @GET("/songs")
    Call<List<SongModel>> getSongs();
}
