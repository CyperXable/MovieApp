package com.example.daniel.movieapp;


import com.example.daniel.movieapp.Models.PopularMovies;
import com.example.daniel.movieapp.Models.FoundMovies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("/3/movie/{category}")
    Call<PopularMovies> listOfMovies(
            @Path("category") String category,
        @Query("api_key") String apiKey,
        @Query("language") String language,
        @Query("page") int page
    );
    @GET("/3/search/movie")
    Call<FoundMovies> foundMovies(
        @Query("api_key") String apiKey,
        @Query("query") String query,
        @Query("page") int page
    );
}
