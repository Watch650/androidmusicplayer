package vn.edu.usth.midgroupproject.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "https://androidmusicplayer-be.vercel.app";  // Server's URL
    private static Retrofit retrofit;

    // Get Retrofit instance
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())  // Use Gson for JSON to Java Object conversion
                    .build();
        }
        return retrofit;
    }
}

