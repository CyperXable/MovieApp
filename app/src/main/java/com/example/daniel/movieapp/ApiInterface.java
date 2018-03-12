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
    @GET("/3/search/{category}")
    Call<FoundMovies> foundMovies(
        @Path("category") String category,
        @Query("api_key") String apiKey,
        @Query("query") String query,
        @Query("page") int page
    );

//    https://api.themoviedb.org/3/movie/popular?api_key=5efee822a961bc8a4ce567b867dfa166&language=en-US&page=1
}
