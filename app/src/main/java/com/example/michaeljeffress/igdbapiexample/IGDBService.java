package com.example.michaeljeffress.igdbapiexample;


import com.example.michaeljeffress.igdbapiexample.models.Game;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;


public interface IGDBService {
    @GET("games/")
    Call<List<Game>> getGame(@Header("X-Mashape-Key") String apiKey, @Query("fields")
            String gameFields, @Query("limit") int gameLimitNum, @Query("offset")
            int gameOffsetNum, @Query("order") String gameOrder, @Query("search") String gameName);
}
