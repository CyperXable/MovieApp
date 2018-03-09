package com.example.daniel.movieapp;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("/3/movie/{category}")
    // async
    Call<MovieResults> listOfMovies(
            @Path("category") String category,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );

//    https://api.themoviedb.org/3/movie/popular?api_key=5efee822a961bc8a4ce567b867dfa166&language=en-US&page=1
}
